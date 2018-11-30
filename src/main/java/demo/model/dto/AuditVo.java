package demo.model.dto;

/**
 * Created by p51 on 2018/6/11.
 */
public class AuditVo {
    private Long[] ids;
    private String audit;
    private String partAudit;
    private String settle;
    private String submitState;

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

    public String getSubmitState() {
        return submitState;
    }

    public void setSubmitState(String submitState) {
        this.submitState = submitState;
    }

    public String getPartAudit() {
        return partAudit;
    }

    public void setPartAudit(String partAudit) {
        this.partAudit = partAudit;
    }
}
