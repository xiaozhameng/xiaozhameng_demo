package com.xiaozhameng.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * <p>Description: 定义任务-有返回值</p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author 小蚱蜢
 * @version V1.0 2018/6/10
 */
public class MyCallable implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(MyCallable.class);

    private int id;
    public MyCallable(int id){
        this.id = id;
    }

    // 定义线程执行体
    @Override
    public String call() throws Exception {
        // 这里模拟调用远程接口，做一些操作，返回时间大概在1-3s之间
        int sleepTime = getThreadSleepTime();
        Thread.sleep(sleepTime);
        logger.info("模拟调用接口，耗时时间：{}, 模拟调用接口返回结果#{}",sleepTime,id);

        return "模拟调用接口返回结果";
    }

    /**
     * 模拟任务执行耗费时间
     * @return
     */
    private int getThreadSleepTime(){
        Random random = new Random();
        int nextInt = random.nextInt(3);
        int sleepTime = 1000 * nextInt;
        return sleepTime;
    }

}
