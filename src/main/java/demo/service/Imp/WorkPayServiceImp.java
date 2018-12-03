package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.model.dto.AuditVo;
import demo.model.dto.WorkPayReqVo;
import demo.mapper.WorkPayEntityMapper;
import demo.model.UserinfoEntity;
import demo.model.WorkPayEntity;
import demo.service.WorkPayService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class WorkPayServiceImp implements WorkPayService {
    @Resource
    WorkPayEntityMapper workPayEntityMapper;

    @Override
    public PageInfo<WorkPayEntity> queryAllOrder(WorkPayReqVo vo) {
//        if (vo.getPageNum() != null && vo.getPageSize() != null) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
//        }
        List<WorkPayEntity> workPayList = workPayEntityMapper.selectAllOrder(vo);

        return new PageInfo<>(workPayList);
    }

    @Override
    public int addOrder(WorkPayEntity work) {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        work.setPayUser(userinfoEntity.getUsername());
        work.setAudit("0");//待审
        work.setSettle("0");//待结
        return workPayEntityMapper.insert(work);
    }

    @Override
    public int updateOrder(WorkPayEntity work) {
//        WorkPayEntity workBefore = workPayEntityMapper.selectByPrimaryKey(work.getId());
//        StringBuilder sb = new StringBuilder();
//        if (workBefore.getPayDate().equals(work.getPayDate())) {
//            sb.append("1");
//        }
        work.setAudit("0");
//        work.setSettle("0");
        return workPayEntityMapper.updateByPrimaryKeySelective(work);
    }

    @Override
    public int auditOrder(AuditVo vo) {
        return workPayEntityMapper.auditOrder(vo);
    }
}
