package xiaozhameng.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/6/9
 */
public class ThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    /**
     * 多线程相关内容学习
     *
     * 一、线程机制：
     *      并发编程可以使得我们将程序划分为多个分离的，独立运行的任务。通过使用多线程机制，这些独立的任务（也被称之为子任务）中的每一个将由执行线程来驱动
     *      一个线程就是进程中一个单一的顺序控制流，因此单个的进程可以拥有多个并发执行的任务。
     *
     * 二、任务的定义
     *      线程可以驱动任务，因此我们需要一种描述任务的方法。这可以由Runnable接口来提供。要想定义一个任务，只需要实现Runnable 接口，并复写run（）方法，例如LiftOff任务将显示倒计时
     *
     * 三、Thread 类
     *      将Runnable 对象转换成工作任务的传统方式，就是把它提交一个Thread 构造器，通过Thread 实例来驱动任务
     */

    @Test
    public void test_Runnable() {

        System.out.println("Thread run: " + Thread.currentThread());

        // runnable
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread run: " + Thread.currentThread());
            }
        }.run();

    }

    @Test
    public void test_thread_() {

        System.out.println("Thread run: " + Thread.currentThread());

        // runnable
        new Thread().start();

    }

    @Test
    public void testLifftOff() {
        LiftOff launch = new LiftOff();
        launch.run();
    }

    @Test
    public void testBasicThread(){
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff");
    }

    @Test
    public void testMoreBasicThread(){
        for (int i= 0; i<5 ;i++){
            Thread thread = new Thread(new LiftOff());
            thread.start();
        }
        System.out.println("Waiting for LiftOff");

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (Exception e){

        }
    }

    @Test
    public void test_MyCallable(){
        int taskCount = 1000000;

        // 假设每个任务耗时1秒钟，执行完10个任务，需要耗时10秒

        // 使用多线程调用
        long timeStart = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        // ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futureResult = new ArrayList<Future<String>>();
        for (int i =0; i<taskCount ; i++){
            logger.info("开始执行任务：{}",i);
            Future<String> submitResult = executorService.submit(new MyCallable(i));
            futureResult.add(submitResult);
        }

        for (Future<String> sigleFuture : futureResult){
            logger.info("sigleFuture.isDone() :{}", sigleFuture.isDone());
            // if (sigleFuture.isDone()){
            //     try {
            //         logger.info("Future has finished task:{}", sigleFuture.get());
            //     } catch (InterruptedException e) {
            //         e.printStackTrace();
            //     } catch (ExecutionException e) {
            //         e.printStackTrace();
            //     }
            // }
            try {
                logger.info("task result :{}", sigleFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("使用多线程执行{}个任务，每个任务大概耗时1秒，最终耗费时间：{}",taskCount, ((endTime-timeStart)/ 1000.00));
    }

    @Test
    public void testThreadExecutTask(){
        long timeStart = System.currentTimeMillis();
        int taskCount = 100;
        for (int i=0; i< taskCount; i++){
            try {
                logger.info("正在执行第{}个任务", i);
                Thread.sleep(1000);
                logger.info("第{}个任务执行结束", i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("使用单个线程执行{}个任务，每个任务大概耗时1秒，最终耗费时间：{}",taskCount, ((endTime-timeStart)/ 1000.00));
    }


    @Test
    public void testThreadLocal(){
        ThreadLocal threadLocal = new ThreadLocal();
    }
}


