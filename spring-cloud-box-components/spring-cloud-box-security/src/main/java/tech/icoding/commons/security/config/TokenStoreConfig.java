/**
 * Copyright (c) 2020 All rights reserved.
 *
 * 版权所有，侵权必究！
 */

package tech.icoding.commons.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * TokenStore
 *
 * @author Mark cxwm
 */
@Configuration
@AllArgsConstructor
public class TokenStoreConfig {
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
   /* @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public TokenStore tokenStore() {
        RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore();
        redisTemplateStore.setRedisTemplate(redisTemplate);
        return redisTemplateStore;
    }*/
}
