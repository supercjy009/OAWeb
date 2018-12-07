package demo.mapper;

import demo.model.dto.AuditVo;
import demo.model.dto.PartOrderReqVo;
import demo.model.dto.PartTimeDto;
import demo.model.dto.SettleDateVo;
import demo.model.PartTimeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartTimeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartTimeEntity record);

    int insertSelective(PartTimeEntity record);

    PartTimeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartTimeEntity record);

    int updateByPrimaryKey(PartTimeEntity record);

    PartTimeEntity getOrderPartByQq(@Param("partQq") String partQq, @Param("orderNumber") String orderNumber);

    int deletePart(@Param("orderNumber") String orderNumber, @Param("partQq") String partQq);

    int addSettleDate(SettleDateVo vo);

    List<PartTimeDto> selectAllOrder(PartOrderReqVo vo);

    int auditOrder(AuditVo vo);
}