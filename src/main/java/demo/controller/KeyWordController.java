package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.model.dto.KeyWordReqVo;
import demo.model.KeyWordEntity;
import demo.service.KeyWordService;
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
@RequestMapping(value = "/webAjax/keyWord")
public class KeyWordController {
    @Resource
    KeyWordService entityService;

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
    public Map<String, Object> queryAllOrder(KeyWordReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<KeyWordEntity> workPayPageInfo = entityService.queryAllOrder(vo);
        mapOut.put("code", 0);
        mapOut.put("count", workPayPageInfo.getTotal());
        mapOut.put("data", workPayPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map<String, Object> addOrder(@RequestBody KeyWordEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.addOrder(order));
        return mapOut;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public Map<String, Object> updateOrder(@RequestBody KeyWordEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.updateOrder(order));
        return mapOut;
    }


    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteEntity(@RequestParam Long id) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.deleteEntity(id));
        return mapOut;
    }
}
