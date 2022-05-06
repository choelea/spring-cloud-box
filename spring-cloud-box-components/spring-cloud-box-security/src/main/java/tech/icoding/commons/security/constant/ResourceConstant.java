/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */
package tech.icoding.commons.security.constant;

/**
 * 资源常量
 *
 * @author Mark cxwm
 */
public class ResourceConstant {
    /**
     * 不进行认证的URL
     */
    public static final String [] IGNORING_URLS = {
        "/actuator/**",
        "/v2/api-docs",
        "/webjars/**",
        "/swagger/**",
        "/swagger-resources/**",
        "/doc.html"
    };

}
