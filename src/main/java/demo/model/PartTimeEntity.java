package demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class PartTimeEntity {
    private Integer id;

    private String partQq;

    private String submitState;

    private String partPhone;

    private String partAlipay;

    private BigDecimal partMoney;

    private BigDecimal deduct;

    private Date settleDate;

    private String partRemark;

    private String partAudit;

    private String partSettleState;

    private String financeRemark;

    private String orderNumber;

    private String serviceName;

    private Long sendServiceId;

    private String sendServiceName;

    private String partMoneyReal;

    private Date createTime;

    private String partMoneyFinance;

    private String partAuditFinance;

    private String partSettleStateFinance;

    private String partFinanceRemark;

    private String partUserRemark;

    private Boolean isDelete;

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

    public BigDecimal getPartMoney() {
        return partMoney;
    }

    public void setPartMoney(BigDecimal partMoney) {
        this.partMoney = partMoney.stripTrailingZeros();
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct.stripTrailingZeros();
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

    public Long getSendServiceId() {
        return sendServiceId;
    }

    public void setSendServiceId(Long sendServiceId) {
        this.sendServiceId = sendServiceId;
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

    public String getPartMoneyFinance() {
        return partMoneyFinance;
    }

    public void setPartMoneyFinance(String partMoneyFinance) {
        this.partMoneyFinance = partMoneyFinance == null ? null : partMoneyFinance.trim();
    }

    public String getPartAuditFinance() {
        return partAuditFinance;
    }

    public void setPartAuditFinance(String partAuditFinance) {
        this.partAuditFinance = partAuditFinance == null ? null : partAuditFinance.trim();
    }

    public String getPartSettleStateFinance() {
        return partSettleStateFinance;
    }

    public void setPartSettleStateFinance(String partSettleStateFinance) {
        this.partSettleStateFinance = partSettleStateFinance == null ? null : partSettleStateFinance.trim();
    }

    public String getPartFinanceRemark() {
        return partFinanceRemark;
    }

    public void setPartFinanceRemark(String partFinanceRemark) {
        this.partFinanceRemark = partFinanceRemark == null ? null : partFinanceRemark.trim();
    }

    public String getPartUserRemark() {
        return partUserRemark;
    }

    public void setPartUserRemark(String partUserRemark) {
        this.partUserRemark = partUserRemark == null ? null : partUserRemark.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}