package demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.service_id
     *
     * @mbg.generated
     */
    private String serviceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.service_name
     *
     * @mbg.generated
     */
    private String serviceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.get_order_date
     *
     * @mbg.generated
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date getOrderDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.customer_im
     *
     * @mbg.generated
     */
    private String customerIm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.order_number
     *
     * @mbg.generated
     */
    private String orderNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.delivery_date
     *
     * @mbg.generated
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.customer_mail
     *
     * @mbg.generated
     */
    private String customerMail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.order_content
     *
     * @mbg.generated
     */
    private String orderContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.order_price
     *
     * @mbg.generated
     */
    private String orderPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.pay_state
     *
     * @mbg.generated
     */
    private String payState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.pay_progress
     *
     * @mbg.generated
     */
    private String payProgress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.due_date
     *
     * @mbg.generated
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date dueDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.due_money
     *
     * @mbg.generated
     */
    private String dueMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.recommend_im
     *
     * @mbg.generated
     */
    private String recommendIm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.customer_account
     *
     * @mbg.generated
     */
    private String customerAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.refund_way
     *
     * @mbg.generated
     */
    private String refundWay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.refund_money
     *
     * @mbg.generated
     */
    private String refundMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.refund_date
     *
     * @mbg.generated
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date refundDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.refund_remark
     *
     * @mbg.generated
     */
    private String refundRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.audit
     *
     * @mbg.generated
     */
    private String audit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oa_order.part_name
     *
     * @mbg.generated
     */
    private String partName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.id
     *
     * @return the value of oa_order.id
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.id
     *
     * @param id the value for oa_order.id
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.service_id
     *
     * @return the value of oa_order.service_id
     * @mbg.generated
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.service_id
     *
     * @param serviceId the value for oa_order.service_id
     * @mbg.generated
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.service_name
     *
     * @return the value of oa_order.service_name
     * @mbg.generated
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.service_name
     *
     * @param serviceName the value for oa_order.service_name
     * @mbg.generated
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.get_order_date
     *
     * @return the value of oa_order.get_order_date
     * @mbg.generated
     */
    public Date getGetOrderDate() {
        return getOrderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.get_order_date
     *
     * @param getOrderDate the value for oa_order.get_order_date
     * @mbg.generated
     */
    public void setGetOrderDate(Date getOrderDate) {
        this.getOrderDate = getOrderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.customer_im
     *
     * @return the value of oa_order.customer_im
     * @mbg.generated
     */
    public String getCustomerIm() {
        return customerIm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.customer_im
     *
     * @param customerIm the value for oa_order.customer_im
     * @mbg.generated
     */
    public void setCustomerIm(String customerIm) {
        this.customerIm = customerIm == null ? null : customerIm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.order_number
     *
     * @return the value of oa_order.order_number
     * @mbg.generated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.order_number
     *
     * @param orderNumber the value for oa_order.order_number
     * @mbg.generated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.delivery_date
     *
     * @return the value of oa_order.delivery_date
     * @mbg.generated
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.delivery_date
     *
     * @param deliveryDate the value for oa_order.delivery_date
     * @mbg.generated
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.customer_mail
     *
     * @return the value of oa_order.customer_mail
     * @mbg.generated
     */
    public String getCustomerMail() {
        return customerMail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.customer_mail
     *
     * @param customerMail the value for oa_order.customer_mail
     * @mbg.generated
     */
    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail == null ? null : customerMail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.order_content
     *
     * @return the value of oa_order.order_content
     * @mbg.generated
     */
    public String getOrderContent() {
        return orderContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.order_content
     *
     * @param orderContent the value for oa_order.order_content
     * @mbg.generated
     */
    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent == null ? null : orderContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.order_price
     *
     * @return the value of oa_order.order_price
     * @mbg.generated
     */
    public String getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.order_price
     *
     * @param orderPrice the value for oa_order.order_price
     * @mbg.generated
     */
    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice == null ? null : orderPrice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.pay_state
     *
     * @return the value of oa_order.pay_state
     * @mbg.generated
     */
    public String getPayState() {
        return payState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.pay_state
     *
     * @param payState the value for oa_order.pay_state
     * @mbg.generated
     */
    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.pay_progress
     *
     * @return the value of oa_order.pay_progress
     * @mbg.generated
     */
    public String getPayProgress() {
        return payProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.pay_progress
     *
     * @param payProgress the value for oa_order.pay_progress
     * @mbg.generated
     */
    public void setPayProgress(String payProgress) {
        this.payProgress = payProgress == null ? null : payProgress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.due_date
     *
     * @return the value of oa_order.due_date
     * @mbg.generated
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.due_date
     *
     * @param dueDate the value for oa_order.due_date
     * @mbg.generated
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.due_money
     *
     * @return the value of oa_order.due_money
     * @mbg.generated
     */
    public String getDueMoney() {
        return dueMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.due_money
     *
     * @param dueMoney the value for oa_order.due_money
     * @mbg.generated
     */
    public void setDueMoney(String dueMoney) {
        this.dueMoney = dueMoney == null ? null : dueMoney.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.remark
     *
     * @return the value of oa_order.remark
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.remark
     *
     * @param remark the value for oa_order.remark
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.recommend_im
     *
     * @return the value of oa_order.recommend_im
     * @mbg.generated
     */
    public String getRecommendIm() {
        return recommendIm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.recommend_im
     *
     * @param recommendIm the value for oa_order.recommend_im
     * @mbg.generated
     */
    public void setRecommendIm(String recommendIm) {
        this.recommendIm = recommendIm == null ? null : recommendIm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.customer_account
     *
     * @return the value of oa_order.customer_account
     * @mbg.generated
     */
    public String getCustomerAccount() {
        return customerAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.customer_account
     *
     * @param customerAccount the value for oa_order.customer_account
     * @mbg.generated
     */
    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount == null ? null : customerAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.refund_way
     *
     * @return the value of oa_order.refund_way
     * @mbg.generated
     */
    public String getRefundWay() {
        return refundWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.refund_way
     *
     * @param refundWay the value for oa_order.refund_way
     * @mbg.generated
     */
    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay == null ? null : refundWay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.refund_money
     *
     * @return the value of oa_order.refund_money
     * @mbg.generated
     */
    public String getRefundMoney() {
        return refundMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.refund_money
     *
     * @param refundMoney the value for oa_order.refund_money
     * @mbg.generated
     */
    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney == null ? null : refundMoney.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.refund_date
     *
     * @return the value of oa_order.refund_date
     * @mbg.generated
     */
    public Date getRefundDate() {
        return refundDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.refund_date
     *
     * @param refundDate the value for oa_order.refund_date
     * @mbg.generated
     */
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.refund_remark
     *
     * @return the value of oa_order.refund_remark
     * @mbg.generated
     */
    public String getRefundRemark() {
        return refundRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.refund_remark
     *
     * @param refundRemark the value for oa_order.refund_remark
     * @mbg.generated
     */
    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark == null ? null : refundRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.audit
     *
     * @return the value of oa_order.audit
     * @mbg.generated
     */
    public String getAudit() {
        return audit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.audit
     *
     * @param audit the value for oa_order.audit
     * @mbg.generated
     */
    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oa_order.part_name
     *
     * @return the value of oa_order.part_name
     * @mbg.generated
     */
    public String getPartName() {
        return partName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oa_order.part_name
     *
     * @param partName the value for oa_order.part_name
     * @mbg.generated
     */
    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }
}