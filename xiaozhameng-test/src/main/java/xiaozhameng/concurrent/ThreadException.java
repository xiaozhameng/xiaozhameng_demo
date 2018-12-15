package xiaozhameng.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadException {
    /**
     * 在驱动线程一层捕获执行线程的异常
     * 结果是，异常逃逸
     */
    @Test
    public void testExceptionThread() {

        try {
            ExecutorService threadPool = Executors.newCachedThreadPool();
            threadPool.submit(new ExceptionThread<String>());
        } catch (Exception e) {
            System.out.println("驱动线程捕获到执行线程的异常："+ e);
        }
    }

    /**
     * 自定义线程工厂类，设置异常处理器
     */
    @Test
    public void testExceptionThread_ExceptionHandler() {

        try {
            ExecutorService threadPool = Executors.newFixedThreadPool(5,new MyHandlerThreadFactory());
            threadPool.submit(new ExceptionThreadForRunnable());
        } catch (Exception e) {
            System.out.println("驱动线程捕获到执行线程的异常："+ e);
        }
    }

}

/**
 * 定义一个类，总是抛出异常
 */
class ExceptionThread<T> implements Callable <T>{

    @Override
    public T call() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "线程执行时抛出了异常");
        throw new RuntimeException(name + "线程执行时抛出了异常");
    }
}

/**
 * 定义一个类，总是抛出异常
 */
class ExceptionThreadForRunnable implements Runnable{

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        throw new RuntimeException(name + "线程执行时抛出了异常");
    }
}


/**
 * 自定义线程工厂
 */
class MyHandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+ " creating new Thread");
        Thread thread= new Thread(r);
        System.out.println(thread.getName() + " created");

        // 设置异常处理器
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("我是自定义异常处理器，这里假设我已经处理了异常！");
            }
        });
        return thread;
    }

}


