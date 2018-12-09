package demo.model.dto;

import demo.model.PartTimeUser;
import demo.model.UserinfoEntity;

public class UserAddVo extends UserinfoEntity {
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
