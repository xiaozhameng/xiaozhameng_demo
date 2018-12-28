package xiaozhameng.thread;

import org.junit.Test;
import xiaozhameng.TestUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程学习测试类
 */
public class ThreadCommonTest extends TestUtil {

    @Test
    public void testCommon(){
        Collections.synchronizedMap(new HashMap<>());

        ConcurrentHashMap map = new ConcurrentHashMap();
    }

    @Test
    public void threadLocalTest(){
        ThreadLocal<String> threadLocalTest1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocalTest2 = new ThreadLocal<>();
        ThreadLocal<String> threadLocalTest3 = new ThreadLocal<>();
        new Thread(() -> {
            threadLocalTest1.set("aaa");
            threadLocalTest2.set("bbb");
            threadLocalTest3.set("ccc");
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
        }).start();
    }

}
