package demo.model.dto;

public class AppointPartVo {
    private Long orderId;

    private String orderNumber;

    private Integer partId;

    private String partQq;
    //稿酬
    private String partMoney;
    //客服说明
    private String partRemark;
    //应扣
    private String deduct;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPartQq() {
        return partQq;
    }

    public void setPartQq(String partQq) {
        this.partQq = partQq;
    }

    public String getPartMoney() {
        return partMoney;
    }

    public void setPartMoney(String partMoney) {
        this.partMoney = partMoney;
    }

    public String getPartRemark() {
        return partRemark;
    }

    public void setPartRemark(String partRemark) {
        this.partRemark = partRemark;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }
}
