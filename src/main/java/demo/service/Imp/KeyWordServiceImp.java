package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.dto.AuditVo;
import demo.dto.KeyWordReqVo;
import demo.dto.WorkPayReqVo;
import demo.mapper.KeyWordEntityMapper;
import demo.mapper.KeyWordEntityMapper;
import demo.model.UserinfoEntity;
import demo.model.KeyWordEntity;
import demo.service.KeyWordService;
import demo.service.WorkPayService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class KeyWordServiceImp implements KeyWordService {
    @Resource
    KeyWordEntityMapper KeyWordEntityMapper;

    @Override
    public PageInfo<KeyWordEntity> queryAllOrder(KeyWordReqVo vo) {
//        if (vo.getPageNum() != null && vo.getPageSize() != null) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
//        }
        List<KeyWordEntity> workPayList = KeyWordEntityMapper.selectAllOrder(vo);

        return new PageInfo<>(workPayList);
    }

    @Override
    public int addOrder(KeyWordEntity work) {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        work.setCreateDate(new Date());
        work.setCreateUser(userinfoEntity.getUid());
        return KeyWordEntityMapper.insert(work);
    }

    @Override
    public int updateOrder(KeyWordEntity work) {
//        KeyWordEntity workBefore = KeyWordEntityMapper.selectByPrimaryKey(work.getId());
//        StringBuilder sb = new StringBuilder();
//        if (workBefore.getPayDate().equals(work.getPayDate())) {
//            sb.append("1");
        return KeyWordEntityMapper.updateByPrimaryKeySelective(work);
    }

    @Override
    public int deleteEntity(Long id) {
        return KeyWordEntityMapper.deleteByPrimaryKey(id);
    }
}
