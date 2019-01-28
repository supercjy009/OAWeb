package demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderEntity {

    private Long id;


    private String serviceId;


    private String serviceName;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date getOrderDate;


    private String customerIm;


    private String orderNumber;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date deliveryDate;


    private String customerMail;


    private String orderContent;


    private BigDecimal orderPrice;

    private String payState;

    private String payProgress;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date dueDate;

    private String dueMoney;

    private String remark;

    private String recommendIm;

    private String customerAccount;

    private String refundWay;

    private BigDecimal refundMoney;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date refundDate;

    private String refundRemark;

    private String audit;

    private String partName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public Date getGetOrderDate() {
        return getOrderDate;
    }

    public void setGetOrderDate(Date getOrderDate) {
        this.getOrderDate = getOrderDate;
    }

    public String getCustomerIm() {
        return customerIm;
    }

    public void setCustomerIm(String customerIm) {
        this.customerIm = customerIm == null ? null : customerIm.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail == null ? null : customerMail.trim();
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent == null ? null : orderContent.trim();
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice == null ? null : orderPrice.stripTrailingZeros();
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    public String getPayProgress() {
        return payProgress;
    }

    public void setPayProgress(String payProgress) {
        this.payProgress = payProgress == null ? null : payProgress.trim();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueMoney() {
        return dueMoney;
    }

    public void setDueMoney(String dueMoney) {
        this.dueMoney = dueMoney == null ? null : dueMoney.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRecommendIm() {
        return recommendIm;
    }

    public void setRecommendIm(String recommendIm) {
        this.recommendIm = recommendIm == null ? null : recommendIm.trim();
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount == null ? null : customerAccount.trim();
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay == null ? null : refundWay.trim();
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney == null ? null : refundMoney.stripTrailingZeros();
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark == null ? null : refundRemark.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }
}