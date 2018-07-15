package demo.service;

import demo.model.SysRoleEntity;

/**
 * Created by PC on 2017/12/2.
 */
public interface SysRoleService {
    SysRoleEntity queryRoleByUserid(Long userid);
}
