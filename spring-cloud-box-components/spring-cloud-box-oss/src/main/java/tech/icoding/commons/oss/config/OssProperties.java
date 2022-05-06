package tech.icoding.commons.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huangfei
 * @create 2022-04-21
 */
@Data
@Component
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class OssProperties {
    public static final String PREFIX = "oss";

    private String bucket;
    private String endpoint;
    private String region;
    private Boolean pathStyleAccess = true;
    private String accessKeyId;
    private String accessKeySecret;
}
