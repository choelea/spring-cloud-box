/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.security.feign.fallback;

import feign.hystrix.FallbackFactory;
import tech.icoding.commons.security.feign.AccountFeignClient;
import tech.icoding.commons.security.user.UserDetail;
import tech.icoding.commons.tools.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 账号接口 FallbackFactory
 *
 * @author Mark cxwm
 */
@Slf4j
@Component
public class AccountFeignClientFallbackFactory implements FallbackFactory<AccountFeignClient> {
    @Override
    public AccountFeignClient create(Throwable throwable) {
        log.error("{}", throwable);

        return new AccountFeignClient() {

            @Override
            public Result<UserDetail> getByUsername(String username) {
                return new Result<>();
            }

            @Override
            public Result<UserDetail> getByIscUsername(String username) {
                return new Result<>();
            }
        };
    }
}
