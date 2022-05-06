package tech.icoding.commons.tools.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@ClassName PortalMsgDTO
 *@Description TODO
 *@Author Dx
 *@Date 2021/10/21
 *@Version 1.0
 **/
@Data
@ApiModel(value = "撤销门户消息参数")
public class PortalMsgDTO {
    @ApiModelProperty(required = true, name = "id",value = "消息唯一编码，使用 32 位 uuid 不可重复",position = 1)
    private String id;
    @ApiModelProperty(required = true, name = "appID",value = "应用系统编号,由门户管理员分配给各应用系统",position = 2)
    private String appID;
}
