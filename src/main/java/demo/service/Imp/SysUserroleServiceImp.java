package demo.service.Imp;

import demo.mapper.SysUserroleEntityMapper;
import demo.model.SysUserroleEntity;
import demo.service.SysUserroleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysUserroleServiceImp implements SysUserroleService {
    @Resource
    private SysUserroleEntityMapper sysUserroleEntityMapper;
    @Override
    public List<SysUserroleEntity> queryUserroleByuserid(Long userid) {
        return sysUserroleEntityMapper.queryUserroleByuserid(userid);
    }
}
