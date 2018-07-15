package demo.service;

import demo.model.SysUserroleEntity;

import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
public interface SysUserroleService {
    List<SysUserroleEntity> queryUserroleByuserid(Long userid);
}
