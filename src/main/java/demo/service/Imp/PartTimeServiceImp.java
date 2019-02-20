package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.model.SysUserroleEntity;
import demo.model.dto.AuditVo;
import demo.model.dto.PartOrderReqVo;
import demo.mapper.PartTimeEntityMapper;
import demo.model.PartTimeEntity;
import demo.model.UserinfoEntity;
import demo.model.dto.PartTimeDto;
import demo.model.dto.SettleDateVo;
import demo.service.PartTimeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        order.setPartAuditFinance("0");
        return partTimeMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int auditOrder(AuditVo vo) {
        return partTimeMapper.auditOrder(vo);
    }

    @Override
    public int addSettleDate(SettleDateVo vo) {
        return partTimeMapper.addSettleDate(vo);
    }

    @Override
    public int deleteEntity(Long[] ids) {
        return partTimeMapper.updateIsDelete(ids);
    }
}
