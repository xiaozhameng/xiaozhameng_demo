package xiaozhameng.algorithm;

import xiaozhameng.algorithm.dataStructure.BinarySearchTreeNode;
import xiaozhameng.algorithm.dataStructure.BinaryTreeNode;

/**
 * 数据结构操作的测试方法
 */
public class AlgorithmMethodTest {


    public static void buildBinaryTree(BinaryTreeNode root,int data){
        if (root == null || data == root.getData()){
            return;
        }
        // 在左子树上构建
        if (data < root.getData()){
            // 与左树root节点比较
            BinaryTreeNode rootLeft = root.getLeft();
            if (rootLeft == null){
                root.setLeft(new BinaryTreeNode(data));
            }else {
                buildBinaryTree(rootLeft,data);
            }
        }else {
            // 与左树root节点比较
            BinaryTreeNode rootRight = root.getRight();
            if (rootRight == null){
                root.setRight(new BinaryTreeNode(data));
            }else {
                buildBinaryTree(rootRight,data);
            }
        }
    }


    public BinarySearchTreeNode find(BinarySearchTreeNode root, int data){
        if (root == null ){
            return null;
        }
        int dataItem = root.getData();
        if (data == dataItem){
            return root;
        }else if (data < root.getData()){
            find(root.getLeft(),data);
        }else {
            find(root.getRight(),data);
        }
        return root;
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(BinaryTreeNode root){
        if (root == null){
            return;
        }
        System.out.print(root.getData());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void inOrder(BinaryTreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getData() + ",");
        inOrder(root.getRight());
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void inOrderNonRecursive(BinaryTreeNode root){
        if (root == null){
            return;
        }
        inOrderNonRecursive(root.getLeft());
        inOrderNonRecursive(root.getRight());
        System.out.print(root.getData()  + ",");
    }


}
