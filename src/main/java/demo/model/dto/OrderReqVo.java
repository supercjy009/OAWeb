package demo.model.dto;

import java.util.Date;

/**
 * Created by p51 on 2018/7/25.
 */
public class OrderReqVo extends BaseReqDto{
    private String orderDateReq; //接单时间(最近七天、当月等等)
    private Date orderDateStart;
    private Date orderDateEnd;
    private String deliveryDateReq; //交稿时间
    private Date deliveryDateStart;
    private Date deliveryDateEnd;
    private String payState;
    private String submitState;
    private String partInfo;
    private Boolean masterHand;
    private Boolean referrer;
    private String serviceId;
    private Long sendServiceId;
    private String audit;
    private String partAudit;
    private String partSettleState;
    private String keyWord;
    private String partName;//这是文案第几部门
    private Date settleDate;
    private Boolean hideSettle;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getSendServiceId() {
        return sendServiceId;
    }

    public void setSendServiceId(Long sendServiceId) {
        this.sendServiceId = sendServiceId;
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

    public Boolean getHideSettle() {
        return hideSettle;
    }

    public void setHideSettle(Boolean hideSettle) {
        this.hideSettle = hideSettle;
    }
}
