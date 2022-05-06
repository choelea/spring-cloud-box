package tech.icoding.commons.platform.utils;

import com.huawei.it.eip.ump.client.producer.Producer;
import com.huawei.it.eip.ump.client.producer.SendResult;
import com.huawei.it.eip.ump.common.exception.UmpException;
import com.huawei.it.eip.ump.common.message.Message;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: GaoDong
 * @Date: 2021/7/6
 * @Description: 测试
 */
public class ProducerTest {
    public static void main(String[] args) throws UmpException, UnsupportedEncodingException {
        Producer producer = new Producer();
        producer.setUmpNamesrvUrls("<MQS Name ServerIP:9776>"); // 设置MQS的服务器地址
        producer.setAppId("XXXXID"); // 设置客户端账号
        producer.setAppSecret("*****"); // 设置客户端密钥
        producer.setTopic("Topic Name"); // 设置Topic Name
        producer.setEncryptTransport(false); // 设置是否需要加密传输
        producer.start(); // 启动消息生产者，建议在应用程序启动时调用（即执行此代码）
        // 发送消息的业务逻辑
        Message message = new Message();
        message.setBusinessId("id-xxx"); // 设置消息业务标示，便于追踪消息轨迹
        message.setTags("tag-xxx"); // 设置消息标签
        message.setBody("body".getBytes("UTF-8")); // 消息体，推荐使用
        //JSON.toJSONString(businessDo).getBytes("UTF-8"); //，注：MQS不对二进制消息进行转换，
        //Producer和Consumer需协商好序列化和反序列化方式
        try {
            SendResult sendResult = producer.send(message);
            if (sendResult.isSuccess()) {
            // 发送成功的逻辑处理
            } else {
            // 发送失败的逻辑处理
            }
        } catch (UmpException e) {
        // 异常处理
        }
        producer.shutdown(); // 关闭消息生产者，建议在应用程序关闭的时候调用
    }

}
