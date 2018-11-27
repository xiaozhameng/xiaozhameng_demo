package com.xiaozhameng.kafka.config;

/**
 * Created by xiaozhameng on 2017/1/18.
 * kafka 的配置信息
 */
public interface KafkaProperties {

    String zkConnect = "10.22.10.139:2181";
    String groupId = "group1";
    String topic = "topic1";
    String kafkaServerURL = "10.22.10.139";
    int kafkaServerPort = 9092;
    int kafkaProducerBufferSize = 64 * 1024;
    int connectionTimeOut = 20000;
    int reconnectInterval = 10000;
    String topic2 = "topic2";
    String topic3 = "topic3";
    String clientId = "SimpleConsumerDemoClient";

}
