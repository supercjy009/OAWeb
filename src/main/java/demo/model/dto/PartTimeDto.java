package demo.model.dto;

import demo.model.PartTimeEntity;

import java.util.Date;

public class PartTimeDto extends PartTimeEntity {
    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
