package demo.service.Imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.mapper.SysUserroleEntityMapper;
import demo.mapper.UserinfoEntityMapper;
import demo.model.PartTimeUser;
import demo.model.SysUserroleEntity;
import demo.model.UserinfoEntity;
import demo.model.dto.UserAddVo;
import demo.model.dto.UserReqVo;
import demo.service.UserinfoService;
import demo.util.MyDES;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    @Transactional
    public int registUser(String username, String password, String salt, Long roleId) {
        UserinfoEntity user = userinfoEntityMapper.queryUserInfoByusername(username);
        if (user != null) {
            return -100;
        }
        UserinfoEntity userinfoEntity = new UserinfoEntity();
        userinfoEntity.setName(username);
        //密码进行加密处理  明文为  password+盐
        String paw = password + salt;
        String pawDES = MyDES.encryptBasedDes(paw);
        userinfoEntity.setPassword(pawDES);
        userinfoEntity.setSalt(salt);
        userinfoEntity.setState((byte) 1);
        userinfoEntity.setUsername(username);
        userinfoEntityMapper.insert(userinfoEntity);
        SysUserroleEntity sur = new SysUserroleEntity();
        sur.setUid(userinfoEntity.getUid());
        sur.setRoleId(roleId);
        return sysUserroleEntityMapper.insert(sur);
    }

    @Override
    public PageInfo<UserinfoEntity> selectAllUser(UserReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        List<UserinfoEntity> partUserList = userinfoEntityMapper.selectAllUser();

        return new PageInfo<>(partUserList);
    }

    @Override
    public int editEntity(UserAddVo user) {
        String paw = user.getPassword() + user.getSalt();
        String pawDES = MyDES.encryptBasedDes(paw);
        user.setPassword(pawDES);
        userinfoEntityMapper.updateByPrimaryKeySelective(user);
        List<SysUserroleEntity> userroleEntities = sysUserroleEntityMapper.queryUserroleByuserid(user.getUid());
        for (SysUserroleEntity userrole : userroleEntities) {
            userrole.setRoleId(Long.valueOf(user.getRoleId()));
            sysUserroleEntityMapper.updateByPrimaryKey(userrole);
        }
        return 0;
    }

    @Override
    public int deleteEntity(Long uid) {
        //删除用户角色关系
        sysUserroleEntityMapper.deleteByuserid(uid);
        return userinfoEntityMapper.deleteByPrimaryKey(uid);
    }
}
