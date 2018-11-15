package demo.dto;

import demo.model.OrderEntity;
import demo.model.PayProgress;

import java.util.List;

public class OrderVo extends OrderEntity {
    private List<PayProgress> progressList;

    public List<PayProgress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<PayProgress> progressList) {
        this.progressList = progressList;
    }
}
