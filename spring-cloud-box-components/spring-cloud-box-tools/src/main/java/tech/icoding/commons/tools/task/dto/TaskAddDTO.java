package tech.icoding.commons.tools.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@ClassName TaskDTO
 *@Description TODO
 *@Author Dx
 *@Date 2021/10/21
 *@Version 1.0
 **/
@Data
@ApiModel(value = "新增待办入参数")
public class TaskAddDTO {
    @ApiModelProperty(required = true, name = "appID", value = "应用系统编号,由待办事项系统管理员分配给各应用系统",position = 1)
    private String appID;
    @ApiModelProperty(required = true, name = "taskName", value = "待办事项名称，超过100个字符截取",position = 2)
    private String taskName;
    @ApiModelProperty(required = true, name = "appTaskID", value = "应用系统中的待办事项唯一的ID",position = 3)
    private String appTaskID;
    @ApiModelProperty(required = true, name = "taskType", value = "所属待办类型，待办事项系统管理员分配给各应用系统",position = 4)
    private String taskType;
    @ApiModelProperty(required = true, name = "appReceiveUid", value = "接收者对应的门户账号，需要处理该项待办事项的用户，多个用户以英文逗号(，)分隔",position = 5)
    private String appReceiveUid;
    @ApiModelProperty(required = true, name = "url", value = "待办事项 pc 端处理链接，能够通过该链接直接到待办事项的处理界面",position = 6)
    private String url;
    @ApiModelProperty(required = false, name = "appUrl", value = "待办事项移动端处理链接，能够通过该链接直接到待办事项的处理界面",position = 7)
    private String appUrl;
    @ApiModelProperty(required = false, name = "appSendUser", value = "应用系统发送者名称",position = 8)
    private String appSendUser;
    @ApiModelProperty(required = false, name = "sendTime", value = "待办事项信息启用时间 yyyy-MM-ddHH:mm:ss，如果为空，则使用当时日期, 默认系统接收时间",position = 9)
    private String sendTime;
    @ApiModelProperty(required = false, name = "endTime", value = "待办事项信息结束时间 yyyy-MM-dd HH:mm:ss，如果为空，则使用应用系统待办事项的开始时间+7 天计算",position = 10)
    private String endTime;
    @ApiModelProperty(required = false, name = "handleTime", value = "待办事项信息处理时间 yyyy-MM-dd HH:mm:s，如果为空，则使用当时日期,默认系统接收时间",position = 11)
    private String handleTime;
    @ApiModelProperty(required = false, name = "taskDesc", value = "待办事项描述，超过 200 个字符截取",position = 12)
    private String taskDesc;
    @ApiModelProperty(required = false, name = "stateId", value = "待办事项的处理进度，0：待处理（新）； 1 ：已浏览；（根据具体应用系统，此 进度标识为可选）；2：完成；3：撤销； 4：超期 (调用 addTask 方法时，此字段值始终未 0)",position = 13)
    private String stateId;
    @ApiModelProperty(required = false, name = "priorityId", value = "待办事项信息紧急程度，越小越紧急,0:特急 1:紧急 2:一般,缺省 2",position = 14)
    private String priorityId;
    @ApiModelProperty(required = false, name = "noticeFlag", value = "待办事项提醒方式 0:不提醒 2:手机短信 3:邮件提醒 其他：不提醒",position = 15)
    private String noticeFlag;
    @ApiModelProperty(required = false, name = "noticeTimes", value = "待办事项信息通知次数",position = 16)
    private String noticeTimes;
    @ApiModelProperty(required = false, name = "noticeInterval", value = "待办事项信息 2 次通知的间隔时间",position = 17)
    private String noticeInterval;
}
