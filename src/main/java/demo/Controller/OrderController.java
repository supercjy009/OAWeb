package demo.Controller;

import com.github.pagehelper.PageInfo;
import demo.dto.*;
import demo.model.OrderEntity;
import demo.model.WorkPayEntity;
import demo.service.Imp.SysPermissionSerivceImp;
import demo.service.OrderService;
import demo.service.WorkPayService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
            }

        });
    }

    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
    public Map<String, Object> queryAllOrder(OrderReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<PartTimeOrderRes> orderPageInfo = null;
        try {
            orderPageInfo = orderService.queryAllOrder(vo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    @RequestMapping(value = "/auditOrder", method = RequestMethod.POST)
    public Map<String, Object> auditOrder(@RequestBody AuditVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.auditOrder(vo));
        return mapOut;
    }
}
