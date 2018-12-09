package demo.model.dto;

import demo.model.UserinfoEntity;
import demo.util.MyDES;

public class UserReqDto extends UserinfoEntity {
    private String passwordStr;
    private String roleDescription;

    public String getPasswordStr() {
        return passwordStr;
    }

    public void setPasswordStr(String passwordStr) {
        this.passwordStr = MyDES.decrypPassword(passwordStr, this.getSalt());
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
