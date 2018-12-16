package demo.controller;

import demo.model.SysRoleEntity;
import demo.model.SysUserroleEntity;
import demo.model.UserinfoEntity;
import demo.service.Imp.SysPermissionSerivceImp;
import demo.service.Imp.SysUserroleServiceImp;
import demo.service.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/roleManage")
public class RoleController {
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    SysUserroleServiceImp sysUserroleServiceImp;

    @RequestMapping(value = "/queryPermissionAll", method = RequestMethod.GET)
    public Map<String, Object> queryPermissionAll() {
        Map<String, Object> mapOut = new HashMap<>();
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        List<SysUserroleEntity> sysUserroleEntities = sysUserroleServiceImp.queryUserroleByuserid(userinfoEntity.getUid());
        if (sysUserroleEntities == null) {
            mapOut.put("code", -1005);
        } else {
            Long roleId = sysUserroleEntities.get(0).getRoleId();
            mapOut.put("code", 1);
            mapOut.put("username", userinfoEntity.getUsername());
            mapOut.put("permission", sysPermissionSerivceImp.selectPermissionListByRoleId(roleId));
        }
        return mapOut;
    }

    @RequestMapping(value = "/queryPermission", method = RequestMethod.GET)
    public Map<String, Object> queryPermission(@RequestParam Long roleId) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        Map<String, Object> trees = new HashMap<>();
        trees.put("trees", sysPermissionSerivceImp.queryPermission(roleId));
        mapOut.put("data", trees);
        return mapOut;
    }

    @RequestMapping(value = "/setPermission", method = RequestMethod.GET)
    public Map<String, Object> setPermission(@RequestParam Integer flag, @RequestParam Long roleId, @RequestParam Long permissionId) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        Map<String, Object> trees = new HashMap<>();
        trees.put("trees", sysPermissionSerivceImp.setPermission(flag, roleId, permissionId));
        mapOut.put("data", trees);
        return mapOut;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public Map<String, Object> addRole(@RequestParam String description) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", sysRoleService.addRole(description));
        return mapOut;
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public Map<String, Object> editRole(@RequestBody SysRoleEntity roleEntity) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", sysRoleService.editRole(roleEntity.getId(), roleEntity.getDescription()));
        return mapOut;
    }

    @RequestMapping(value = "/queryRole", method = RequestMethod.GET)
    public Map<String, Object> queryRole() {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        mapOut.put("data", sysRoleService.queryRoleList());
        return mapOut;
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
    public Map<String, Object> deleteRole(@RequestParam Long id) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", sysRoleService.deleteRole(id));
        return mapOut;
    }
//
//    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
//    public Map<String, Object> deleteEntity(@RequestParam Long uid) {
//        Map<String, Object> mapOut = new HashMap<>();
//        mapOut.put("code", userService.deleteEntity(uid));
//        return mapOut;
//    }
}
