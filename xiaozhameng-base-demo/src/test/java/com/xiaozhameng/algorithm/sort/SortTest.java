package com.xiaozhameng.algorithm.sort;

import com.xiaozhameng.TestUtil;
import org.junit.Test;

/**
 * 排序算法测试
 * <p>
 * 案例中所有的排序基于从小到大进行排序，也即小的在最前面，大的在最后面
 */
public class SortTest extends TestUtil {

    public static int[] datas = {2, 9, 3, 4, 8, 3};

    /**
     * 归并排序
     * <p>
     * 算法思想
     * 归并是把两个已经排序的文件合并成一个更大的已排序文件的过程
     * 选择是把一个文件分成包含k个最小元素和n-k个最大元素的两个部分的过程。
     * <p>
     * 选择和归并互为逆操作
     * 归并排序是快速排序的补充
     * 归并排序以连续的方式访问数据
     * 归并排序适用于链表排序
     * 归并排序对输入的初始次序不敏感
     */
    @Test
    public void test_mergeSort() {
        int[] temp = new int[datas.length];
        int start = 0;
        int end = datas.length -1;
        arraySort(datas,start,end,temp);
        System.out.println("归并排序结果：" + printArray(datas));
    }

    /**
     * 拆数组
     *
     * 递归代码，切记中止条件
     */
    public void arraySort(int[] data, int start,int end,int[] temp) {
        int mid = (start + end)/ 2;
        if (start < end){
            arraySort(data,start,mid,temp);
            arraySort(data,mid +1,end,temp);
            mergeSort(data,start,mid,end,temp);
        }
    }

    /**
     * 归并排序算法实现
     *
     * @param data  源数据
     * @param start 开始位置
     * @param mid   中间位置
     * @param end   数组长度
     * @param temp  临时数组
     */
    public void mergeSort(int[] data, int start, int mid, int end, int[] temp) {
        int i = start;
        int j = mid + 1;
        int t = 0;
        // 从左边取出一个数，跟右边的进行比较，把小的放进temp数组
        while (i<= mid && j<= end){
            if (data[i] <= data[j]){
                temp[t++] = data[i++];
            }else {
                temp[t++] = data[j++];
            }
        }
        // 将其他的元素填充到数组中
        while (i<= mid){
            temp[t++] = data[i++];
        }
        while (j<= end){
            temp[t++] = data[j++];
        }

        // 将temp中的元素全部拷贝到原数组中
        t = 0;
        while (start <= end){
            data[start ++] = temp[t++];
        }
    }


    /**
     * 插入排序
     * <p>
     * 算法思想
     * 每次从输入数据中移除一个元素，并将其插入到已经排序的序列的正确位置。直到所有的元素都插入有序序列中。
     * 可以看一下这篇博客：https://www.cnblogs.com/chengxiao/p/6103002.html
     */
    @Test
    public void test_insertionSort() {
        int i, j, v;
        /**
         * 从数组中取出一个元素，并插入到已经排好序的序列中
         */
        for (i = 2; i < datas.length; i++) {
            v = datas[i];

            // 已经排好序的序列
            j = i;
            while (datas[j - 1] > v && j >= 1) {
                datas[j] = datas[j - 1];
                j--;
            }
            datas[j] = v;
        }
        System.out.println("插入排序结果：" + printArray(datas));
    }

    /**
     * 选择排序：select Sort
     * <p>
     * 算法思想
     * ①、寻找序列中的最新值元素
     * ②、用当前的位置的值交换最小值
     * ③、对所有的元素重复上述操作，直到整个序列排序完成
     */
    @Test
    public void test_selectSort() {
        // 外层循环控制遍历最小元素的次数
        int i, j, min, temp;
        for (i = 0; i < datas.length - 1; i++) {
            min = i;
            // 内层循环遍历查找最小的元素
            for (j = i + 1; j < datas.length; j++) {
                if (datas[j] < datas[min]) {

                    // 交换元素：交换本轮比较中最小的元素与当前元素
                    temp = datas[j];
                    datas[j] = datas[min];
                    datas[min] = temp;

                    min = j;
                }
            }
        }
        System.out.println("选择排序结果：" + printArray(datas));
    }

    /**
     * 冒泡排序 Bubble Sort
     * <p>
     * 算法思想
     * 迭代地对输入序列中的第一个元素到最后一个元素进行两两比较。当需要时，交换这两个元素的位置。该过程只需迭代直到在一趟排序过程中不需要进行交换为止。
     * 冒泡排序得名于键值较小的元素如同气泡一样逐渐漂浮到序列的顶端。通常情况下，插入排序比冒泡排序有更好的性能
     */
    @Test
    public void test_BubbleSort() {
        int temp = 0;
        // 外层循环控制遍历次数：总共遍历datas.length -1 轮
        for (int pass = datas.length - 1; pass > 0; pass--) {
            for (int i = 0; i < pass; i++) {
                // 比较
                if (datas[i] > datas[i + 1]) {
                    temp = datas[i];
                    datas[i] = datas[i + 1];
                    datas[i + 1] = temp;
                }
            }
        }
        String dataStr = printArray(datas);
        System.out.println("冒泡排序结果：" + dataStr);
    }

}
