package demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileUtil {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public String uploadFile(MultipartFile file, String target) throws IOException {
        // 取得当前上传文件的文件名称
        String fileName = file.getOriginalFilename();
        String targetDir = uploadFolder + target;
        File targetFolder = new File(targetDir);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        String fileUri = target + fileName;
        file.transferTo(new File(targetDir + fileName));
        return fileUri;
    }
}
