package xiaozhameng.algorithm;
import org.junit.Test;
import xiaozhameng.algorithm.dataStructure.BinaryTreeNode;
import xiaozhameng.algorithm.dataStructure.ListNode;

import java.util.Stack;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/7/7
 */
public class AlgorithmTest {

    public static void main(String[] args) {

    }

    /**
     * 一个二维数组打印
     * 1,2,3,4
     * 5,6,7,8
     * 9,10,11,12
     *
     * 打印顺序为：1,2,5,3,6,9,……
     * 斜对角打印
     */
    @Test
    public void testBytesTest(){
        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        // 行
        for (int x=0; x< arr.length;x++){
            // 列
            for (int y=0; y< arr[x].length; y++){
                if (x>0){
                    y = arr[x].length-1;
                }
                int m=x;
                int n=y;
                System.out.print(arr[m][n] +" ");
                while ((m < arr.length -1) && (n< arr[x].length) && (n>0)){
                    System.out.print(arr[m+1][n-1] +" ");
                    m++;
                    n--;
                }
            }
        }
    }


    @Test
    public void buildBinaryTree(){
        int[] datas = {62,88,58,47,35,73,51,99,37,93};
        // 构建二叉树
        BinaryTreeNode root = new BinaryTreeNode(datas[0]);
        for (int i=1;i<datas.length;i++){
            AlgorithmMethodTest.buildBinaryTree(root,datas[i]);
        }

        // 遍历二叉树
        AlgorithmMethodTest.inOrder(root);
    }


    /**
     * 二叉树的遍历
     * 前序遍历
     */
    @Test
    public void test_traversal_01_binarytree(){

    }

    @Test
    public void testStack_jdkStack(){
        Stack<String> stack = new Stack<String>();
        for (int i=0; i<10; i++){
            stack.push(i+"");
        }

        while (!stack.empty()){
            System.out.println("出栈：" + stack.pop());
        }
    }

    /**
     * 单向链表的遍历
     */
    @Test
    public void testListNode_Traversal(){
        ListNode headNode = new ListNode(10);
        int listNodeLength = headNode.listNodeLength(headNode);
        System.out.println("遍历该链表，长度为：" + listNodeLength);
    }

    /**
     * 单向链表的插入
     */
    @Test
    public void testListNode_insert(){
        ListNode headNode = new ListNode(10);
        for (int i=10; i<20; i++){
            ListNode listNode = new ListNode(i);
            int size = headNode.listNodeLength(headNode);
            headNode = headNode.insertInListNode(headNode,listNode,size + 1);
        }
        System.out.println("插入10个元素之后，总节点个数为：" +headNode.listNodeLength(headNode));
    }

}
