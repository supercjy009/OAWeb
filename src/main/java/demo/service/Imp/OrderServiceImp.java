package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.dto.*;
import demo.mapper.OrderEntityMapper;
import demo.model.OrderEntity;
import demo.model.UserinfoEntity;
import demo.model.WorkPayEntity;
import demo.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
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
}
