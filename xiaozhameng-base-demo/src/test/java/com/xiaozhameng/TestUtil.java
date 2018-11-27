package com.xiaozhameng;

/**
 * 测试辅助工具类
 */
public class TestUtil {

    /**
     * 数组的toString方法
     * @param dataList
     * @return
     */
    public String printArray(Integer[] dataList){
        if (dataList == null || dataList.length <1){
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (Integer data: dataList){
            buffer.append(data).append(",");
        }
        return buffer.toString().substring(0,buffer.toString().length()-1);
    }


}
