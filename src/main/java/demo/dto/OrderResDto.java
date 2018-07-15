package demo.dto;

import demo.model.OrderEntity;
import demo.model.PartTimeEntity;

import java.util.List;

/**
 * Created by p51 on 2018/7/15.
 */
public class OrderResDto extends OrderEntity {
    //    private OrderEntity order;
    private List<PartTimeEntity> partTimes;

//    public OrderEntity getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderEntity order) {
//        this.order = order;
//    }

    public List<PartTimeEntity> getPartTimes() {
        return partTimes;
    }

    public void setPartTimes(List<PartTimeEntity> partTimes) {
        this.partTimes = partTimes;
    }
}
