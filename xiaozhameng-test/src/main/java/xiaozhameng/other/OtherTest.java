package xiaozhameng.other;

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
        StringBuffer buffer = new StringBuffer();
        buffer.append("hello");

        Integer a = 1000, b = 1000;
        System.out.println(a == b);//1
        Integer c = 100, d = 100;
        System.out.println(c == d);//2

        int aa = 1000, bb = 1000;
        System.out.println(aa == bb);//1
        int cc = 100, dd = 100;
        System.out.println(cc == dd);//2
    }

    @Test
    public void testDataTypeJava(){
        byte bd = "a".getBytes()[0];
        System.out.println("a".getBytes().length);
        System.out.println(bd);
    }

    @Test
    public void testJava8(){
        int[] datas = {1,2,3};

    }
}
