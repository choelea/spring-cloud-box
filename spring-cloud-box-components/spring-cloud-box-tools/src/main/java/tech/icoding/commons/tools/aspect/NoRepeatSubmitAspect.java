package tech.icoding.commons.tools.aspect;

import tech.icoding.commons.tools.exception.RenException;
import tech.icoding.commons.tools.redis.RedisUtils;
import tech.icoding.commons.tools.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Auther: GaoDong
 * @Date: 2021/10/31
 * @Description: 表单重复提交AOP拦截处理
 */
@Slf4j
@Aspect
@Component
public class NoRepeatSubmitAspect {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 【环绕通知】 用于拦截指定方法，判断用户表单保存操作是否属于重复提交
     *
     *     定义切入点表达式： execution(public * (…))
     *     表达式解释： execution：主体    public:可省略   *：标识方法的任意返回值  任意包+类+方法(…) 任意参数
     *
     *      com.cxwm.modules.*.*Controller.*： 标识AOP所切服务的包名，即需要进行横切的业务类
     *      .*Controller ： 标识类名，*即所有类
     *      .*(..) ： 标识任何方法名，括号表示参数，两个点表示任何参数类型
     *
     * @param joinPoint：切入点对象
     * @param noRepeatSubmit:自定义的注解对象
     * @return: java.lang.Object
     */
    @Around("execution( * tech.icoding.*.controller..*(..)) && @annotation(noRepeatSubmit)")
    public Object doAround(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            // 拿到ip地址、请求路径、token
            StringBuilder builder = new StringBuilder();
            String ip = IpUtils.getIpAddr(request);
            String url = request.getRequestURI();
            String token = request.getHeader("Authorization");
            // 现在时间
            long now = System.currentTimeMillis();
            // 自定义key值方式
            String key = "REQUEST_FORM:" + builder.append(token).append(url).append(ip).toString().replace(":",".");
            String value = (String)redisUtils.get(key);
            if (StringUtils.isNotBlank(value)) {
                // 上次表单提交时间
                long lastTime = Long.parseLong(value);
                // 如果现在距离上次提交时间小于设置的默认时间 则 判断为重复提交  否则 正常提交 -> 进入业务处理
                if ((now - lastTime) > noRepeatSubmit.time()) {
                    // 非重复提交操作 - 重新记录操作时间
                    redisUtils.set(key, String.valueOf(now));
                    // 进入处理业务
                    return joinPoint.proceed();
                } else {
                    return new RenException("请勿重复提交!");
                }
            } else {
                // 这里是第一次操作
                redisUtils.set(key, String.valueOf(now));
                return joinPoint.proceed();
            }
        } catch (Throwable throwable) {
            if(throwable instanceof RenException){
                log.error(((RenException) throwable).getMsg());
                return new RenException(((RenException) throwable).getCode(),((RenException) throwable).getMsg());
            }else if(throwable instanceof NoHandlerFoundException){
                return new RenException(404, "路径不存在，请检查路径是否正确");
            }else if(throwable instanceof AuthenticationException){
                return new RenException("没有权限，请联系管理员授权");
            }else if(throwable instanceof DuplicateKeyException){
                return new RenException("数据库中已存在该记录");
            }else {
                log.error(throwable.getMessage(), throwable);
                return new RenException("系统异常!");
            }
        }
    }
}
