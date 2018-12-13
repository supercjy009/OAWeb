package demo.service.Imp;

import demo.mapper.SysPermissionEntityMapper;
import demo.mapper.SysRoleEntityMapper;
import demo.mapper.SysRolepermissionEntityMapper;
import demo.model.SysRoleEntity;
import demo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysRoleServiceImp implements SysRoleService {
    @Resource
    private SysRoleEntityMapper sysRoleEntityMapper;

    @Resource
    SysRolepermissionEntityMapper rolePermissionMapper;

    @Override
    public SysRoleEntity queryRoleByUserid(Long userid) {
        return sysRoleEntityMapper.selectByPrimaryKey(userid);
    }

    @Override
    public List<SysRoleEntity> queryRoleList() {
        return sysRoleEntityMapper.selectAllRole();
    }

    @Override
    public int addRole(String description) {
        SysRoleEntity roleQuery = sysRoleEntityMapper.selectByDescription(description);
        if (roleQuery != null) {
            return -100;
        }
        SysRoleEntity role = new SysRoleEntity();
        role.setDescription(description);
        role.setRole(description);
        role.setAvailable(true);
        role.setCreateDate(new Date());
        return sysRoleEntityMapper.insert(role);
    }

    @Override
    public int editRole(Long id, String description) {
        SysRoleEntity roleQuery = sysRoleEntityMapper.selectByDescription(description);
        if (roleQuery != null) {
            return -100;
        }
        SysRoleEntity role = new SysRoleEntity();
        role.setId(id);
        role.setDescription(description);
        role.setRole(description);
        return sysRoleEntityMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteRole(Long id) {
        rolePermissionMapper.deleteByRoleId(id);
        return sysRoleEntityMapper.deleteByPrimaryKey(id);
    }
}
