/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */
package tech.icoding.commons.security.service.impl;

import tech.icoding.commons.security.enums.UserStatusEnum;
import tech.icoding.commons.security.feign.AccountFeignClient;
import tech.icoding.commons.security.service.GDUserDetailsService;
import tech.icoding.commons.security.user.UserDetail;
import tech.icoding.commons.tools.exception.ErrorCode;
import tech.icoding.commons.tools.exception.RenException;
import tech.icoding.commons.tools.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService
 *
 * @author Mark cxwm
 */
@Service
public class RenUserDetailsServiceImpl implements GDUserDetailsService {
    @Autowired(required=false)
    private AccountFeignClient accountFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDetail> result = accountFeignClient.getByUsername(username);
        UserDetail userDetail = result.getData();
        if(userDetail == null){
            throw new RenException(ErrorCode.ACCOUNT_NOT_EXIST);
        }

        //账号不可用
        if(userDetail.getStatus() == UserStatusEnum.DISABLE.value()){
            userDetail.setEnabled(false);
        }

        return userDetail;
    }

    @Override
    public UserDetails loadMemberByIscForThird(String username) {
        Result<UserDetail> result = accountFeignClient.getByIscUsername(username);
        UserDetail userDetail = result.getData();
        if(userDetail == null){
            throw new RenException(ErrorCode.ACCOUNT_NOT_EXIST);
        }

        //账号不可用
        if(userDetail.getStatus() == UserStatusEnum.DISABLE.value()){
            userDetail.setEnabled(false);
        }

        return userDetail;
    }

    @Override
    public UserDetails loadMemberByOpenId(String openId) {
        return null;
    }

    @Override
    public UserDetails loadMemberByMobile(String mobile) {
        return null;
    }
}
