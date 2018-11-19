package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.PartUserReqVo;
import demo.dto.SettleDateVo;
import demo.model.PartTimeUser;

import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface PartUserService {
    PageInfo<PartTimeUser> queryAllOrder(PartUserReqVo vo);

    int addEntity(PartTimeUser order);

    int updateEntity(PartTimeUser order);

    int deleteEntity(Long id);

    int addSettleDate(SettleDateVo vo);
}
