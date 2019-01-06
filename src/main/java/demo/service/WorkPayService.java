package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.AuditVo;
import demo.model.dto.ServiceVo;
import demo.model.dto.WorkPayReqVo;
import demo.model.WorkPayEntity;

import java.text.ParseException;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface WorkPayService {
    PageInfo<WorkPayEntity> queryAllOrder(WorkPayReqVo vo) throws ParseException;

    int addOrder(WorkPayEntity order);

    int updateOrder(WorkPayEntity order);

    int auditOrder(AuditVo vo);

    int deleteEntity(Long[] ids);

    List<ServiceVo> selectAllService(String serviceName, String partName);
}
