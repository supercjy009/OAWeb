package demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PartOrderReqVo {
    private String partAudit;
    private String partSettleState;
    private String moneyState;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date settleDate;
    private String keyWord;
    private Integer page;
    private Integer limit;


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

    public String getPartAudit() {
        return partAudit;
    }

    public void setPartAudit(String partAudit) {
        this.partAudit = partAudit;
    }

    public String getPartSettleState() {
        return partSettleState;
    }

    public void setPartSettleState(String partSettleState) {
        this.partSettleState = partSettleState;
    }

    public String getMoneyState() {
        return moneyState;
    }

    public void setMoneyState(String moneyState) {
        this.moneyState = moneyState;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}
