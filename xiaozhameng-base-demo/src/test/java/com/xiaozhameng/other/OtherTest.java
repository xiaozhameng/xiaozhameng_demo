package com.xiaozhameng.other;

import org.junit.Test;

import java.util.*;

public class OtherTest {

    public static void main(String[] args) {
        try {
            throw new RuntimeException("模拟异常");
        }catch (Exception e){
            System.out.println("发生了异常：" + e.getMessage());
        }finally {
            System.out.println("这里执行最后的代码语句");
        }
    }

    @Test
    public void testOtherCommon(){
        Exception exception = new RuntimeException("运行时异常");
        Error error = new Error("错误");
        Map<String,String> hashMap = new HashMap<String,String>();
        Collection<String> collection = new ArrayList<String>();
    }

    @Test
    public void testJava8(){
        int[] datas = {1,2,3};

    }
}
