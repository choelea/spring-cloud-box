/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户
 *
 * @author Mark cxwm
 */
public class SecurityUser {
    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return new UserDetail();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return getUser().getDeptId();
    }
}