package demo.service;

import demo.model.OrderEntity;

import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface PartTimeService {
    List<OrderEntity> queryAllOrder(String partName);

    int addOrder(OrderEntity order);

    int updateOrder(OrderEntity order);

}
