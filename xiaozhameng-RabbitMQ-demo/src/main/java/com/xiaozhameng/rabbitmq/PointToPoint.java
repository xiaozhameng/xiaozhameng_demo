package com.xiaozhameng.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xiaozhameng.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 作者 maimangxiaozhameng
 * @version 1.0
 * @date 创建时间：2017年8月11日 下午2:21:27
 * @parameter
 * @return
 */
public abstract class PointToPoint {

    Logger logger = LoggerFactory.getLogger(PointToPoint.class);

    protected Channel channel;
    protected Connection connection;
    protected String pointName;

    /**
     * 获取一个队列的连接
     *
     * @param pointName
     * @throws Exception
     */
    public PointToPoint(String pointName) throws Exception {
        this.pointName = pointName;

        //创建连接工厂
        ConnectionFactory cf = new ConnectionFactory();

        //设置rabbitmq服务器地址
        cf.setHost(ConfigUtil.MQ_HOST);

        //获取一个新的连接
        connection = cf.newConnection();

        //创建一个通道
        channel = connection.createChannel();

        //申明一个队列，如果这个队列不存在，将会被创建
        channel.queueDeclare(pointName, false, false, false, null);
    }


    /**
     * @return void    返回类型
     * @throws Exception 设定文件
     * @Title: close
     * @Description: 其实在程序完成时一般会自动关闭连接，但是这里提供手动操作的入口，
     */
    public void close() throws Exception {
        if (this.channel != null){
            this.channel.close();
        }
        if (this.connection != null){
            this.connection.close();
        }
    }
}