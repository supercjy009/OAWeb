package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.mapper.EditRecordEntityMapper;
import demo.model.*;
import demo.model.dto.*;
import demo.mapper.PartTimeEntityMapper;
import demo.service.PartTimeService;
import demo.util.EntityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class PartTimeServiceImp implements PartTimeService {
    @Resource
    PartTimeEntityMapper partTimeMapper;
    @Resource
    SysUserroleServiceImp sysUserroleServiceImp;
    @Resource
    SysPermissionSerivceImp sysPermissionSerivceImp;
    @Resource
    EditRecordEntityMapper recordMapper;

    @Override
    public PageInfo<PartTimeDto> queryAllOrder(PartOrderReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        List<SysUserroleEntity> sysUserroleEntities = sysUserroleServiceImp.queryUserroleByuserid(userinfoEntity.getUid());
        Long roleId = sysUserroleEntities.get(0).getRoleId();
        vo.setPartUser(SystemConstant.PART_ROLE_ID.equals(roleId));
        if (SystemConstant.PART_ROLE_ID.equals(roleId)) {
            vo.setPartQq(userinfoEntity.getUsername());
        }
        List<String> permissions = sysPermissionSerivceImp.selectPermissionListByRoleId();
        vo.setSeeAll(permissions.contains("partTime:order:all") || permissions.contains("all"));
        vo.setUid(userinfoEntity.getUid());
        List<PartTimeDto> partList = partTimeMapper.selectAllOrder(vo);
        return new PageInfo<>(partList);
    }

    @Override
    public int addOrder(PartTimeEntity order) {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        return partTimeMapper.insert(order);
    }

    @Override
    public int updateOrder(PartTimeEntity order) {
        //记录哪些单元格需要变色
        PartTimeEntity originalOrder = partTimeMapper.selectByPrimaryKey(order.getId());
        List<String> rec = new ArrayList<>();
        if (order.getPartMoneyFinance() != null && !order.getPartMoneyFinance().equals(originalOrder.getPartMoneyFinance())) {
            rec.add("partMoneyFinance");
        }
        if (order.getPartUserRemark() != null && !order.getPartUserRemark().equals(originalOrder.getPartUserRemark())) {
            rec.add("partUserRemark");
        }
        if (order.getPartMoneyReal() != null && !order.getPartMoneyReal().equals(originalOrder.getPartMoneyReal())) {
            rec.add("partMoneyReal");
        }

        if (order.getPartFinanceRemark() != null && !order.getPartFinanceRemark().equals(originalOrder.getPartFinanceRemark())) {
            rec.add("partFinanceRemark");
        }
        for (String fieldName : rec) {
            EditRecordEntity record = new EditRecordEntity();
            record.setRecordId(Long.valueOf(originalOrder.getId()));
            record.setFieldName(fieldName);
            record.setTableName("partOrder");
            EditRecordEntity key = recordMapper.selectByPrimaryKey(record);
            if (key == null) {
                recordMapper.insert(record);
            }
        }
        if (!"-100".equals(order.getPartAuditFinance()) || originalOrder.getPartMoneyFinance() != null) { // -100代表新建
            order.setPartAuditFinance("0");
        }
        return partTimeMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int auditOrder(AuditVo vo) {
        if ("1".equals(vo.getAudit())) {//删除变色的单元格
            DeleteRecordVo deleteVo = new DeleteRecordVo();
            deleteVo.setTableName("partOrder");
            deleteVo.setIds(vo.getIds());
            recordMapper.deleteRecord(deleteVo);
        }
        return partTimeMapper.auditOrder(vo);
    }

    @Override
    public int addSettleDate(SettleDateVo vo) {
        for (Long id : vo.getIds()) {
            EditRecordEntity record = new EditRecordEntity();
            record.setRecordId(id);
            record.setFieldName("settleDate");
            record.setTableName("partOrder");
            EditRecordEntity key = recordMapper.selectByPrimaryKey(record);
            if (key == null) {
                recordMapper.insert(record);
            }
        }
        return partTimeMapper.addSettleDate(vo);
    }

    @Override
    public int deleteEntity(Long[] ids) {
        return partTimeMapper.updateIsDelete(ids);
    }
}
