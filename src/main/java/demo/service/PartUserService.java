package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.AuditVo;
import demo.dto.PartUserVo;
import demo.dto.WorkPayReqVo;
import demo.model.PartTimeUser;
import demo.model.WorkPayEntity;

/**
 * Created by p51 on 2018/5/16.
 */
public interface PartUserService {
    PageInfo<PartTimeUser> queryAllOrder(PartUserVo vo);

    int addEntity(PartTimeUser order);

    int updateEntity(PartTimeUser order);

}
