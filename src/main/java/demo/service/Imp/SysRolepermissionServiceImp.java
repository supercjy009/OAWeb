package demo.service.Imp;


import demo.mapper.SysRolepermissionEntityMapper;
import demo.model.SysRolepermissionEntity;
import demo.service.SysRolepermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class SysRolepermissionServiceImp implements SysRolepermissionService {
    @Resource
    SysRolepermissionEntityMapper sysRolepermissionEntityMapper;
    @Override
    public List<SysRolepermissionEntity> queryListSysRolepermissionEntityByroleid(Long roleid) {
        return sysRolepermissionEntityMapper.queryListSysRolepermissionEntityByroleid(roleid);
    }
}
