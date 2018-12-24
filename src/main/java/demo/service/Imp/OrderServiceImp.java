package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.model.dto.*;
import demo.mapper.OrderEntityMapper;
import demo.mapper.PartTimeEntityMapper;
import demo.mapper.PartTimeUserMapper;
import demo.mapper.PayProgressMapper;
import demo.model.*;
import demo.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        List<PartTimeOrderRes> orderList = orderEntityMapper.selectAllOrder(vo);
        return new PageInfo<>(orderList);
    }

    @Override
    public int addOrder(OrderVo order) {
        OrderEntity entity = orderEntityMapper.selectByOrderNumber(order.getOrderNumber());
        if (entity != null) {
            return -100;
        }
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        order.setServiceName(userinfoEntity.getName());
        order.setServiceId(userinfoEntity.getUid().toString());
        order.setGetOrderDate(new Date());
        order.setAudit("0");
        orderEntityMapper.insert(order);
        Long orderId = order.getId();
        if (order.getProgressList() != null && order.getProgressList().size() != 0) {
            for (int i = 0; i < order.getProgressList().size(); i++) {
                PayProgress payProgress = order.getProgressList().get(i);
                payProgress.setOrderId(orderId.intValue());
                progressMapper.insert(payProgress);
            }
        }
        return 1;
    }

    @Override
    public int updateOrder(OrderVo order) {
        Long orderId = order.getId();
        if (order.getProgressList() != null && order.getProgressList().size() != 0) {
            for (int i = 0; i < order.getProgressList().size(); i++) {
                PayProgress payProgress = order.getProgressList().get(i);
                if (payProgress.getId() != null) {

                }
                payProgress.setOrderId(orderId.intValue());
                progressMapper.insert(payProgress);
            }
        }
        order.setAudit("0");
        return orderEntityMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int auditOrder(AuditVo vo) {
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
        PartTimeEntity partTime = new PartTimeEntity();
        partTime.setOrderNumber(order.getOrderNumber());
        partTime.setPartQq(vo.getPartQq());
        partTime.setPartMoney(vo.getPartMoney());
        partTime.setPartRemark(vo.getPartRemark());
        partTime.setPartSettleState("0");//待结
        partTime.setSubmitState("0");//待交稿
        partTime.setPartPhone(partTimeUser.getPartPhone());
        partTime.setPartAlipay(partTimeUser.getPartAlipay());
        partTime.setPartMoneyReal("0");//实发稿酬
        partTime.setCreateTime(new Date());//派单日期
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
        PartTimeEntity partTime = partTimeMapper.selectByPrimaryKey(vo.getPartId());
        PartTimeUser partTimeUser = partUserMapper.getPartUserByQq(vo.getPartQq());
        if (StringUtils.isEmpty(partTime.getDeduct()) && partTimeUser != null) {
            //更新问题率
            Integer issue = partTimeUser.getProblemRate() == null ? 0 : Integer.valueOf(partTimeUser.getProblemRate());
            partTimeUser.setProblemRate(String.valueOf(issue + 1));
            partUserMapper.updateByPrimaryKeySelective(partTimeUser);

        }
        partTime.setPartRemark(vo.getPartRemark());
        partTime.setPartMoney(vo.getPartMoney());
        partTime.setDeduct(vo.getDeduct());
        return partTimeMapper.updateByPrimaryKeySelective(partTime);
    }

    @Override
    public int deleteOrder(Long[] ids) {
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
}
