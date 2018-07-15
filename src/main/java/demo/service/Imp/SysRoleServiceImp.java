package demo.service.Imp;

import demo.mapper.SysRoleEntityMapper;
import demo.model.SysRoleEntity;
import demo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysRoleServiceImp implements SysRoleService {
    @Resource
    private SysRoleEntityMapper sysRoleEntityMapper;
    @Override
    public SysRoleEntity queryRoleByUserid(Long userid) {
        return sysRoleEntityMapper.selectByPrimaryKey(userid);
    }
}
