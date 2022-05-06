/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.security.feign;

import tech.icoding.commons.security.feign.fallback.AccountFeignClientFallbackFactory;
import tech.icoding.commons.security.user.UserDetail;
import tech.icoding.commons.tools.constant.ServiceConstant;
import tech.icoding.commons.tools.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 账号接口
 *
 * @author Mark cxwm
 */
@FeignClient(name = ServiceConstant.BIZ_SYSTEM_SERVER, contextId = "AccountFeignClient", fallbackFactory = AccountFeignClientFallbackFactory.class)
public interface AccountFeignClient {

    /**
     * 根据用户名，获取com_user表用户信息
     * @param username  用户名
     */
    @PostMapping("sys/user/getByUsername")
    Result<UserDetail> getByUsername(@RequestParam("username") String username);

    /**
     * 根据用户名，获取isc_user表用户信息
     * @param username  用户名
     */
    @PostMapping("sys/user/getByIscUsername")
    Result<UserDetail> getByIscUsername(@RequestParam("username") String username);
}