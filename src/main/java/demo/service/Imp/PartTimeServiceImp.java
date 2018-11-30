package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.model.dto.PartOrderReqVo;
import demo.mapper.PartTimeEntityMapper;
import demo.model.PartTimeEntity;
import demo.model.UserinfoEntity;
import demo.model.dto.PartTimeDto;
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

    @Override
    public PageInfo<PartTimeDto> queryAllOrder(PartOrderReqVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
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
        return partTimeMapper.updateByPrimaryKey(order);
    }
}
