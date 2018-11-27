package com.xiaozhameng.rabbitmq;


import com.rabbitmq.client.*;
import com.xiaozhameng.util.ConfigUtil;

import java.io.IOException;

public class SimpleCustomer {

    public static void main(String[] args) throws Exception {

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址

        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(ConfigUtil.TEST_QUEUE_01, false, false, false, null);
        System.out.println("SimpleCustomer Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("SimpleCustomer Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(ConfigUtil.TEST_QUEUE_01, true, consumer);

    }
}