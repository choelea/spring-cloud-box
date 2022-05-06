package tech.icoding.commons.tools.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: GaoDong
 * @Date: 2021/10/31
 * @Description: 校验表单重复提交
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {
    /**
     * 默认时间5秒
     */
    int time() default 5 * 1000;
}
