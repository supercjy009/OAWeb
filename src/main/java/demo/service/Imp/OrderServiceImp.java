package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.dto.*;
import demo.mapper.OrderEntityMapper;
import demo.mapper.PartTimeEntityMapper;
import demo.mapper.PartTimeUserMapper;
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
    public int addOrder(OrderEntity order) {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        order.setServiceName(userinfoEntity.getName());
        return orderEntityMapper.insert(order);
    }

    @Override
    public int updateOrder(OrderEntity order) {
        return orderEntityMapper.updateByPrimaryKey(order);
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
        partTimeMapper.insert(partTime);
        //更新最近接单日和接单数量
        partTimeUser.setRecentOrderDate(new Date());
        int getOrder = partTimeUser.getGetOrderNumber() == null ? 0 : partTimeUser.getGetOrderNumber();
        partTimeUser.setGetOrderNumber(getOrder + 1);
        partUserMapper.updateByPrimaryKeySelective(partTimeUser);
        return 1;
    }
}
