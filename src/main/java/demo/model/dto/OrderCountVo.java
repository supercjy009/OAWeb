package demo.model.dto;

import java.math.BigDecimal;

public class OrderCountVo {
    private Long id;
    private Double orderPrice;
    private Double refundMoney;
    private Double partMoney;
    private Double deduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getPartMoney() {
        return partMoney;
    }

    public void setPartMoney(Double partMoney) {
        this.partMoney = partMoney;
    }

    public Double getDeduct() {
        return deduct;
    }

    public void setDeduct(Double deduct) {
        this.deduct = deduct;
    }
}
