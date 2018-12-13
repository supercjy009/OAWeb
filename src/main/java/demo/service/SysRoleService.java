package demo.service;

import demo.model.SysRoleEntity;

import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
public interface SysRoleService {
    SysRoleEntity queryRoleByUserid(Long userid);

    List<SysRoleEntity> queryRoleList();

    int addRole(String description);

    int editRole(Long id, String description);

    int deleteRole(Long id);
}
