package demo.mapper;

import demo.model.PartTimeEntity;
import org.apache.ibatis.annotations.Param;

public interface PartTimeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartTimeEntity record);

    int insertSelective(PartTimeEntity record);

    PartTimeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartTimeEntity record);

    int updateByPrimaryKey(PartTimeEntity record);

    PartTimeEntity getOrderPartByQq(@Param("partQq") String partQq, @Param("orderNumber") String orderNumber);

    int deletePart(@Param("orderNumber") String orderNumber, @Param("partQq") String partQq);
}