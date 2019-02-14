package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.PartUserAddVo;
import demo.model.dto.PartUserReqVo;
import demo.model.dto.SettleDateVo;
import demo.model.PartTimeUser;

import java.text.ParseException;

/**
 * Created by p51 on 2018/5/16.
 */
public interface PartUserService {
    PageInfo<PartTimeUser> queryAllOrder(PartUserReqVo vo) throws ParseException;

    int addEntity(PartTimeUser order);

    int updateEntity(PartUserAddVo order);

    int deleteEntity(Long id);

}
