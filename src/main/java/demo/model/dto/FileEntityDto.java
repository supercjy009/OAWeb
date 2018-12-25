package demo.model.dto;

import demo.model.FileEntity;

public class FileEntityDto extends FileEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
