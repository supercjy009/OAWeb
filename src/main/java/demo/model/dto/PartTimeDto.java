package demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import demo.model.PartTimeEntity;

import java.util.Date;

public class PartTimeDto extends PartTimeEntity {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
