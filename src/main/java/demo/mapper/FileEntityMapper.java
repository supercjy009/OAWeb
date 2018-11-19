package demo.mapper;

import demo.dto.FileReqVo;
import demo.model.FileEntity;

import java.util.List;

public interface FileEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileEntity record);

    int insertSelective(FileEntity record);

    FileEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileEntity record);

    int updateByPrimaryKey(FileEntity record);

    List<FileEntity> selectAllOrder(FileReqVo vo);
}