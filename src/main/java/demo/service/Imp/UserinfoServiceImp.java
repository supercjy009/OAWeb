package demo.service.Imp;


import demo.mapper.UserinfoEntityMapper;
import demo.model.UserinfoEntity;
import demo.service.UserinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by PC on 2017/12/2.
 */
@Service
public class UserinfoServiceImp implements UserinfoService {
    @Resource
    private UserinfoEntityMapper userinfoEntityMapper;//这里会报错，但是并不会影响
    @Override
    public UserinfoEntity queryUserInfoByusername(String username) {
        try{
            return userinfoEntityMapper.queryUserInfoByusername(username);
        }catch (Exception ex){
            System.out.println("queryUserInfoByusername has exception:" + ex.getMessage());
            return null;
        }

    }
}
