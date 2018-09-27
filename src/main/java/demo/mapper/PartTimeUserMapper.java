package demo.mapper;

import demo.dto.PartUserReqVo;
import demo.model.PartTimeUser;

import java.util.List;

public interface PartTimeUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PartTimeUser record);

    int insertSelective(PartTimeUser record);

    PartTimeUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PartTimeUser record);

    int updateByPrimaryKey(PartTimeUser record);

    List<PartTimeUser> selectAllOrder(PartUserReqVo vo);
}