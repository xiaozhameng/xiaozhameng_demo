package com.xiaozhameng.common.util.redis;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化类
 * Created by qianhao on 17/4/1.
 */
public class SerializeUtils {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    public static byte[] encode(Object object){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(object);
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("序列化对象失败："+ ToStringBuilder.reflectionToString(object),e);
        }
        finally {
            try {
                outputStream.close();
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                logger.error("关闭输出流失败", e);
            }
        }
        return null;
    }

    public static Object decode(byte[] data){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            return ois.readObject();
        } catch (IOException e) {
            logger.error("反序列化对象失败", e);
        } catch (ClassNotFoundException e) {
            logger.error("没找到对应的class类", e);
        } finally {
            try {
                inputStream.close();
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e) {
                logger.error("关闭输出流失败", e);
            }
        }
        return null;
    }
}
