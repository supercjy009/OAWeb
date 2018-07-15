package demo.service;

import demo.model.SysRolepermissionEntity;

import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
public interface SysRolepermissionService {
    List<SysRolepermissionEntity> queryListSysRolepermissionEntityByroleid(Long roleid);
}
