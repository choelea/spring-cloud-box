/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {
    String value() default "";
}