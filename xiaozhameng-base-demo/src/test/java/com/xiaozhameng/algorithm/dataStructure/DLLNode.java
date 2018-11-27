package com.xiaozhameng.algorithm.dataStructure;

/**
 * 双向链表
 */
public class DLLNode {

    private int data;
    private DLLNode next;
    private DLLNode previous;

    public DLLNode(int data){
        this.data = data;
    }


    /**
     * 获取链表的长度
     * @param headNode
     * @return
     */
    public int getNodeLength(DLLNode headNode){
        int length = 0;
        DLLNode currentNode = headNode;
        while (currentNode != null){
            length ++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    /**
     * 双向链表的插入操作
     * @return
     */
    public DLLNode dllInsert(DLLNode headNode,DLLNode nodeToInsert,int position){
        if (headNode == null){
            return nodeToInsert;
        }
        int size = getNodeLength(headNode);
        if (position > size+1 || position <1){
            System.out.println("Position of node to insert is invalid, The valid inpputs are 1 to" +(size+1));
            return headNode;
        }
        if (position == 1){
            nodeToInsert.setNext(headNode);
            headNode.setPrevious(nodeToInsert);
            return nodeToInsert;
        }else {
            DLLNode previousNode = headNode;
            int count = 1;
            while (count < position -1){
                previousNode = previousNode.getNext();
                count ++;
            }

            DLLNode currentNode = previousNode.getNext();
            nodeToInsert.setNext(previousNode.getNext());
            if (currentNode != null){
                currentNode.setPrevious(nodeToInsert);
            }
            currentNode.setNext(nodeToInsert);
            nodeToInsert.setPrevious(currentNode);

        }
        return headNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }

    public DLLNode getPrevious() {
        return previous;
    }

    public void setPrevious(DLLNode previous) {
        this.previous = previous;
    }
}
