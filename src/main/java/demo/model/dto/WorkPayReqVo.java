package demo.model.dto;

import java.util.Date;

/**
 * Created by p51 on 2018/6/5.
 */
public class WorkPayReqVo extends BaseReqDto{
    private String partName;
    private Integer page;
    private Integer limit;
    private String payDate;//支出时间
    private Date payDateStart;
    private Date payDateEnd;
    private String getUser;
    private String audit;
    private String settle;

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

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getGetUser() {
        return getUser;
    }

    public void setGetUser(String getUser) {
        this.getUser = getUser;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Date getPayDateStart() {
        return payDateStart;
    }

    public void setPayDateStart(Date payDateStart) {
        this.payDateStart = payDateStart;
    }

    public Date getPayDateEnd() {
        return payDateEnd;
    }

    public void setPayDateEnd(Date payDateEnd) {
        this.payDateEnd = payDateEnd;
    }
}
