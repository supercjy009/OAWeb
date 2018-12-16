package demo.service.Imp;

import demo.mapper.SysPermissionEntityMapper;
import demo.mapper.SysRolepermissionEntityMapper;
import demo.model.SysPermissionEntity;
import demo.model.SysRolepermissionEntity;
import demo.model.dto.AuthJson;
import demo.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysPermissionSerivceImp implements SysPermissionService {
    @Resource
    SysPermissionEntityMapper sysPermissionEntityMapper;
    @Resource
    SysRolepermissionEntityMapper rolePermissionMapper;

    @Override
    public SysPermissionEntity selectByPrimaryKey(Long id) {
        return sysPermissionEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermissionEntity> queryAllPermission() {
        return sysPermissionEntityMapper.selectAllPermission();
    }

    @Override
    @Transactional
    public int insertSelective(SysPermissionEntity sysPermissionEntity) {
        deleteByPrimaryKey(1L);
        return sysPermissionEntityMapper.insert(sysPermissionEntity);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysPermissionEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AuthJson> queryPermission(Long roleId) {
        //第一级
        List<AuthJson> parent = sysPermissionEntityMapper.queryPermission((long) 0, roleId);
        for (AuthJson authJson : parent) {
            //第二级
            List<AuthJson> child = sysPermissionEntityMapper.queryPermission(authJson.getValue(), roleId);
            for (AuthJson ch : child) {
                if (ch.getChecked()) {
                    authJson.setChecked(1);
                    break;
                }
            }
            authJson.setList(child);
        }
        return parent;
    }

    @Override
    public int setPermission(Integer flag, Long roleId, Long permissionId) {
        SysRolepermissionEntity sysRolepermissionEntity;
        if (flag == 1) {
            sysRolepermissionEntity = new SysRolepermissionEntity();
            sysRolepermissionEntity.setRoleId(roleId);
            sysRolepermissionEntity.setPermissionId(permissionId);
            return rolePermissionMapper.insert(sysRolepermissionEntity);
        } else if ((flag == -1)) {
            return rolePermissionMapper.deleteRolePermissionById(roleId, permissionId);
        } else {
            return -1;
        }
    }

    @Override
    public List<SysPermissionEntity> queryPermissionByRoleId(Long roleId) {
        return sysPermissionEntityMapper.selectPermissionByRoleId(roleId);
    }

    @Override
    public List<String> selectPermissionListByRoleId(Long roleId) {
        return sysPermissionEntityMapper.selectPermissionListByRoleId(roleId);
    }
}
