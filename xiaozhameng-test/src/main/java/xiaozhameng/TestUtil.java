package xiaozhameng;

import java.util.Arrays;

/**
 * 测试辅助工具类
 */
public class TestUtil {

    /**
     * 数组的toString方法
     * @param dataList
     * @return
     */
    public String printArray(int[] dataList){
        return Arrays.toString(dataList);
    }

    public void swap(int[] A,int left,int right){
        if (left < 0 || right <0 || A.length == 0){
            return;
        }
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }

}
