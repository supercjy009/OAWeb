package demo.service.Imp;


import demo.mapper.SysUserroleEntityMapper;
import demo.mapper.UserinfoEntityMapper;
import demo.model.SysUserroleEntity;
import demo.model.UserinfoEntity;
import demo.service.UserinfoService;
import demo.util.MyDES;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class UserinfoServiceImp implements UserinfoService {
    @Resource
    private UserinfoEntityMapper userinfoEntityMapper;//这里会报错，但是并不会影响
    @Resource
    private SysUserroleEntityMapper sysUserroleEntityMapper;

    @Override
    public UserinfoEntity queryUserInfoByusername(String username) {
        try {
            return userinfoEntityMapper.queryUserInfoByusername(username);
        } catch (Exception ex) {
            System.out.println("queryUserInfoByusername has exception:" + ex.getMessage());
            return null;
        }

    }

    @Override
    public int registUser(String username, String password, String salt, Long roleId) {
        UserinfoEntity userinfoEntity = new UserinfoEntity();
        userinfoEntity.setName(username);
        //密码进行加密处理  明文为  password+盐
        String paw = password + salt;
        String pawDES = MyDES.encryptBasedDes(paw);
        userinfoEntity.setPassword(pawDES);
        userinfoEntity.setSalt(salt);
        userinfoEntity.setState((byte) 1);
        userinfoEntityMapper.insert(userinfoEntity);
        SysUserroleEntity sur = new SysUserroleEntity();
        sur.setUid(userinfoEntity.getUid());
        sur.setRoleId(roleId);
        return sysUserroleEntityMapper.insert(sur);
    }
}
