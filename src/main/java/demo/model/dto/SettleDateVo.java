package demo.model.dto;

import java.util.Date;

public class SettleDateVo {
    private Long[] ids;
    private Date settleDate;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}
