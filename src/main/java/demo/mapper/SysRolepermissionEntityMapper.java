package demo.mapper;

import demo.model.SysRolepermissionEntity;

import java.util.List;

public interface SysRolepermissionEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbggenerated
     */
    int insert(SysRolepermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbggenerated
     */
    int insertSelective(SysRolepermissionEntity record);

    List<SysRolepermissionEntity> queryListSysRolepermissionEntityByroleid(Long roleid);
}