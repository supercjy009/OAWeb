package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.KeyWordReqVo;
import demo.model.KeyWordEntity;

/**
 * Created by p51 on 2018/5/16.
 */
public interface KeyWordService {
    PageInfo<KeyWordEntity> queryAllOrder(KeyWordReqVo vo);

    int addOrder(KeyWordEntity order);

    int updateOrder(KeyWordEntity order);

    int deleteEntity(Long id);
}
