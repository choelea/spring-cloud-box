package tech.icoding.commons.tools.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@ClassName PortalMsgAddDTO
 *@Description TODO
 *@Author Dx
 *@Date 2021/10/21
 *@Version 1.0
 **/
@Data
@ApiModel(value = "发送门户消息参数")
public class PortalMsgAddDTO {
    @ApiModelProperty(required = true, name = "id", value = "消息唯一编码，使用 32 位 uuid 不可重复",position = 1)
    private String id;
    @ApiModelProperty(required = true, name = "title", value = "消息标题，超过 200 个字符截取",position = 2)
    private String title;
    @ApiModelProperty(required = true, name = "content", value = "消息内容",position = 3)
    private String content;
    @ApiModelProperty(required = true, name = "appID", value = "应用系统编号,由门户管理员分配给各应用系统",position = 4)
    private String appID;
    @ApiModelProperty(required = false, name = "deptId", value = "接收消息的对应组织机构部门 id，多个部门 id 以逗号(，)分隔。（deptId 和mhaccout 不能同时为空)",position = 5)
    private String deptId;
    @ApiModelProperty(required = false, name = "mhaccout", value = "接收消息的对应组织机构部门 id，多个部门 id 以逗号(，)分隔。（deptId 和mhaccout 不能同时为空)",position = 6)
    private String mhaccout;
    @ApiModelProperty(required = false, name = "sendTime", value = "消息的发布时间 yyyy-MM-dd HH:mm:s，如果为空",position = 7)
    private String sendTime;
    @ApiModelProperty(required = false, name = "endTime", value = "消息的过期时间 yyyy-MM-dd HH:mm:s，如果为空，则使用当前系统时间+3 天",position = 8)
    private String endTime;
}
