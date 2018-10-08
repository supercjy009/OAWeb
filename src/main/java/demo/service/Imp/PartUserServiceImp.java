package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.dto.PartUserReqVo;
import demo.mapper.PartTimeUserMapper;
import demo.model.PartTimeUser;
import demo.service.PartUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class PartUserServiceImp implements PartUserService {
    @Resource
    PartTimeUserMapper partTimeUserMapper;


    @Override
    public PageInfo<PartTimeUser> queryAllOrder(PartUserReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        List<PartTimeUser> partUserList = partTimeUserMapper.selectAllOrder(vo);

        return new PageInfo<>(partUserList);
    }

    @Override
    public int addEntity(PartTimeUser order) {
        return partTimeUserMapper.insert(order);
    }

    @Override
    public int updateEntity(PartTimeUser order) {
        return 0;
    }
}
