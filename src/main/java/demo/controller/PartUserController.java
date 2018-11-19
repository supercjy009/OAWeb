package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.dto.PartUserAddVo;
import demo.dto.PartUserReqVo;
import demo.dto.SettleDateVo;
import demo.model.PartTimeUser;
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
import java.util.List;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/partUser")
public class PartUserController {
    @Resource
    PartUserService partUserService;

    @Resource
    UserinfoService userinfoService;

    private static String SALT_STRING = "part";

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
    public Map<String, Object> queryAllEntity(PartUserReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<PartTimeUser> workPayPageInfo = partUserService.queryAllOrder(vo);
        mapOut.put("code", 0);
        mapOut.put("count", workPayPageInfo.getTotal());
        mapOut.put("data", workPayPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addEntity", method = RequestMethod.POST)
    public Map<String, Object> addEntity(@RequestBody PartUserAddVo user) {
        Map<String, Object> mapOut = new HashMap<>();
        if (StringUtils.isEmpty(user.getPassWord()) || StringUtils.isEmpty(user.getPartQq())) {
            mapOut.put("code", -1);
            mapOut.put("message", "qq和密码不能为空");
        } else {
            String passWord = user.getPassWord();
            userinfoService.registUser(user.getPartQq(), passWord, SALT_STRING, SystemConstant.PART_ROLE_ID);
            mapOut.put("code", partUserService.addEntity(user));
        }
        return mapOut;
    }

    @RequestMapping(value = "/editEntity", method = RequestMethod.POST)
    public Map<String, Object> updateEntity(@RequestBody PartUserAddVo user) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partUserService.updateEntity(user));
        return mapOut;
    }

    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteEntity(@RequestParam Long id) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partUserService.deleteEntity(id));
        return mapOut;
    }

    @RequestMapping(value = "/addSettleDate", method = RequestMethod.POST)
    public Map<String, Object> addSettleDate(@RequestBody SettleDateVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", partUserService.addSettleDate(vo));
        return mapOut;
    }
}
