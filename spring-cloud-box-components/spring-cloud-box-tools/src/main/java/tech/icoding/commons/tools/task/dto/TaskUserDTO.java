package tech.icoding.commons.tools.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@ClassName TaskUserDTO
 *@Description TODO
 *@Author Dx
 *@Date 2021/10/21
 *@Version0
 **/
@Data
@ApiModel(value = "撤销，完成指定用户的待办入参数")
public class TaskUserDTO {

    @ApiModelProperty(required = true,value = "应用系统编号",name = "appID",position = 1)
    private String appID;
    @ApiModelProperty(required = true,value = "待办事项唯一的ID",name = "appTaskID",position = 2)
    private String appTaskID;
    @ApiModelProperty(required = true,value = "要撤销待办的门户账号",name = "user",position = 3)
    private String user;
    @ApiModelProperty(required = false,value = "待办事项信息处理时间 yyyy-MM-dd HH:mm:s，如果为空，则使用当时日期,默认系统接收时间",name = "handleTime",position = 4)
    private String handleTime;

}
