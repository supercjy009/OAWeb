package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.UserinfoEntity;
import demo.model.dto.UserAddVo;
import demo.model.dto.UserReqVo;

/**
 * Created by PC on 2017/12/2.
 */
public interface UserinfoService {
    UserinfoEntity queryUserInfoByusername(String username);

    int registUser(String username, String password, String salt, Long roleId);

    PageInfo<UserinfoEntity> selectAllUser(UserReqVo vo);

    int editEntity(UserAddVo user);

    int deleteEntity(Long uid);
}
