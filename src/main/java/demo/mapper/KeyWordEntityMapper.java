package demo.mapper;

import demo.model.dto.KeyWordReqVo;
import demo.model.KeyWordEntity;

import java.util.List;

public interface KeyWordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KeyWordEntity record);

    int insertSelective(KeyWordEntity record);

    KeyWordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KeyWordEntity record);

    int updateByPrimaryKey(KeyWordEntity record);

    List<KeyWordEntity> selectAllOrder(KeyWordReqVo vo);
}