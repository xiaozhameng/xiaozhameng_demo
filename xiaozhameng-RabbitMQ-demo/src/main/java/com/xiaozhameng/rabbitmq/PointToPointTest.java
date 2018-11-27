package com.xiaozhameng.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2017/10/5
 */
public class PointToPointTest {

    Logger logger = LoggerFactory.getLogger(PointToPointTest.class);

    public static void main(String[] args) {
        try {
            // 生产者产生消息--单独的线程
            PointToPointProducer producer = new PointToPointProducer("POINT-TO-POINT");
            for (int i = 0; i < 10000; i++) {
                MessageTest message = new MessageTest();
                message.setMessageName("TestMessage");
                message.setMessageBody("MessageBody");
                message.setMessageCode("000000");
                message.setMessageDesc("messageDesc");
                producer.sendMessage(message);
            }

            // 消费者消费消息
            new Thread(new PointToPointConsumer("POINT-TO-POINT")).start();

        }catch (Exception e){
            System.out.println("发生了异常了----");
        }
    }

}
