package tech.icoding.commons.oss.service;

import tech.icoding.commons.oss.config.OssProperties;
import tech.icoding.commons.tools.exception.RenException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @author huangfei
 * @create 2022-04-21
 */
@Slf4j
@Component
public class UploadTemplate {
    private final BestOssTemplate bestOssTemplate;
    private final OssProperties ossProperties;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public UploadTemplate(BestOssTemplate bestOssTemplate, OssProperties ossProperties) {
        this.bestOssTemplate = bestOssTemplate;
        this.ossProperties = ossProperties;
    }

    public String upload(MultipartFile file, String prefix) {
        String dataPath = format.format(new Date());
        // 文件名称生成
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String objectName = prefix + "/" + dataPath + "/" + uuid + file.getOriginalFilename();
        try {
            bestOssTemplate.putObject(ossProperties.getBucket(), objectName, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    file.getInputStream());
        }
        catch (Exception e) {
            log.error("上传失败", e);
            throw new RenException("文件上传失败！");
        }
        return bestOssTemplate.getObjectURL(ossProperties.getBucket(), objectName);
    }

    /**
     * 上传单个文件到本地磁盘（供测试时使用）
     * @param file
     * @return
     */
    @SneakyThrows
    public String uploadLocal(MultipartFile file, String prefix) {
        String fileExtension  = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + fileExtension;
        String path = "d:/stc/" + prefix + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd/"));
        log.info("文件上传路径：{}", path);

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = path + fileName;

        File dest = new File(filePath);
        if (!dest.exists()) {
            file.transferTo(dest);
        }

        return filePath;
    }
}
