package demo.dto;

public class AppointPartVo {
    private Long orderId;

    private String partQq;
    //稿酬
    private String partMoney;
    //客服说明
    private String partRemark;

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
}
