//package demo.service.Imp;
//
//import demo.mapper.OrderEntityMapper;
//import demo.model.OrderEntity;
//import demo.model.UserinfoEntity;
//import demo.service.OrderService;
//import demo.service.PartTimeService;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by p51 on 2018/5/16.
// */
//@Service
//public class PartTimeServiceImp implements PartTimeService {
//    @Resource
//    OrderEntityMapper orderEntityMapper;
//
//    @Override
//    public List<OrderEntity> queryAllOrder(String partName) {
//        return orderEntityMapper.selectAllOrder(null);
//    }
//
//    @Override
//    public int addOrder(OrderEntity order) {
//        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
//        return orderEntityMapper.insert(order);
//    }
//
//    @Override
//    public int updateOrder(OrderEntity order) {
//        return orderEntityMapper.updateByPrimaryKey(order);
//    }
//}
