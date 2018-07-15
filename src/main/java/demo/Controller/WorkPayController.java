package demo.Controller;

import com.github.pagehelper.PageInfo;
import demo.dto.AuditVo;
import demo.dto.WorkPayReqVo;
import demo.model.OrderEntity;
import demo.model.WorkPayEntity;
import demo.service.OrderService;
import demo.service.WorkPayService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
}
