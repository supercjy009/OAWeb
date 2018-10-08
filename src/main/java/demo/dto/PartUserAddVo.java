package demo.dto;

import demo.model.PartTimeUser;

public class PartUserAddVo extends PartTimeUser {
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
