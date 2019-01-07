package demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import demo.model.OrderEntity;

import java.util.Date;

/**
 * Created by p51 on 2018/7/27.
 */
public class PartTimeOrderRes extends OrderEntity {
    private Long partId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date payDate; //最新付款进度的日期

    private String partQq;

    private String submitState;

    private String partPhone;

    private String partAlipay;

    private String partMoney;

    private String deduct;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date settleDate;

    private String partRemark;

    private String partAudit;

    private String partSettleState;

    private String financeRemark;

    private String sendServiceName;

    private Boolean masterHand;

    private String masterHandStr;

    public String getPartQq() {
        return partQq;
    }

    public void setPartQq(String partQq) {
        this.partQq = partQq == null ? null : partQq.trim();
    }

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState == null ? null : submitState.trim();
    }

    public String getPartPhone() {
        return partPhone;
    }

    public void setPartPhone(String partPhone) {
        this.partPhone = partPhone == null ? null : partPhone.trim();
    }

    public String getPartAlipay() {
        return partAlipay;
    }

    public void setPartAlipay(String partAlipay) {
        this.partAlipay = partAlipay == null ? null : partAlipay.trim();
    }

    public String getPartMoney() {
        return partMoney;
    }

    public void setPartMoney(String partMoney) {
        this.partMoney = partMoney == null ? null : partMoney.trim();
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct == null ? null : deduct.trim();
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getPartRemark() {
        return partRemark;
    }

    public void setPartRemark(String partRemark) {
        this.partRemark = partRemark == null ? null : partRemark.trim();
    }

    public String getPartAudit() {
        return partAudit;
    }

    public void setPartAudit(String partAudit) {
        this.partAudit = partAudit == null ? null : partAudit.trim();
    }

    public String getPartSettleState() {
        return partSettleState;
    }

    public void setPartSettleState(String partSettleState) {
        this.partSettleState = partSettleState == null ? null : partSettleState.trim();
    }

    public String getFinanceRemark() {
        return financeRemark;
    }

    public void setFinanceRemark(String financeRemark) {
        this.financeRemark = financeRemark == null ? null : financeRemark.trim();
    }


    public String getSendServiceName() {
        return sendServiceName;
    }

    public void setSendServiceName(String sendServiceName) {
        this.sendServiceName = sendServiceName == null ? null : sendServiceName.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Boolean getMasterHand() {
        return masterHand;
    }

    public void setMasterHand(Boolean masterHand) {
        this.masterHand = masterHand;
        this.masterHandStr = masterHand ? "是" : "否";
    }

    public String getMasterHandStr() {
        return masterHandStr;
    }

    public void setMasterHandStr(String masterHandStr) {
        this.masterHandStr = masterHandStr;
    }
}
