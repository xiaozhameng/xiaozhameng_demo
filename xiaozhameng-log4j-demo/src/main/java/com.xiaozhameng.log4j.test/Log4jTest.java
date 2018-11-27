package com.xiaozhameng.log4j.test;


import org.junit.Test;

/**
 * Created by xiaozhameng on 2017/1/12.
 */
public class Log4jTest extends BaseTest{

    /**
     * 测试信息
     * logger对象来自于BaseTest，这样在web层Controller中就不用每个都自己创建对象了
     *
     * 日志级别格式等控制在log4j.properties 配置文件
     */
    @Test
    public void testLog4j(){
        logger.debug("这个是logger debug 输出");
        logger.info("这个是logger info 输出");
        logger.warn("这个是logger warn 输出");
        logger.error("这个是logger error 输出");
    }

}
