package demo.dto;

import java.util.Date;

/**
 * Created by p51 on 2018/7/25.
 */
public class OrderReqVo {
    private String orderDateReq; //接单时间(最近七天、当月等等)
    private Date orderDateStart;
    private Date orderDateEnd;
    private String deliveryDateReq; //交稿时间
    private Date deliveryDateStart;
    private Date deliveryDateEnd;
    private String payState;
    private String submitState;
    private String partInfo;
    private String serviceName;
    private String sendServiceName;
    private String audit;
    private String partAudit;
    private String partSettleState;
    private String keyWord;
    private String partName;
    private Date settleDate;
    private Integer page;
    private Integer limit;

    public String getOrderDateReq() {
        return orderDateReq;
    }

    public void setOrderDateReq(String orderDateReq) {
        this.orderDateReq = orderDateReq;
    }

    public String getDeliveryDateReq() {
        return deliveryDateReq;
    }

    public void setDeliveryDateReq(String deliveryDateReq) {
        this.deliveryDateReq = deliveryDateReq;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState;
    }

    public String getPartInfo() {
        return partInfo;
    }

    public void setPartInfo(String partInfo) {
        this.partInfo = partInfo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSendServiceName() {
        return sendServiceName;
    }

    public void setSendServiceName(String sendServiceName) {
        this.sendServiceName = sendServiceName;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getPartAudit() {
        return partAudit;
    }

    public void setPartAudit(String partAudit) {
        this.partAudit = partAudit;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    public String getPartSettleState() {
        return partSettleState;
    }

    public void setPartSettleState(String partSettleState) {
        this.partSettleState = partSettleState;
    }

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }

    public Date getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(Date deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public Date getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(Date deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}
