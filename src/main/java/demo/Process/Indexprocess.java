package demo.Process;

import demo.model.UserinfoEntity;
import demo.service.Imp.SysPermissionSerivceImp;
import demo.service.SysRoleService;
import demo.service.SysRolepermissionService;
import demo.service.SysUserroleService;
import demo.service.UserinfoService;
import demo.shiro.MyShiroRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PC on 2018/1/2.
 */
@Service
public class Indexprocess {
    Logger logger = LoggerFactory.getLogger(Indexprocess.class);
    @Resource
    UserinfoService userinfoService;
    @Resource
    SysUserroleService sysUserroleService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysRolepermissionService sysRolepermissionService;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    @Resource
    MyShiroRealm myShiroRealm;
    public String clearUserPermission(String strUsername){
        try{
            List<Long> li = new ArrayList<Long>();
            UserinfoEntity userinfoEntity = userinfoService.queryUserInfoByusername(strUsername);
            if(null != userinfoEntity){
                li.add(userinfoEntity.getUid());
            }else{
                return "fail";
            }
            myShiroRealm.clearUserAuthByUserId(li);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return "fail";
        }
        return "success";
    }
}
