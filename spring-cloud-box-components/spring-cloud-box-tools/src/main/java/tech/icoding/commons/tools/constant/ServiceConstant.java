/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.tools.constant;

/**
 * 微服务名称常量
 *
 * @author Mark cxwm
 * @since 1.0.0
 */
public interface ServiceConstant {
    /**
     * 系统服务
     */
    String BIZ_SYSTEM_SERVER = "system-service-server";
    /**
     * 授权服务
     */
    String WMS_AUTH_SERVER = "spring-cloud-boxbiz-auth";
    /**
     * 物资库服务
     */
    String WMS_WZK_ADMIN = "spring-cloud-boxwzk-admin";
    /**
     * 专业仓服务
     */
    String WMS_ZYC_ADMIN = "spring-cloud-boxzyc-admin";

    /**
     * 物资库服务
     */
    String WZK_INTELLIGENT_SERVER = "wzk-intelligent-server";


    /**
     * 专业仓业务公共服务
     */
    String WMS_COMMON_SERVER = "spring-cloud-boxcommon-server";

}