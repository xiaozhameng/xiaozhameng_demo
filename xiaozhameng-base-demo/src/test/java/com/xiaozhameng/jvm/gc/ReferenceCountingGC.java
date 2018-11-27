package com.xiaozhameng.jvm.gc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReferenceCountingGC {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员变量唯一的意义就是占用内存，以便在GC日志中看清楚是否被回收过
     *
     * VM 参数：-XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:D:\app\file\java-log\gc-log.log
     * 输出GC 详细日志
     */
    private byte[] bigSize = new byte[2*_1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;
        // 这时候，objA，objB的引用计数都为2

        // 引用失效，计数器减1
        objA = null;
        objB = null;

        // 假设这里发生GC
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }

    @Test
    public void testMap(){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("jf",null);

        Object jf = map.get("jf");

        System.out.println(map.size());
        System.out.println(jf);
    }
}
