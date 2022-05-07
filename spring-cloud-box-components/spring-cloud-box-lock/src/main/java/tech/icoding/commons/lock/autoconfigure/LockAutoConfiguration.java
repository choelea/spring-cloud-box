package tech.icoding.commons.lock.autoconfigure;

import tech.icoding.commons.lock.LockExecutor;
import tech.icoding.commons.lock.LockTemplate;
import tech.icoding.commons.lock.RedisTemplateLockExecutor;
import tech.icoding.commons.lock.aop.LockAnnotationAdvisor;
import tech.icoding.commons.lock.aop.LockInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 分布式锁自动配置器
 *
 * @author Mark cxwm zengzh TaoYu
 */
@Configuration
public class LockAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(RedisTemplate.class)
    public LockExecutor lockExecutor(RedisTemplate redisTemplate) {
        RedisTemplateLockExecutor redisTemplateLockExecutor = new RedisTemplateLockExecutor();
        redisTemplateLockExecutor.setRedisTemplate(redisTemplate);
        return redisTemplateLockExecutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public LockTemplate lockTemplate(LockExecutor lockExecutor){
        LockTemplate lockTemplate = new LockTemplate();
        lockTemplate.setLockExecutor(lockExecutor);
        return lockTemplate;
    }
    @Bean
    @ConditionalOnMissingBean
    public LockAnnotationAdvisor lockAnnotationAdvisor(LockInterceptor lockInterceptor) {
        return new LockAnnotationAdvisor(lockInterceptor);
    }

    @Bean
    @ConditionalOnMissingBean
    public LockInterceptor lockInterceptor(LockTemplate lockTemplate) {
        LockInterceptor lockInterceptor = new LockInterceptor();
        lockInterceptor.setLockTemplate(lockTemplate);
        return lockInterceptor;
    }

}
