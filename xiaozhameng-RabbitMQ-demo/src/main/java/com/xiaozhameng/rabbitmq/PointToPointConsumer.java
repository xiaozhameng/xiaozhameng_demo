package com.xiaozhameng.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2017/10/5
 */
public class PointToPointConsumer extends PointToPoint implements Runnable {

    public PointToPointConsumer(String pointName) throws Exception {
        super(pointName);
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(pointName, true, new DefaultConsumer(this.channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    logger.info("PointToPointConsumer run - consumeMessage",new String(body));
                }
            });
        } catch (Exception e) {
            logger.error("PointToPointConsumer 处理消息发生异常：", e);
        }
    }
}
