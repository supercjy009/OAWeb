package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.dto.OrderReqVo;
import demo.dto.OrderResDto;
import demo.dto.PartTimeOrderRes;
import demo.dto.WorkPayReqVo;
import demo.mapper.OrderEntityMapper;
import demo.model.OrderEntity;
import demo.model.UserinfoEntity;
import demo.model.WorkPayEntity;
import demo.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public PageInfo<PartTimeOrderRes> queryAllOrder(OrderReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
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
}
