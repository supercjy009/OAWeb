package demo.service;

import demo.model.UserinfoEntity;

/**
 * Created by PC on 2017/12/2.
 */
public interface UserinfoService {
    UserinfoEntity queryUserInfoByusername(String username);

    int registUser(String username, String password, String salt, Long roleId);
}
