package tech.icoding.commons.platform.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 */
@Component
@Slf4j
public class ApplicationContextAwareImpl implements ApplicationContextAware {


    /**
     * 实现该接口用来初始化应用程序上下文
     * 该接口会在执行完毕@PostConstruct的方法后被执行
     * <p>
     * 接着，会进行Mapper地址扫描并加载，就是RequestMapping中指定的那个路径
     *
     * @param applicationContext 应用程序上下文
     * @throws BeansException beans异常
     */
    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        log.info("初始化应用程序上下文");
        ApplicationContextUtils.getInstance().setCfgContext((ConfigurableApplicationContext) applicationContext);
    }

}

