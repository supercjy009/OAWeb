package demo.mapper;

import demo.model.PartTimeEntity;

public interface PartTimeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartTimeEntity record);

    int insertSelective(PartTimeEntity record);

    PartTimeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartTimeEntity record);

    int updateByPrimaryKey(PartTimeEntity record);
}