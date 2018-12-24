package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.model.SysRoleEntity;
import demo.model.dto.*;
import demo.mapper.PayProgressMapper;
import demo.model.PayProgress;
import demo.service.OrderService;
import demo.service.WorkPayService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    PayProgressMapper progressMapper;

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
        mapOut.put("count", orderPageInfo.getTotal());
        mapOut.put("data", orderPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/viewProgress", method = RequestMethod.POST)
    public Map<String, Object> viewProgress(Long id) {
        Map<String, Object> mapOut = new HashMap<>();
        List<PayProgress> progressList = progressMapper.selectByOrderId(id);
        mapOut.put("code", 1);
        mapOut.put("data", progressList);
        return mapOut;
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map<String, Object> addOrder(@RequestBody OrderVo order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.addOrder(order));
        return mapOut;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public Map<String, Object> updateOrder(@RequestBody OrderVo order) {
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

    @RequestMapping(value = "/appointPart", method = RequestMethod.POST)
    public Map<String, Object> appointPart(@RequestBody AppointPartVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.appointPart(vo));
        return mapOut;
    }

    @RequestMapping(value = "/editPart", method = RequestMethod.POST)
    public Map<String, Object> editPart(@RequestBody AppointPartVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.editPart(vo));
        return mapOut;
    }

    @RequestMapping(value = "/deletePart", method = RequestMethod.POST)
    public Map<String, Object> deletePart(@RequestBody AppointPartVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.deletePart(vo));
        return mapOut;
    }

    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteOrder(@RequestParam Long[] ids) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.deleteOrder(ids));
        return mapOut;
    }

    @RequestMapping(value = "/editFinaRemark", method = RequestMethod.POST)
    public Map<String, Object> editRole(@RequestBody RemarkVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", orderService.editFinaRemark(vo));
        return mapOut;
    }

    @RequestMapping(value = "/selectAllService", method = RequestMethod.GET)
    public Map<String, Object> selectAllService(@RequestParam(defaultValue = "") String serviceName,
                                                @RequestParam(defaultValue = "") String partName, @RequestParam String flag) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        mapOut.put("data", orderService.selectAllService(serviceName, flag, partName));
        return mapOut;
    }


}
