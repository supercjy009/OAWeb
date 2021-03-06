package demo.model.dto;

import java.util.Date;

public class PartUserReqVo {
    private Integer page;
    private Integer limit;
    private String flag;
    private String recentDate;
    private Integer getOrderNumber;
    private Date recentDateStart;
    private Date recentDateEnd;
    private Boolean masterHand;
    private Boolean referrer;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getRecentDate() {
        return recentDate;
    }

    public void setRecentDate(String recentDate) {
        this.recentDate = recentDate;
    }

    public Date getRecentDateStart() {
        return recentDateStart;
    }

    public void setRecentDateStart(Date recentDateStart) {
        this.recentDateStart = recentDateStart;
    }

    public Date getRecentDateEnd() {
        return recentDateEnd;
    }

    public void setRecentDateEnd(Date recentDateEnd) {
        this.recentDateEnd = recentDateEnd;
    }

    public Boolean getMasterHand() {
        return masterHand;
    }

    public void setMasterHand(Boolean masterHand) {
        this.masterHand = masterHand;
    }

    public Boolean getReferrer() {
        return referrer;
    }

    public void setReferrer(Boolean referrer) {
        this.referrer = referrer;
    }

    public Integer getGetOrderNumber() {
        return getOrderNumber;
    }

    public void setGetOrderNumber(Integer getOrderNumber) {
        this.getOrderNumber = getOrderNumber;
    }
}
