package com.xiaozhameng.concurrent;

/**
 * 定义一个任务
 */
public class LiftOff implements Runnable{
    protected int countDown = 10;
    // 标识有多少个任务
    private static int taskCount = 0;
    // 标识符id 可以区分任务的多个实例，因为是final的，一旦创建之后，无法更改
    private final int id = taskCount ++;
    public LiftOff(){};
    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown >0 ? countDown : "LiftOff!") + "),";
    }

    @Override
    public void run() {
        while (countDown -- >0){
            System.out.println(status());
            Thread.yield();
        }
    }
}