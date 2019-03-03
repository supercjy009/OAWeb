package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.mapper.*;
import demo.model.dto.*;
import demo.model.*;
import demo.service.OrderService;
import demo.util.EntityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class OrderServiceImp implements OrderService {
    @Resource
    OrderEntityMapper orderEntityMapper;
    @Resource
    PartTimeUserMapper partUserMapper;
    @Resource
    PartTimeEntityMapper partTimeMapper;
    @Resource
    PayProgressMapper progressMapper;
    @Resource
    EditRecordEntityMapper recordMapper;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    @Resource
    SysUserroleServiceImp sysUserroleServiceImp;

    @Override
    public PageInfo<PartTimeOrderRes> queryAllOrder(OrderReqVo vo) throws ParseException {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(vo.getOrderDateReq())) {
            String[] dateSplit = vo.getOrderDateReq().split("~");
            vo.setOrderDateStart(format.parse(dateSplit[0].trim()));
            vo.setOrderDateEnd(format.parse(dateSplit[1]));
        }
        if (!StringUtils.isEmpty(vo.getDeliveryDateReq())) {
            String[] dateSplit = vo.getDeliveryDateReq().split("~");
            vo.setDeliveryDateStart(format.parse(dateSplit[0].trim()));
            vo.setDeliveryDateEnd(format.parse(dateSplit[1]));
        }
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        List<String> permissions = sysPermissionSerivceImp.selectPermissionListByRoleId();
        vo.setSeeAll(permissions.contains("order:all") || permissions.contains("all"));
        vo.setUid(userinfoEntity.getUid());
        List<PartTimeOrderRes> orderList = orderEntityMapper.selectAllOrder(vo);
        return new PageInfo<>(orderList);
    }

    @Override
    @Transactional
    public int addOrder(OrderVo order) {
        OrderEntity entity = orderEntityMapper.selectByOrderNumber(order.getOrderNumber());
        if (entity != null) {
            return -100;
        }
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        order.setServiceName(userinfoEntity.getName());
        order.setServiceId(userinfoEntity.getUid().toString());
        order.setGetOrderDate(new Date());
        order.setAudit("");
        orderEntityMapper.insert(order);
        Long orderId = order.getId();
        if (order.getProgressList() != null && order.getProgressList().size() != 0) {
            for (int i = 0; i < order.getProgressList().size(); i++) {
                PayProgress payProgress = order.getProgressList().get(i);
                if (payProgress == null) {
                    continue;
                }
                payProgress.setOrderId(orderId.intValue());
                progressMapper.insert(payProgress);
            }
        }
        return 1;
    }

    @Override
    @Transactional
    public int updateOrder(OrderVo order) {
        Long orderId = order.getId();
        Boolean editProgress = false;
        if (order.getProgressList() != null && order.getProgressList().size() != 0) {
            List<PayProgress> oldProgressList = progressMapper.selectByOrderId(orderId);
            List<Long> oldIds = new ArrayList<>();
            for (PayProgress oldProgress : oldProgressList) {
                //记录原始有哪些付款进度
                oldIds.add(oldProgress.getId());
            }
            for (int i = 0; i < order.getProgressList().size(); i++) {
                PayProgress payProgress = order.getProgressList().get(i);
                if (payProgress == null) {
                    continue;
                }
                if (payProgress.getId() != null && oldIds.contains(payProgress.getId())) {//包含说明没有被删除
                    oldIds.remove(payProgress.getId());
                } else {
                    editProgress = true;
                    payProgress.setOrderId(orderId.intValue());
                    progressMapper.insert(payProgress);
                }
            }

            for (Long oldId : oldIds) { //删除这些付款进度
                editProgress = true;
                progressMapper.deleteByPrimaryKey(oldId);
            }
        }
        order.setAudit("0");
        //记录哪些单元格需要变色
        OrderEntity originalOrder = orderEntityMapper.selectByPrimaryKey(orderId);
        List<String> rec = new ArrayList<>();
        try {
            originalOrder.setAudit("0");
            rec = EntityUtil.compareEntity(originalOrder, order);
            if(editProgress){
                rec.add("payDate");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String fieldName : rec) {
            EditRecordEntity record = new EditRecordEntity();
            record.setRecordId(originalOrder.getId());
            record.setFieldName(fieldName);
            record.setTableName("order");
            EditRecordEntity key = recordMapper.selectByPrimaryKey(record);
            if (key == null) {
                recordMapper.insert(record);
            }
        }
        return orderEntityMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int auditOrder(AuditVo vo) {
        if ("1".equals(vo.getAudit())) {//删除变色的单元格
            DeleteRecordVo deleteVo = new DeleteRecordVo();
            deleteVo.setTableName("order");
            deleteVo.setIds(vo.getIds());
            recordMapper.deleteRecord(deleteVo);
        }
        if ("1".equals(vo.getPartAudit())) {//删除变色的兼职单元格
            DeleteRecordVo deleteVo = new DeleteRecordVo();
            deleteVo.setTableName("part");
            deleteVo.setIds(vo.getIds());
            recordMapper.deleteRecord(deleteVo);
        }

        return orderEntityMapper.auditOrder(vo);
    }

    @Override
    @Transactional
    public int appointPart(AppointPartVo vo) {
        OrderEntity order = orderEntityMapper.selectByPrimaryKey(vo.getOrderId());
        PartTimeUser partTimeUser = partUserMapper.getPartUserByQq(vo.getPartQq());
        PartTimeEntity partTimeTmp = partTimeMapper.getOrderPartByQq(vo.getPartQq(), order.getOrderNumber());
        if (partTimeUser == null) {
            return -1;
        }
        if (partTimeTmp != null) { //已经指派过了
            return -2;
        }
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        PartTimeEntity partTime = new PartTimeEntity();
        partTime.setSendServiceName(userinfoEntity.getName());
        partTime.setSendServiceId(userinfoEntity.getUid());
        partTime.setOrderNumber(order.getOrderNumber());
        partTime.setPartQq(vo.getPartQq());
        partTime.setPartMoney(vo.getPartMoney() == null ? BigDecimal.ZERO : vo.getPartMoney());
        partTime.setPartRemark(vo.getPartRemark());
        partTime.setPartAudit("");//审核待审(改为空，标志为初始状态)
        partTime.setPartSettleState("0");//状态待结
        partTime.setSubmitState("0");//状态待交稿
//        partTime.setDeduct(BigDecimal.ZERO);//应扣初始化为0
//        partTime.setPartAuditFinance("0"); //兼职接单表审核状态
//        partTime.setPartSettleStateFinance("-1"); //兼职接单表结算状态
        partTime.setPartPhone(partTimeUser.getPartPhone());
        partTime.setPartAlipay(partTimeUser.getPartAlipay());
//        partTime.setPartMoneyReal("0");//实发稿酬
        partTime.setCreateTime(new Date());//派单日期
        partTime.setOrderMasterHand(partTimeUser.getMasterHand());
        partTimeMapper.insert(partTime);
        //更新最近接单日和接单数量
        partTimeUser.setRecentOrderDate(new Date());
        int getOrder = partTimeUser.getGetOrderNumber() == null ? 0 : partTimeUser.getGetOrderNumber();
        partTimeUser.setGetOrderNumber(getOrder + 1);
        partUserMapper.updateByPrimaryKeySelective(partTimeUser);
        return 1;
    }

    @Override
    @Transactional
    public int deletePart(AppointPartVo vo) {
        //删除单元格变色
        DeleteRecordVo deleteVo = new DeleteRecordVo();
        deleteVo.setTableName("part");
        Long[] ids = {Long.valueOf(vo.getPartId())};
        deleteVo.setIds(ids);
        recordMapper.deleteRecord(deleteVo);
        PartTimeUser partTimeUser = partUserMapper.getPartUserByQq(vo.getPartQq());
        if (partTimeUser != null) {
            //更新接单数量
            int getOrder = partTimeUser.getGetOrderNumber() == null ? 0 : partTimeUser.getGetOrderNumber();
            partTimeUser.setGetOrderNumber(getOrder - 1);
            partUserMapper.updateByPrimaryKeySelective(partTimeUser);
        }
        return partTimeMapper.deleteByPrimaryKey(vo.getPartId());
    }

    @Override
    @Transactional
    public int editPart(AppointPartVo vo) {
        PartTimeEntity originalPartTime = partTimeMapper.selectByPrimaryKey(vo.getPartId());
        PartTimeEntity partTimeEdit = new PartTimeEntity();
//        PartTimeUser partTimeUser = partUserMapper.getPartUserByQq(vo.getPartQq());
//        if (StringUtils.isEmpty(partTime.getDeduct()) && partTimeUser != null) {
//            //更新问题率
//            Integer issue = partTimeUser.getProblemRate() == null ? 0 : Integer.valueOf(partTimeUser.getProblemRate());
//            partTimeUser.setProblemRate(String.valueOf(issue + 1));
//            partUserMapper.updateByPrimaryKeySelective(partTimeUser);
//
//        }
        partTimeEdit.setId(vo.getPartId());
        partTimeEdit.setPartRemark(vo.getPartRemark());
        partTimeEdit.setPartMoney(vo.getPartMoney());
        //为了变色，因为null应扣就不能变色了。。compareEntity里有判断
        partTimeEdit.setDeduct(vo.getDeduct() == null ? new BigDecimal(1234.4321) : vo.getDeduct());

        //记录哪些单元格需要变色
        List<String> rec = new ArrayList<>();
        try {
            rec = EntityUtil.compareEntity(originalPartTime, partTimeEdit);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String fieldName : rec) {
            EditRecordEntity record = new EditRecordEntity();
            record.setRecordId(Long.valueOf(originalPartTime.getId()));
            record.setFieldName(fieldName);
            record.setTableName("part");
            EditRecordEntity key = recordMapper.selectByPrimaryKey(record);
            if (key == null) {
                recordMapper.insert(record);
            }
        }
        originalPartTime.setPartAudit("0");
        originalPartTime.setPartRemark(vo.getPartRemark());
        originalPartTime.setPartMoney(vo.getPartMoney());
        originalPartTime.setDeduct(vo.getDeduct());
        return partTimeMapper.updateByPrimaryKey(originalPartTime);
    }

    @Override
    @Transactional
    public int deleteOrder(Long[] ids) {
        //删除单元格变色
        DeleteRecordVo deleteVo = new DeleteRecordVo();
        deleteVo.setTableName("order");
        deleteVo.setIds(ids);
        recordMapper.deleteRecord(deleteVo);

        for (Long id : ids) {
            OrderEntity order = orderEntityMapper.selectByPrimaryKey(id);
            partTimeMapper.deletePart(order.getOrderNumber(), null);
        }
        return orderEntityMapper.deleteByPrimaryKeys(ids);
    }

    @Override
    public int editFinaRemark(RemarkVo vo) {
        return partTimeMapper.editFinaRemark(vo.getId(), vo.getRemark());
    }

    @Override
    public List<ServiceVo> selectAllService(String serviceName, String flag, String partName) {
        if ("1".equals(flag)) {//接单客服
            return orderEntityMapper.selectAllService(serviceName, partName);
        }

        if ("2".equals(flag)) {//派单客服
            return orderEntityMapper.selectAllSendService(serviceName, partName);
        }

        return null;
    }

    @Override
    public OrderCountVo countOrder(Long[] ids) {
        OrderCountVo vo = new OrderCountVo();
        List<OrderCountVo> list = orderEntityMapper.countOrder(ids);
        vo.setOrderPrice(list.stream().mapToDouble(OrderCountVo::getOrderPrice).sum());
        vo.setRefundMoney(list.stream().mapToDouble(OrderCountVo::getRefundMoney).sum());
        vo.setPartMoney(list.stream().mapToDouble(OrderCountVo::getPartMoney).sum());
        vo.setDeduct(list.stream().mapToDouble(OrderCountVo::getDeduct).sum());
        return vo;
    }
}
