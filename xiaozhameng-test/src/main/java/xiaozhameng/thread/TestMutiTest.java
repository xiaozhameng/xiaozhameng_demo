package xiaozhameng.thread;


import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * 多线程方法测试
 */
public class TestMutiTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testThreadPool(){
        int i = 0;
        while (i < 100){
            i++;

            // 模拟渠道请求
            Future<String> submit = executorService.submit(() -> {
                Thread.sleep(2000);
                return ("result-" + "" + UUID.randomUUID().toString());
            });

            // 操作是否完成
            boolean done = submit.isDone();
            if (done){
                System.out.println("thread finished");
            }else {
                System.out.println("submit finished");
            }
        }
    }

}
