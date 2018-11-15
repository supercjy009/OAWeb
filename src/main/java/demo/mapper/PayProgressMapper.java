package demo.mapper;

import demo.model.PayProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayProgressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayProgress record);

    int insertSelective(PayProgress record);

    PayProgress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayProgress record);

    int updateByPrimaryKey(PayProgress record);

    List<PayProgress> selectByOrderId(@Param("orderId") Long orderId);
}