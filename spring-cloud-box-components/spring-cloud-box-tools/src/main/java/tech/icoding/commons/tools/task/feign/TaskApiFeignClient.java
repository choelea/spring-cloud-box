package tech.icoding.commons.tools.task.feign;

import tech.icoding.commons.tools.task.dto.*;
import tech.icoding.commons.tools.task.feign.fallBack.TaskApiFeignClientFallbackFactory;
import tech.icoding.commons.tools.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: GaoDong
 * @Date: 2021/12/22
 * @Description: 待办消息调用
 */
@FeignClient(name = "spring-cloud-boxtaskApi-service", contextId = "TaskApiFeignClient", fallbackFactory = TaskApiFeignClientFallbackFactory.class)
public interface TaskApiFeignClient {
    //新增待办
    @PostMapping("/taskApi/msgCenter/taskServer/api/addTask")
    Result addTask(@RequestBody TaskAddDTO taskAddDTO);
    //撤销待办
    @PostMapping("/taskApi/msgCenter/taskServer/api/cancelTask")
    Result canceTask(@RequestBody TaskDTO taskDTO);
    //撤销指定用户待办
    @PostMapping("/taskApi/msgCenter/taskServer/api/cancelUserTask")
    Result cancelUserTask(@RequestBody TaskUserDTO taskUserDTO);
    //完成待办
    @PostMapping("/taskApi/msgCenter/taskServer/api/completeTask")
    Result completeTask(@RequestBody TaskDTO taskDTO);
    //完成指定用户待办
    @PostMapping("/taskApi/msgCenter/taskServer/api/completeUserTask")
    Result completeUserTask(@RequestBody  TaskUserDTO taskUserDTO);

    //发送门户消息
    @PostMapping("/taskApi/msgCenter/portalMsgServer/api/addPortalMsg")
    Result addPortalMsg(@RequestBody PortalMsgAddDTO portalMsgAddDTO);
    //撤销门户消息
    @PostMapping("/taskApi/msgCenter/portalMsgServer/api/cancelPortalMsg")
    Result cancelPortalMsg(@RequestBody PortalMsgDTO portalMsgDTO);
}
