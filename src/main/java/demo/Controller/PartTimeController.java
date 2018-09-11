package demo.Controller;

import com.github.pagehelper.PageInfo;
import demo.dto.PartUserVo;
import demo.model.PartTimeUser;
import demo.service.PartUserService;
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
@RequestMapping(value = "/webAjax/partUser")
public class PartTimeController {
    @Resource
    PartUserService partUserService;

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
    public Map<String, Object> queryAllEntity(PartUserVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<PartTimeUser> workPayPageInfo = partUserService.queryAllOrder(vo);
        mapOut.put("code", 0);
        mapOut.put("count", workPayPageInfo.getTotal());
        mapOut.put("data", workPayPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addEntity", method = RequestMethod.POST)
    public Map<String, Object> addEntity(@RequestBody PartTimeUser user) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partUserService.addEntity(user));
        return mapOut;
    }

    @RequestMapping(value = "/editEntity", method = RequestMethod.POST)
    public Map<String, Object> updateEntity(@RequestBody PartTimeUser user) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partUserService.updateEntity(user));
        return mapOut;
    }
}
