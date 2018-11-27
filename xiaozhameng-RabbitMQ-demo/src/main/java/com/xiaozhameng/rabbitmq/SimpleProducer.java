package com.xiaozhameng.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xiaozhameng.util.ConfigUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生成者
 */
public class SimpleProducer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列
        channel.queueDeclare(ConfigUtil.TEST_QUEUE_01, false, false, false, null);
        String message = "Hello RabbitMQ";
        //发送消息到队列中
        for (int i = 0; i < 9000; i++) {
            message = "MESSAGE--TIMES-"+ i;
            channel.basicPublish("", ConfigUtil.TEST_QUEUE_01, null, message.getBytes("UTF-8"));
            System.out.println("SimpleProducer Send +'" + message + "'");
            Thread.sleep(10);
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}