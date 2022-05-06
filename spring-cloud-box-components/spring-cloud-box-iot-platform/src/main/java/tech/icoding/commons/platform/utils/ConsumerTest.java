package tech.icoding.commons.platform.utils;

import com.huawei.it.eip.ump.client.consumer.ConsumeStatus;
import com.huawei.it.eip.ump.client.consumer.Consumer;
import com.huawei.it.eip.ump.client.listener.MessageListener;
import com.huawei.it.eip.ump.common.exception.UmpException;
import com.huawei.it.eip.ump.common.message.Message;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/6
 * @Description: 消息订阅者
 */
public class ConsumerTest {
    public static void main(String[] args) throws UmpException {
        Consumer consumer = new Consumer();
        consumer.setUmpNamesrvUrls("192.168.0.88:61616"); // 设置统一消息平台的服务器地址
        consumer.setAppId("cxwm"); // 设置客户端账号
        consumer.setAppSecret("hdkj2021"); // 设置客户端密钥
        consumer.setTopic("mqtt_to_mq");// 设置 Topic Name
        consumer.setTags("*"); // 设置订阅消息的标签，可以指定消费某一类型的消息，默认*表示消费所有类型的消息
        consumer.setEncryptTransport(false);// 设置是否需要加密传输
        consumer.subscribe(new MessageListener() {
            public ConsumeStatus consume(Message message) throws UnsupportedEncodingException {
                // 消费消息的业务逻辑
                System.out.println("Receive: " + new String(message.getBody(), "UTF-8"));
                // 正常接收到消息后，请务必返回 CONSUME_SUCCESS，只有在业务处理失败才返回RECONSUME_LATER
                return ConsumeStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start(); // 启动消费者，建议在应用程序关闭时执行
        consumer.shutdown();//关闭此消费者。
    }

}
