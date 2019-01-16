package demo.model.dto;

import java.math.BigDecimal;

public class AppointPartVo {
    private Long orderId;

    private String orderNumber;

    private Integer partId;

    private String partQq;
    //稿酬
    private BigDecimal partMoney;
    //客服说明
    private String partRemark;
    //应扣
    private BigDecimal deduct;

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


    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public BigDecimal getPartMoney() {
        return partMoney;
    }

    public void setPartMoney(BigDecimal partMoney) {
        this.partMoney = partMoney;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }
}
