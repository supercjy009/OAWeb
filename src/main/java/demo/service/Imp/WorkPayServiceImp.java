package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.mapper.EditRecordEntityMapper;
import demo.model.EditRecordEntity;
import demo.model.OrderEntity;
import demo.model.dto.AuditVo;
import demo.model.dto.DeleteRecordVo;
import demo.model.dto.ServiceVo;
import demo.model.dto.WorkPayReqVo;
import demo.mapper.WorkPayEntityMapper;
import demo.model.UserinfoEntity;
import demo.model.WorkPayEntity;
import demo.service.WorkPayService;
import demo.util.EntityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class WorkPayServiceImp implements WorkPayService {
    @Resource
    WorkPayEntityMapper workPayEntityMapper;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    @Resource
    EditRecordEntityMapper recordMapper;
    @Override
    public PageInfo<WorkPayEntity> queryAllOrder(WorkPayReqVo vo) throws ParseException {
//        if (vo.getPageNum() != null && vo.getPageSize() != null) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
//        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(vo.getPayDate())) {
            String[] dateSplit = vo.getPayDate().split("~");
            vo.setPayDateStart(format.parse(dateSplit[0].trim()));
            vo.setPayDateEnd(format.parse(dateSplit[1]));
        }
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        List<String> permissions = sysPermissionSerivceImp.selectPermissionListByRoleId();
        String pValue = "1".equals(vo.getPartName()) || "2".equals(vo.getPartName()) || "3".equals(vo.getPartName()) ? "order" : vo.getPartName();
        vo.setSeeAll(permissions.contains(pValue + ":workpay:all") || permissions.contains("all"));
        vo.setUid(userinfoEntity.getUid());
        List<WorkPayEntity> workPayList = workPayEntityMapper.selectAllOrder(vo);

        return new PageInfo<>(workPayList);
    }

    @Override
    public int addOrder(WorkPayEntity work) {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        work.setPayUser(userinfoEntity.getUid().toString());
//        work.setAudit("0");//待审
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
        //记录哪些单元格需要变色
        WorkPayEntity originalOrder = workPayEntityMapper.selectByPrimaryKey(work.getId());
        List<String> rec = new ArrayList<>();
        try {
            originalOrder.setAudit("0");
            rec = EntityUtil.compareEntity(originalOrder, work);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String fieldName : rec) {
            EditRecordEntity record = new EditRecordEntity();
            record.setRecordId(originalOrder.getId());
            record.setFieldName(fieldName);
            record.setTableName("workPay");
            EditRecordEntity key = recordMapper.selectByPrimaryKey(record);
            if (key == null) {
                recordMapper.insert(record);
            }
        }
        work.setAudit("0");
//        work.setSettle("0");
        return workPayEntityMapper.updateByPrimaryKeySelective(work);
    }

    @Override
    public int auditOrder(AuditVo vo) {
        if ("1".equals(vo.getAudit())) {//删除变色的单元格
            DeleteRecordVo deleteVo = new DeleteRecordVo();
            deleteVo.setTableName("workPay");
            deleteVo.setIds(vo.getIds());
            recordMapper.deleteRecord(deleteVo);
        }
        return workPayEntityMapper.auditOrder(vo);
    }

    @Override
    public int deleteEntity(Long[] ids) {
        return workPayEntityMapper.deleteByPrimaryKeys(ids);
    }

    @Override
    public List<ServiceVo> selectAllService(String serviceName, String partName) {
        return workPayEntityMapper.selectAllService(serviceName, partName);
    }
}
