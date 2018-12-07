package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.model.dto.PartUserReqVo;
import demo.model.dto.SettleDateVo;
import demo.mapper.PartTimeEntityMapper;
import demo.mapper.PartTimeUserMapper;
import demo.model.PartTimeUser;
import demo.service.PartUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class PartUserServiceImp implements PartUserService {
    @Resource
    PartTimeUserMapper partTimeUserMapper;
    @Resource
    PartTimeEntityMapper partTimeEntityMapper;

    @Override
    public PageInfo<PartTimeUser> queryAllOrder(PartUserReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        List<PartTimeUser> partUserList = partTimeUserMapper.selectAllOrder(vo);

        return new PageInfo<>(partUserList);
    }

    @Override
    public int addEntity(PartTimeUser order) {
        order.setStartJobDate(new Date());
        return partTimeUserMapper.insert(order);
    }

    @Override
    public int updateEntity(PartTimeUser order) {
        return partTimeUserMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteEntity(Long id) {
        return partTimeUserMapper.deleteByPrimaryKey(id);
    }


}
