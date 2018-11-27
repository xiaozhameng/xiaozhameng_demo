package com.xiaozhameng.concurrent;

import org.junit.Test;
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池的使用测试
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                //        new ThreadPoolExecutor(5,10,5, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
                new ThreadPoolExecutor(5,10,30, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(21),new DefaultManagedAwareThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        for (int i=0; i<50; i++){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "前线程开始执行。。。");
                        TimeUnit.SECONDS.sleep(40);
                        //Thread.sleep(30*1000);
                        System.out.println(Thread.currentThread().getName() + "前线程执行结束");
                    }catch (Exception e){
                        System.out.println("发生异常："+ e);
                    }
                }
            });
            System.out.println("已经有"+(i+1)+"个任务了，当前线程池的属性:" + executor);
        }
    }

}
