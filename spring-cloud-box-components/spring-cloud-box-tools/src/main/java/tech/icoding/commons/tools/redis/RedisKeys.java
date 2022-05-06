/**
 * Copyright (c) 2020 All rights reserved.
 * <p>
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.tools.redis;

/**
 * @author Mark cxwm
 * @since 1.0.0
 */
public class RedisKeys {
    /**
     * 系统参数Key
     */
    public static String getSysParamsKey() {
        return "sys:params";
    }

    /**
     * 登录验证码Key
     */
    public static String getLoginCaptchaKey(String uuid) {
        return "sys:captcha:" + uuid;
    }

    /**
     * 系统日志Key
     */
    public static String getSysLogKey() {
        return "sys:log";
    }

    /**
     * 用户菜单导航Key
     */
    public static String getUserMenuNavKey(Long userId, String language) {
        return "sys:user:nav:" + userId + "_" + language;
    }

    /**
     * 用户菜单导航Key
     */
    public static String getUserMenuNavKey(Long userId) {
        return "sys:user:nav:" + userId + "_*";
    }

    /**
     * 用户权限标识Key
     */
    public static String getUserPermissionsKey(Long userId) {
        return "sys:user:permissions:" + userId;
    }

    /**
     * ISC用户权限标识Key
     */
    public static String getIscUserPermissionsKey(Long userId) {
        return "sys:isc:user:permissions:" + userId;
    }

    /**
     * 物资库订单
     */
    public static String getOrderKey(Long orderId) {
        return "wzk:order:" + orderId;
    }

    /**
     * 项目数据Key
     */
    public static String getProjectKey(String projectCode) {
        return "wzk:project:" + projectCode;
    }

    /**
     * ISC用户菜单包括数据权限Key
     */
    public static String getIscUserAppMenuKey(Long userId) {
        return "sys:isc:user:app:menu:" + userId;
    }

    /**
     * 获取ISC所有组织机构Key
     */
    public static String getAllIscOrgKey() {
        return "sys:isc:org:all";
    }

    /**
     * 获取数据超市Token的Key
     * @return 返回Token
     */
    public static String getDataStoreTokenKey(){
        return "data:store:token:key";
    }

    /**
     * 用户工厂的Key
     */
    public static String getUserFactory(Long userId){return "wzk:user:factory";};
}
