package com.xiaozhameng.algorithm;

import com.xiaozhameng.TestUtil;
import org.junit.Test;

import java.util.Random;

/**
 * 排序算法
 */
public class DataSortTest extends TestUtil {

    public static Integer[] dataList = new Integer[10];
    static {
        int from = 11;
        int end = 100;
        Random random = new Random(100);
        for (int x=0; x<10;x++){
            int data = random.nextInt(end-from);
            dataList[x] = (data + from);
        }
    }

    /**
     * 冒泡排序
     * 冒泡排序（Bubble Sort）是一种最简单的排序算法，其基本思想是迭代地对输入序列中的第一个元素到最后一个元素进行两两比较，当需要时交换
     * 这两个元素的位置。该过程持续迭代到这一趟排序过程中不需要交换操作为止
     *
     * 相邻的两个进行比较
     *
     * 源数据：38,50,87,39,45,19,89,57,45,56
     * 第一遍：38,50,87,39,45,19,89,57,45,56
     * 第二遍：38,50,87,39,45,19,89,57,45,56
     * 第二遍：38,50,39,87,45,19,89,57,45,56
     * ……
     */
    @Test
    public void test_BubbleSort(){
        System.out.println("原有数据列："+ printArray(dataList));
        for (int n = dataList.length; n>0; n--){
            for (int i=0; i< n -1 ;i++){
                // 比较第i个数和i+1个数，然后交换，遍历一次之后，最大的数据被移动到了末尾
                if (dataList[i] > dataList[i+1]){
                    int temp;
                    temp = dataList[i];
                    dataList[i] = dataList[i+1];
                    dataList[i+1] = temp;
                }
            }
        }
        System.out.println("冒泡后数据："+ printArray(dataList));
    }

}
