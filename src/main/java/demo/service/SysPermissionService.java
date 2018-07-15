package demo.service;


import demo.model.SysPermissionEntity;

import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
public interface SysPermissionService {
    SysPermissionEntity selectByPrimaryKey(Long id);
    List<SysPermissionEntity> queryAllPermission();
    int insertSelective(SysPermissionEntity sysPermissionEntity);
    int deleteByPrimaryKey(Long id);
}
