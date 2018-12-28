package xiaozhameng.javabase.file;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Description:I/O体系学习测试类 </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/6/3
 */
public class IOTest {

    private static final Logger logger = LoggerFactory.getLogger(IOTest.class);

    String ioTestStr = "我是一个测试字符串";


    /**
     * 从字节中读取数据：使用 ByteArrayInputStream
     */
    @Test
    public void test_ByteArrayInputStream() throws Exception{
        // 获取字符串
        byte[] data = ioTestStr.getBytes();
        // 将字节数组载入流
        ByteArrayInputStream bai = new ByteArrayInputStream(data);

        // 可以从流里面读取的数据还有多少？
        getAvailable(bai);

        // 是否支持标记
        logger.info("ByteArrayInputStream 是否支持标记：{}", bai.markSupported());
        readFromInputStreamWhireAvailable(bai,data.length);

        // 要关闭流资源
        closeInputStream(bai);
    }

    /**
     * InputStream
     * read（byte[] data）
     * read（byte[] data,int off,int len）
     * 两个方法测试
     */
    @Test
    public void test_InputStreamRead() throws Exception{
        InputStream inputStream = new ByteArrayInputStream(ioTestStr.getBytes());

        // 可以从流里面读取的数据还有多少？
        getAvailable(inputStream);

        // 一次读取两个字节
        byte[] resultByte = new byte[20];
        int read = inputStream.read(resultByte);
        while (read != -1){
            logger.info("inputStream.rea d(resultByte) result:{}",read);
            logger.info("读取到字节数组的数据为：{}，do something", resultByte);
            read = inputStream.read(resultByte);
        }
    }


    /**
     * 流里面还有多少可以读取的数据
     * @return
     */
    public int getAvailable(InputStream is) throws IOException{
        int result = is.available();
        logger.info("该输入流读取（或跳过）的剩余字节数：{}", result);
        return result;
    }

    /**
     * 输入流资源释放
     * @param is
     */
    public void closeInputStream(InputStream is){
        try {
            if (is != null){
                is.close();
            }
        }catch (Exception e){
            logger.error("输入流资源释放发生异常",e);
        }
    }

    /**
     * 从输入流中读取,一个字节一个字节读取，本方法只读取一个字节
     * 返回值为：读取的字节值，介于0-255 之间，若已经没有可以读取的数据，返回-1
     * @return
     */
    public int readFromInputStream(InputStream is) throws IOException {
        int read = is.read();
        logger.info("readFromInputStream result:{}", read);
        return read;
    }

    /**
     * 从输入流中读取,一个字节一个字节读取，本方法只读取一个字节
     * 返回值为：读取的字节值，介于0-255 之间，若已经没有可以读取的数据，返回-1
     * @return
     */
    public byte[] readFromInputStreamWhireAvailable(InputStream is,int dataLength) throws IOException {
        // 存贮读取的数据
        byte[] resultByte = new byte[dataLength];
        // 标识
        int po = 0;
        // 每次读取的值
        int result = -1;
        while( (result = readFromInputStream(is)) != -1){
            resultByte[po] =  (byte) result;
            po ++;
        }
        return resultByte;
    }

    /**
     * 输出流：ByteArrayOutputStream
     */
    @Test
    public void test_ByteArrayOutputStream() throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] dataByte = ioTestStr.getBytes();

        byteArrayOutputStream.write(dataByte);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        logger.info("result:{}",new String(bytes));
    }


}
