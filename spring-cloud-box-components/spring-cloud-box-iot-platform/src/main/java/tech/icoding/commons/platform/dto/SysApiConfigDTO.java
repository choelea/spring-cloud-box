package tech.icoding.commons.platform.dto;

import lombok.Data;

import java.io.Serializable;

/**
* 物管配置
*
* @author Mark cxwm xucheng 
* @since 3.0 2022-01-07
*/
@Data
public class SysApiConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String appId;
    private String appKey;
    private String iotHttpUrl;
    private String depotsCode;
    private String deviceId;

}