package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.OrderResDto;
import demo.dto.WorkPayReqVo;
import demo.model.OrderEntity;

import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface OrderService {
    PageInfo<OrderResDto> queryAllOrder(WorkPayReqVo vo);

    int addOrder(OrderEntity order);

    int updateOrder(OrderEntity order);

}
