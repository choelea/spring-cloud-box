package tech.icoding.commons.platform.utils;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/7
 * @Description: 生产者测试
 */
public class ProductTopicActivaMq {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.88:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("mqtt_to_mq");
        MessageProducer producer = session.createProducer(topic);
        Message message = session.createTextMessage("hello topic message1");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();

    }
}
