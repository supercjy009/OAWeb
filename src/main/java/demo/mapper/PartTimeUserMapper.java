package demo.mapper;

import demo.model.PartTimeUser;

public interface PartTimeUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PartTimeUser record);

    int insertSelective(PartTimeUser record);

    PartTimeUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PartTimeUser record);

    int updateByPrimaryKey(PartTimeUser record);
}