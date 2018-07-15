package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.AuditVo;
import demo.dto.WorkPayReqVo;
import demo.model.WorkPayEntity;

/**
 * Created by p51 on 2018/5/16.
 */
public interface WorkPayService {
    PageInfo<WorkPayEntity> queryAllOrder(WorkPayReqVo vo);

    int addOrder(WorkPayEntity order);

    int updateOrder(WorkPayEntity order);

    int auditOrder(AuditVo vo);
}
