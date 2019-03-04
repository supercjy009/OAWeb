package demo.mapper;

import demo.model.EditRecordEntity;
import demo.model.dto.DeleteRecordVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EditRecordEntityMapper {
    int deleteByPrimaryKey(EditRecordEntity key);

    int insert(EditRecordEntity record);

    int insertSelective(EditRecordEntity record);

    EditRecordEntity selectByPrimaryKey(EditRecordEntity record);

    List<EditRecordEntity> selectRecordByTable(@Param("tableName") String tableName);

    void deleteRecord(DeleteRecordVo deleteVo);
}