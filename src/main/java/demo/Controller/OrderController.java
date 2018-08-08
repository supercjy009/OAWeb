package demo.Controller;

import com.github.pagehelper.PageInfo;
import demo.dto.OrderReqVo;
import demo.dto.OrderResDto;
import demo.dto.PartTimeOrderRes;
import demo.dto.WorkPayReqVo;
import demo.model.OrderEntity;
import demo.model.WorkPayEntity;
import demo.service.Imp.SysPermissionSerivceImp;
import demo.service.OrderService;
import demo.service.WorkPayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/order")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    WorkPayService workService;

    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
    public Map<String, Object> queryAllOrder(OrderReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<PartTimeOrderRes> orderPageInfo = orderService.queryAllOrder(vo);
        mapOut.put("code", 0);
//        PageInfo<WorkPayEntity> orderPageInfo = workService.queryAllOrder(vo);
        mapOut.put("count", orderPageInfo.getTotal());
        mapOut.put("data", orderPageInfo.getList());
        mapOut.put("data", orderPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map<String, Object> addOrder(@RequestBody OrderEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.addOrder(order));
        return mapOut;
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public Map<String, Object> updateOrder(@RequestBody OrderEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.updateOrder(order));
        return mapOut;
    }
}
