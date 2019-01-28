package demo.model.dto;

public class BaseReqDto {
    private Long uid;
    private Boolean seeAll;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Boolean getSeeAll() {
        return seeAll;
    }

    public void setSeeAll(Boolean seeAll) {
        this.seeAll = seeAll;
    }
}
