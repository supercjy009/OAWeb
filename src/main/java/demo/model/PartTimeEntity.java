package demo.model;

import java.util.Date;

public class PartTimeEntity {
    private Integer id;

    private String partQq;

    private String submitState;

    private String partPhone;

    private String partAlipay;

    private String partMoney;

    private String deduct;

    private Date settleDate;

    private String partRemark;

    private String partAudit;

    private String partSettleState;

    private String financeRemark;

    private String orderNumber;

    private String serviceName;

    private String sendServiceName;

    private String partMoneyReal;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getSendServiceName() {
        return sendServiceName;
    }

    public void setSendServiceName(String sendServiceName) {
        this.sendServiceName = sendServiceName == null ? null : sendServiceName.trim();
    }

    public String getPartMoneyReal() {
        return partMoneyReal;
    }

    public void setPartMoneyReal(String partMoneyReal) {
        this.partMoneyReal = partMoneyReal == null ? null : partMoneyReal.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}