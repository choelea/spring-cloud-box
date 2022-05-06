/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 数据过滤注解
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**
     * 表的别名
     */
    String tableAlias() default "";

    /**
     * 用户ID
     */
    String userId() default "creator";

    /**
     * 部门ID
     */
    String deptId() default "dept_id";

}