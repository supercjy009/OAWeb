package demo.service.Imp;

import demo.mapper.SysPermissionEntityMapper;
import demo.model.SysPermissionEntity;
import demo.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysPermissionSerivceImp implements SysPermissionService {
    @Resource
    SysPermissionEntityMapper sysPermissionEntityMapper;
    @Override
    public SysPermissionEntity selectByPrimaryKey(Long id) {
        return sysPermissionEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermissionEntity> queryAllPermission() {
        return sysPermissionEntityMapper.selectAllPermission();
    }

    @Override
    @Transactional
    public int insertSelective(SysPermissionEntity sysPermissionEntity){
        deleteByPrimaryKey(1L);
        return sysPermissionEntityMapper.insert(sysPermissionEntity);
    }
    @Override
    public int deleteByPrimaryKey(Long id){
        return  sysPermissionEntityMapper.deleteByPrimaryKey(id);
    }
}
