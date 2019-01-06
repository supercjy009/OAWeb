package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.FileEntityDto;
import demo.model.dto.FileReqVo;
import demo.model.FileEntity;
import demo.model.dto.ServiceVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface FileService {
    PageInfo<FileEntityDto> queryAllOrder(FileReqVo vo) throws ParseException;

    int addOrder(MultipartFile file, String partName) throws IOException;

    int updateOrder(FileEntity order);

    int deleteEntity(Long[] id);

    int setType(Long id, String type);

    List<ServiceVo> selectAllService(String serviceName, String partName);
}
