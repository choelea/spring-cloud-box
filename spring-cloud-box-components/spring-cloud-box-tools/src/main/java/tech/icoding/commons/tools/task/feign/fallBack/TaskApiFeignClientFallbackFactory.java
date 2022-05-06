package tech.icoding.commons.tools.task.feign.fallBack;

import feign.hystrix.FallbackFactory;
import tech.icoding.commons.tools.task.dto.*;
import tech.icoding.commons.tools.task.feign.TaskApiFeignClient;
import tech.icoding.commons.tools.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: GaoDong
 * @Date: 2021/12/22
 * @Description: 待办消息调用 FallbackFactory
 */
@Slf4j
@Component
public class TaskApiFeignClientFallbackFactory implements FallbackFactory<TaskApiFeignClient> {

    @Override
    public TaskApiFeignClient create(Throwable throwable) {
        return new TaskApiFeignClient() {
            @Override
            public Result addTask(TaskAddDTO taskAddDTO) {
                log.error("调用新增待办addTask方法异常：{}",taskAddDTO, throwable);
                return new Result();
            }
            @Override
            public Result canceTask(TaskDTO taskDTO) {
                log.error("调用撤销待办canceTask方法异常：{}",taskDTO, throwable);
                return new Result();
            }
            @Override
            public Result cancelUserTask(TaskUserDTO taskUserDTO) {
                log.error("调用撤销指定用户待办cancelUserTask方法异常：{}",taskUserDTO, throwable);
                return new Result();
            }
            @Override
            public Result completeTask(TaskDTO taskDTO) {
                log.error("调用完成待办completeTask方法异常：{}",taskDTO, throwable);
                return new Result();
            }
            @Override
            public Result completeUserTask(TaskUserDTO taskUserDTO) {
                log.error("调用完成指定用户待办completeUserTask方法异常：{}",taskUserDTO, throwable);
                return new Result();
            }
            @Override
            public Result addPortalMsg(PortalMsgAddDTO portalMsgAddDTO) {
                log.error("调用发送门户消息addPortalMsg方法异常：{}",portalMsgAddDTO, throwable);
                return new Result();
            }
            @Override
            public Result cancelPortalMsg(PortalMsgDTO portalMsgDTO) {
                log.error("调用撤销门户消息cancelPortalMsg方法异常：{}",portalMsgDTO, throwable);
                return new Result();
            }
        };
    }
}
