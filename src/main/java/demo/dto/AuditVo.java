package demo.dto;

import java.util.List;

/**
 * Created by p51 on 2018/6/11.
 */
public class AuditVo {
    private Long[] ids;
    private String audit;
    private String settle;


    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
