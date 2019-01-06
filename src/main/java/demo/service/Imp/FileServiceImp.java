package demo.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.model.dto.FileEntityDto;
import demo.model.dto.FileReqVo;
import demo.mapper.FileEntityMapper;
import demo.model.FileEntity;
import demo.model.UserinfoEntity;
import demo.model.dto.ServiceVo;
import demo.service.FileService;
import demo.util.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
@Service
public class FileServiceImp implements FileService {
    @Resource
    FileEntityMapper entityMapper;
    @Resource
    private FileUtil fileUtil;

    @Override
    public PageInfo<FileEntityDto> queryAllOrder(FileReqVo vo) throws ParseException {
//        if (vo.getPageNum() != null && vo.getPageSize() != null) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
//        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(vo.getCreateDate())) {
            String[] dateSplit = vo.getCreateDate().split("~");
            vo.setCreateDateStart(format.parse(dateSplit[0].trim()));
            vo.setCreateDateEnd(format.parse(dateSplit[1]));
        }
        List<FileEntityDto> entityList = entityMapper.selectAllOrder(vo);

        return new PageInfo<>(entityList);
    }

    @Override
    public int addOrder(MultipartFile file, String partName) throws IOException {
        UserinfoEntity userinfoEntity = (UserinfoEntity) SecurityUtils.getSubject().getPrincipal();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setCreateDate(new Date());
        fileEntity.setCreateUser(userinfoEntity.getUid());
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileUri(fileUtil.uploadFile(file, "/oa/" + partName + "/"));
        fileEntity.setPartName(partName);
        return entityMapper.insert(fileEntity);
    }

    @Override
    public int updateOrder(FileEntity work) {
//        KeyWordEntity workBefore = KeyWordEntityMapper.selectByPrimaryKey(work.getId());
//        StringBuilder sb = new StringBuilder();
//        if (workBefore.getPayDate().equals(work.getPayDate())) {
//            sb.append("1");
        return entityMapper.updateByPrimaryKeySelective(work);
    }

    @Override
    public int deleteEntity(Long[] ids) {
        return entityMapper.deleteByPrimaryKeys(ids);
    }

    @Override
    public int setType(Long id, String type) {
        FileEntity entity = entityMapper.selectByPrimaryKey(id);
        entity.setType(type);
        return entityMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<ServiceVo> selectAllService(String serviceName, String partName) {
        return entityMapper.selectAllService(serviceName, partName);
    }
}
