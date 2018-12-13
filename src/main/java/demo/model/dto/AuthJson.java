package demo.model.dto;

import java.util.List;

public class AuthJson {
    private String name;
    private Long value;
    private Long parentId;
    private Boolean checked = false;
    private Boolean disabled = false;
    private List<AuthJson> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        //是否勾选了
        this.checked = checked != null;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public void setAvailable(Boolean available) {
        this.disabled = !available;
    }

    public List<AuthJson> getList() {
        return list;
    }

    public void setList(List<AuthJson> list) {
        this.list = list;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
