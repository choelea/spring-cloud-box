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
@ApiModel(value = "撤销，完成的待办入参数")
public class TaskDTO {
    @ApiModelProperty(required = true,name = "appID",value = "应用系统编号",position = 1)
    private String appID;
    @ApiModelProperty(required = true,name = "appTaskID",value = "待办事项唯一的ID",position = 2)
    private String appTaskID;
    @ApiModelProperty(required = false,name = "handleTime",value = "处理时间",position = 3)
    private String handleTime;

}
