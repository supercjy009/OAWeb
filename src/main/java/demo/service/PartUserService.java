package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.PartUserReqVo;
import demo.model.dto.SettleDateVo;
import demo.model.PartTimeUser;

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
