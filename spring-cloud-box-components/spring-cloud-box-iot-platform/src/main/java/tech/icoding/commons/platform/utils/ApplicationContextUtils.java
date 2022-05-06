package tech.icoding.commons.platform.utils;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 * @Description: 全局对象获取 单例模式
 */
public class ApplicationContextUtils {

    private static ApplicationContextUtils INSTANCE = new ApplicationContextUtils();

    private ConfigurableApplicationContext cfgContext;

    public static ApplicationContextUtils getInstance() {
        return INSTANCE;
    }

    public <T> T getBean(final Class<T> type) {
        return cfgContext.getBean(type);
    }

    public <T> T getBean(final String name) {
        return (T) cfgContext.getBean(name);
    }
    public <T> T getBean(final String name,final Class<T> type) {
        return (T) cfgContext.getBean(name,type);
    }
    public void registerBean(final String beanName, final Object obj) {
        cfgContext.getBeanFactory().registerSingleton(beanName, obj);
    }

    public void registerBean( final Object obj) {
        cfgContext.getBeanFactory().registerResolvableDependency(obj.getClass(), obj);
    }

    public ConfigurableApplicationContext getCfgContext() {
        return cfgContext;
    }

    public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
        this.cfgContext = cfgContext;
    }
}
