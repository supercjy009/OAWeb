package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.FileReqVo;
import demo.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by p51 on 2018/5/16.
 */
public interface FileService {
    PageInfo<FileEntity> queryAllOrder(FileReqVo vo);

    int addOrder(MultipartFile file, String partName) throws IOException;

    int updateOrder(FileEntity order);

    int deleteEntity(Long id);

    int setType(Long id, String type);
}
