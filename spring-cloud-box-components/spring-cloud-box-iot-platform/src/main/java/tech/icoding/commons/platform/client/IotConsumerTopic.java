package tech.icoding.commons.platform.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 * @Description: MQ消费者
 */
@Slf4j
public class IotConsumerTopic {
    /**
     * Topic消费者
     * @param url tcp://192.168.0.88:61616
     * @param topicName mqtt_to_mq
     * @return
     * @throws JMSException
     */
    public static void iotConsumerTopic(String url,String topicName) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        log.info("读取Topic信息{}",textMessage.getText());
                        message.acknowledge(); //告知activemq应用已收到消息
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        session.commit();
        session.close();
        consumer.close();
        connection.close();
    }
}
