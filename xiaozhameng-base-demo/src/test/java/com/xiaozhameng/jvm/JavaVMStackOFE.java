package com.xiaozhameng.jvm;

import org.junit.Test;

/**
 * Java 虚拟机栈和本地方法栈溢出测试
 *
 * 在HotSpot虚拟机中，虚拟机栈和本地方法栈合二为一，因此对于HotSpot虚拟来说，栈容量的大小设置只取决于 -Xss参数设置，关
 * 于虚拟机栈和本地方法栈，Java虚拟机规范中定义了两种异常
 * 1、如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverFlowError 异常
 * 2、如果虚拟机在扩展时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。
 */
public class JavaVMStackOFE {

    private int stackLenth = 1;

    /**
     * VM Agars : -Xss128k
     * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverFlowError 异常
     *
     * ---------- 异常堆栈
     * 方法中已经有935个局部变量
     * Disconnected from the target VM, address: '127.0.0.1:57839', transport: 'socket'
     *
     * java.lang.StackOverflowError
     * 	at sun.nio.cs.UTF_8.updatePositions(UTF_8.java:77)
     * 	at sun.nio.cs.UTF_8.access$200(UTF_8.java:57)
     * 	at sun.nio.cs.UTF_8$Encoder.encodeArrayLoop(UTF_8.java:636)
     * 	at sun.nio.cs.UTF_8$Encoder.encodeLoop(UTF_8.java:691)
     * 	at java.nio.charset.CharsetEncoder.encode(CharsetEncoder.java:579)
     * 	at sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:271)
     * 	at sun.nio.cs.StreamEncoder.write(StreamEncoder.java:125)
     * 	at java.io.OutputStreamWriter.write(OutputStreamWriter.java:207)
     * 	at java.io.BufferedWriter.flushBuffer(BufferedWriter.java:129)
     * 	at java.io.PrintStream.write(PrintStream.java:526)
     * 	at java.io.PrintStream.print(PrintStream.java:669)
     * 	at java.io.PrintStream.println(PrintStream.java:806)
     * 	at com.xiaozhameng.jvm.JavaVMStackOFE.testStack_StackOverFlowError(JavaVMStackOFE.java:24)
     *
     */
    @Test
    public void testStack_StackOverFlowError(){
        stackLenth ++;
        System.out.println("方法中已经有"+stackLenth+"个局部变量");
        testStack_StackOverFlowError();
    }

    /**
     * 空方法
     */
    private void dontStop(){
        while (true){
        }
    }

    /**
     * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverFlowError 异常
     */
    public void testStack_OutOfMemoryError(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * 执行该方法有较大的风险，可能会导致操作系统假死
     * @param args
     */
    public static void main(String[] args) {
        new JavaVMStackOFE().testStack_OutOfMemoryError();
    }
}
