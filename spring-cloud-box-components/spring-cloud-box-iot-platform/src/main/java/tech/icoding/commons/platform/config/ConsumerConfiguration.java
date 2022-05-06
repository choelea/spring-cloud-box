package tech.icoding.commons.platform.config;


import org.springframework.context.annotation.Configuration;
/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 * @Description: 监听topic、queue工厂配置
 */
@Configuration
public class ConsumerConfiguration {
//    @Value("${spring.activemq.broker-url}")
//    private String host;
//
//    @Bean
//    public ConnectionFactory getActiveMqConnection(){
//        return new ActiveMQConnectionFactory(host);
//    }
//
//    @Bean(name="queueListenerContainerFactory")
//    public JmsListenerContainerFactory queueListenerContailerFactory(ConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPubSubDomain(false);
//        return factory;
//    }
//    @Bean(name="topicListenerContainerFactory")
//    public JmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPubSubDomain(true);
//        return factory;
//    }
}
