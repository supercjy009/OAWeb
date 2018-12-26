package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.model.dto.AuditVo;
import demo.model.dto.WorkPayReqVo;
import demo.model.WorkPayEntity;
import demo.service.WorkPayService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/workpay")
public class WorkPayController {
    @Resource
    WorkPayService workService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM").format((Date) getValue());
            }

        });
    }

    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
    public Map<String, Object> queryAllOrder(WorkPayReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<WorkPayEntity> workPayPageInfo = workService.queryAllOrder(vo);
        mapOut.put("code", 0);
        mapOut.put("count", workPayPageInfo.getTotal());
        mapOut.put("data", workPayPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map<String, Object> addOrder(@RequestBody WorkPayEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", workService.addOrder(order));
        return mapOut;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public Map<String, Object> updateOrder(@RequestBody WorkPayEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", workService.updateOrder(order));
        return mapOut;
    }

    @RequestMapping(value = "/auditOrder", method = RequestMethod.POST)
    public Map<String, Object> auditOrder(@RequestBody AuditVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", workService.auditOrder(vo));
        return mapOut;
    }

    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteEntity(@RequestParam Long[] ids) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", workService.deleteEntity(ids));
        return mapOut;
    }

    @RequestMapping(value = "/selectAllService", method = RequestMethod.GET)
    public Map<String, Object> selectAllService(@RequestParam(defaultValue = "") String serviceName,
                                                @RequestParam(defaultValue = "") String partName) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        mapOut.put("data", workService.selectAllService(serviceName, partName));
        return mapOut;
    }
}
