package xiaozhameng.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆内存溢出
 * VM Args： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\app\file\java-log\
 *
 * 启动参数设置Java堆大小为20m，不可扩展，通过设置-XX:HeapDumpOnOutOfMemoryError 参数
 * 可以让虚拟机在出现内存溢出时Dump出当前的内存堆转存储快照
 */
public class HeapOOM {
    static class OOMObject{
        private byte[] data = new byte[500];
    }
    public static void main(String[] args) {
        // list 引用保证GC Roots 到对象之间有可达路径
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add( new OOMObject());
            System.out.println("list中添加了"+list.size()+"个对象");
        }
    }
}
