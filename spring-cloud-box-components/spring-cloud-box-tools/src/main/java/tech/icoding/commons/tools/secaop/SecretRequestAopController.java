package tech.icoding.commons.tools.secaop;

import tech.icoding.aop.SecretAOPController;
import tech.icoding.commons.tools.exception.RenException;
import tech.icoding.commons.tools.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 继承请求加密切面类，主要是设置Controller类方法返回的对象设置
 * @author lxy
 */
@Aspect
@Slf4j
public class SecretRequestAopController extends SecretAOPController<Result> {

    @Override
    public void throwException(Throwable throwable) {
        if(throwable instanceof RenException){
            throw (RenException) throwable;
        }else if (throwable instanceof NoHandlerFoundException){
            throw new RenException(401,"请求资源不存在，获取请求路径不正确");
        }else if (throwable instanceof DuplicateKeyException){
            throw new RenException("数据库已经存在记录，请勿重复提交");
        }else{
            log.error("系统异常",throwable);
            throw  new RenException(throwable.getLocalizedMessage());
        }

    }
}
