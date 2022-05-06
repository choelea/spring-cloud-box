package tech.icoding.commons.platform.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/14
 * @Description: 物管平台相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix="iot")
public class IotProperties implements Serializable {
    /**
     * 黄石
     */
    private String appKey = "";
    private String appId = "";
    private String deviceId = "";
    private String iotHttpUrl = "";

}
