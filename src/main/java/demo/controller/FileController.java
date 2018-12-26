package demo.controller;

import com.github.pagehelper.PageInfo;
import demo.model.dto.FileEntityDto;
import demo.model.dto.FileReqVo;
import demo.model.FileEntity;
import demo.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by p51 on 2018/5/16.
 */
@RestController
@RequestMapping(value = "/webAjax/file")
public class FileController {
    @Resource
    FileService entityService;
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${file.uploadFolder}")
    private String downFolder;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM").format((Date) getValue());
            }

        });
    }

    @RequestMapping(value = "/queryAllOrder", method = RequestMethod.GET)
        public Map<String, Object> queryAllOrder(FileReqVo vo) {
            Map<String, Object> mapOut = new HashMap<>();
            PageInfo<FileEntityDto> workPayPageInfo = entityService.queryAllOrder(vo);
            mapOut.put("code", 0);
            mapOut.put("count", workPayPageInfo.getTotal());
            mapOut.put("data", workPayPageInfo.getList());
            return mapOut;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Map<String, Object> addOrder(@RequestParam MultipartFile file, @RequestParam String partName) {
        Map<String, Object> mapOut = new HashMap<>();
        try {
            mapOut.put("code", entityService.addOrder(file, partName));
        } catch (IOException e) {
            log.error("上传文件失败，{}", e.getMessage());
            mapOut.put("code", -1);
        }
        return mapOut;
    }

    @RequestMapping(value = "/setType", method = RequestMethod.POST)
    public Map<String, Object> setType(@RequestParam Long id, @RequestParam String type) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.setType(id, type));
        return mapOut;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public Map<String, Object> updateOrder(@RequestBody FileEntity order) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.updateOrder(order));
        return mapOut;
    }


    @RequestMapping(value = "/deleteEntity", method = RequestMethod.POST)
    public Map<String, Object> deleteEntity(@RequestParam Long[] ids) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", entityService.deleteEntity(ids));
        return mapOut;
    }

    //文件下载相关代码
    @ApiOperation("文件下载接口")
    @GetMapping("/downloadFile")
    public String downloadFile(@RequestParam String uri, HttpServletResponse response) {
        log.info("请求下载文件...{}", uri);
        if (!StringUtils.isEmpty(uri)) {
            //设置文件路径
            File file = new File(downFolder + uri);
            if (file.exists()) {
                String fileName = file.getName();
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fileName = new String(fileName.getBytes(), "ISO-8859-1");
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                    byte[] buffer = new byte[1024];
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    log.error("文件下载失败!message:{}", e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.error("文件下载失败!message:{}", e.getMessage());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.error("文件下载失败!message:{}", e.getMessage());
                        }
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/selectAllService", method = RequestMethod.GET)
    public Map<String, Object> selectAllService(@RequestParam(defaultValue = "") String serviceName,
                                                @RequestParam(defaultValue = "") String partName) {
        Map<String, Object> mapOut = new HashMap<>();
        mapOut.put("code", 1);
        mapOut.put("data", entityService.selectAllService(serviceName, partName));
        return mapOut;
    }
}
