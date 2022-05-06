package tech.icoding.commons.platform.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import tech.icoding.commons.platform.dto.SysApiConfigDTO;
import tech.icoding.commons.platform.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 * @Description: 北向http请求
 */
@Configuration
@Slf4j
public class IotHttpPostUtil {

    public static String iotHttpPost(String body, SysApiConfigDTO sysApiConfigDTO) {
        log.info("sysApiConfigDTO "+JSONObject.toJSONString(sysApiConfigDTO));
//        IotProperties iotProperties = ApplicationContextUtils.getInstance()
//                .getBean("iotProperties",IotProperties.class);
        //log.info(JSON.toJSONString(iotProperties));
        String appId = sysApiConfigDTO.getAppId();    //HS_ZYC
        String appKey = sysApiConfigDTO.getAppKey();  //1pOAicCn3dDdOmuSKOe51w==
        String deviceId = sysApiConfigDTO.getDeviceId();  //D1491471LtXGF
        String iotHttpUrl = sysApiConfigDTO.getIotHttpUrl();  //https://25.55.176.146:1443/iot/1.0/deviceCommands
        iotHttpUrl = iotHttpUrl+"?appId="+appId;

        /*业务数据组装*/
        Map<String, Object> bodys = Maps.newHashMap();
        bodys.put("deviceId",deviceId);  //设备ID
        Map<String, Object> commandDTO  = Maps.newHashMap();
        commandDTO.put("serviceId","interface");  //服务ID
        commandDTO.put("method","senddata");  //命令名称
        Map<String,Object> para = new HashMap<>();
        para.put("data",body);
        commandDTO.put("paras",para);
        bodys.put("command",commandDTO);
        /*业务数据组装*/
        Map<String, Object> mapReturn = new HashMap<>();
        try{
            log.info("IothttpPostUtil request " +JSON.toJSONString(bodys));
            mapReturn = HttpUtils.hwApiPost(iotHttpUrl, appId, appKey, null, bodys);
            log.info("IothttpPostUtil result = "+ JSON.toJSONString(mapReturn));
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapReturn.get("code").toString();
    }


}
