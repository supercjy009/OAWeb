package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.model.PartTimeEntity;
import demo.model.dto.*;
import demo.model.PartTimeUser;
import demo.service.PartTimeService;
import demo.service.PartUserService;
import demo.service.UserinfoService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

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
@RequestMapping(value = "/webAjax/partOrder")
public class PartOrderController {
    @Resource
    PartTimeService partService;

    @Resource
    UserinfoService userinfoService;

    private static String SALT_STRING = "part";

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
                return new SimpleDateFormat("yyyy-MM").format((Date) getValue());
            }

        });
    }

    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
    public Map<String, Object> queryAllEntity(PartOrderReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<PartTimeDto> partPageInfo = partService.queryAllOrder(vo);
        mapOut.put("code", 0);
        mapOut.put("count", partPageInfo.getTotal());
        mapOut.put("data", partPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/editEntity", method = RequestMethod.POST)
    public Map<String, Object> updateEntity(@RequestBody PartTimeEntity entity) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partService.updateOrder(entity));
        return mapOut;
    }

    @RequestMapping(value = "/auditOrder", method = RequestMethod.POST)
    public Map<String, Object> auditOrder(@RequestBody AuditVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partService.auditOrder(vo));
        return mapOut;
    }

    @RequestMapping(value = "/addSettleDate", method = RequestMethod.POST)
    public Map<String, Object> addSettleDate(@RequestBody SettleDateVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partService.addSettleDate(vo));
        return mapOut;
    }
}
