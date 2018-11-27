package com.xiaozhameng.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;

/**
 * <p>Description: </p>
 * <p>Copyright: @2017</p>
 * <p>Company: xiaozhameng</p>
 *
 * @author xiaozhameng
 * @version V1.0 2017/10/5
 */
public class PointToPointProducer extends PointToPoint {

    /**
     * 构造函数
     * @param pointName
     * @throws Exception
     */
    public PointToPointProducer(String pointName) throws Exception {
        super(pointName);
    }

    /**
     * @param @param  Object
     * @throws
     * @Title: sendMessage
     * @Description: 生产消息
     */
    public void sendMessage(Serializable message) throws Exception {
        if (this.channel != null){
            logger.info("PointToPointProducer sendMessage:",message);
            channel.basicPublish("", pointName, null, SerializationUtils.serialize(message));
        }else
            throw new RuntimeException("PointToPointProducer.channel is null Exception");
    }
}
