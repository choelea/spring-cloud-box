package tech.icoding.commons.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Auther: GaoDong
 * @Date: 2021/8/2
 * @Description:
 */
public interface GDUserDetailsService extends UserDetailsService {
    /**
     * 根据用户名查询com_user用户
     * @param username
     */
    UserDetails loadUserByUsername(String username);
    /**
     * 根据isc用户名查询isc_user用户
     * @param username
     */
    UserDetails loadMemberByIscForThird(String username);

    /**
     * 根据openId查询用户
     * @param openId
     */
    UserDetails loadMemberByOpenId(String openId);

    /**
     * 根据mobile查询用户
     * @param mobile
     */
    UserDetails loadMemberByMobile(String mobile);
}
