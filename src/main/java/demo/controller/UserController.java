package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.model.UserinfoEntity;
import demo.model.dto.*;
import demo.service.SysRoleService;
import demo.service.UserinfoService;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/userManage")
public class UserController {
    @Resource
    private UserinfoService userService;


    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
    public Map<String, Object> queryAllEntity(UserReqVo vo) {
        Map<String, Object> mapOut = new HashMap<>();
        PageInfo<UserinfoEntity> partPageInfo = userService.selectAllUser(vo);
        mapOut.put("code", 0);
        mapOut.put("count", partPageInfo.getTotal());
        mapOut.put("data", partPageInfo.getList());
        return mapOut;
    }

    @RequestMapping(value = "/addEntity", method = RequestMethod.POST)
    public Map<String, Object> addEntity(@RequestBody UserAddVo user) {
        Map<String, Object> mapOut = new HashMap<>();
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsername())) {
            mapOut.put("code", -1);
            mapOut.put("message", "用户名和密码不能为空");
        } else {
            int flag = userService.registUser(user.getUsername(), user.getPassword(), "user", Long.valueOf(user.getRoleId()));
            if (flag == -100) {
                mapOut.put("code", -1);
                mapOut.put("message", "该用户名已存在");
            } else {
                mapOut.put("code", 1);
            }
        }
        return mapOut;
    }

    @RequestMapping(value = "/editEntity", method = RequestMethod.POST)
    public Map<String, Object> editEntity(@RequestBody UserAddVo user) {
        Map<String, Object> mapOut = new HashMap<>();
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsername())) {
            mapOut.put("code", -1);
            mapOut.put("message", "用户名和密码不能为空");
        } else {
            int flag = userService.editEntity(user);
            if (flag == -100) {
                mapOut.put("code", -1);
                mapOut.put("message", "该用户名已存在");
            } else {
                mapOut.put("code", 1);
            }
        }
        return mapOut;
    }

//    @RequestMapping(value = "/queryRole", method = RequestMethod.GET)
//    public Map<String, Object> queryRole() {
//        Map<String, Object> mapOut = new HashMap<>();
//        mapOut.put("code", 1);
//        mapOut.put("data", sysRoleService.queryRoleList());
//        return mapOut;
//    }

    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteEntity(@RequestParam Long uid) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", userService.deleteEntity(uid));
        return mapOut;
    }
}
