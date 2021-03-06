package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.config.SystemConstant;
import demo.model.UserinfoEntity;
import demo.model.dto.PartUserAddVo;
import demo.model.dto.PartUserReqVo;
import demo.model.dto.SettleDateVo;
import demo.mapper.PartTimeEntityMapper;
import demo.mapper.PartTimeUserMapper;
import demo.model.PartTimeUser;
import demo.model.dto.UserAddVo;
import demo.service.PartUserService;
import demo.service.UserinfoService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private UserinfoService userService;

    @Override
    public PageInfo<PartTimeUser> queryAllOrder(PartUserReqVo vo) throws ParseException {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(vo.getRecentDate())) {
            String[] dateSplit = vo.getRecentDate().split("~");
            vo.setRecentDateStart(format.parse(dateSplit[0].trim()));
            vo.setRecentDateEnd(format.parse(dateSplit[1]));
        }
        List<PartTimeUser> partUserList = partTimeUserMapper.selectAllOrder(vo);

        return new PageInfo<>(partUserList);
    }

    @Override
    public int addEntity(PartTimeUser order) {
        order.setProblemRate("0");
        order.setGetOrderNumber(0);
        order.setOutDeliveryCount(0);
        order.setOutSettleCount(0);
//        order.setTotalReward(BigDecimal.ZERO);
        order.setStartJobDate(new Date());
        return partTimeUserMapper.insert(order);
    }

    @Override
    public int updateEntity(PartUserAddVo order) {
        PartTimeUser pUser = partTimeUserMapper.selectByPrimaryKey(order.getId());
        if(!StringUtils.isEmpty(order.getPassWord()) || !pUser.getPartQq().equals(order.getPartQq())){
            UserinfoEntity user = userService.queryUserInfoByusername(pUser.getPartQq());//这里一定要用pUser
            UserAddVo userVo = new UserAddVo();
            userVo.setUid(user.getUid());
            userVo.setPassword(order.getPassWord());
            userVo.setUsername(order.getPartQq());
            userVo.setName(order.getPartQq());
            userVo.setSalt(SystemConstant.PART_SALT_STRING);
            userService.editEntity(userVo);
        }
        return partTimeUserMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteEntity(Long id) {
        PartTimeUser partTimeUser = partTimeUserMapper.selectByPrimaryKey(id);
        String partQq = partTimeUser.getPartQq();
        UserinfoEntity user = userService.queryUserInfoByusername(partQq);
        if (user != null) {
            //删除用户
            userService.deleteEntity(user.getUid());
        }
        return partTimeUserMapper.deleteByPrimaryKey(id);
    }


}
