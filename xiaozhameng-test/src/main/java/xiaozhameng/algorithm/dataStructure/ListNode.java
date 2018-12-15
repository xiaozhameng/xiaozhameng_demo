package xiaozhameng.algorithm.dataStructure;

/**
 * <p>Description: 单项链表定义</p>
 * <p>Copyright: @2018</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/7/7
 */
public class ListNode {

    private int data;
    private ListNode next;
    public ListNode (int data){
        this.data = data;
    }

    /**
     * 获取链表的长度
     * @param headNode
     * @return
     */
    public int listNodeLength(ListNode headNode){
        int length = 0;
        ListNode currentNode = headNode;
        while (currentNode != null){
            length ++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

    /**
     * 链表的插入
     * @return
     */
    public ListNode insertInListNode(ListNode headNode,ListNode nodeToInsert,int position){
        if (headNode == null){
            return nodeToInsert;
        }
        int size = listNodeLength(headNode);
        if (position > size +1 || position <1){
            System.out.println("Position of node to insert is invalid, The valid inpputs are 1 to" +(size+1));
            return headNode;
        }
        if (position == 1){
            nodeToInsert.next = headNode;
            return nodeToInsert;
        }else {

            // 如果要往第i个位置插入节点，需要找到第i-1个节点 ，将节点元素插入，并将原来的第i个节点设置为插入元素的后继节点
            ListNode previousNode = headNode;
            int count = 1;
            while(count < position -1){
                previousNode = previousNode.next ;
                count ++;
            }

            ListNode currentNode = previousNode.getNext();
            nodeToInsert.setNext(currentNode);
            previousNode.setNext(nodeToInsert);

        }
        return headNode;
    }

    /**
     * 删除链表中的某个节点
     * @param headNode
     * @param position
     * @return
     */
    public ListNode deleteNodeFromLinedListNode(ListNode headNode,int position){
        int size = listNodeLength(headNode);
        if (position > size || position <1){
            System.out.println("Position of node to insert is invalid, The valid inpputs are 1 to" +(size));
            return headNode;
        }
        if (position == 1){
            ListNode currentNode = headNode.getNext();
            headNode = null;
            return currentNode;
        }else {

            // 删除中间节点和尾节点
            ListNode previousNode = headNode;
            int count = 1;
            while(count < position ){
                previousNode = previousNode.next ;
                count ++;
            }

            ListNode currentNode = previousNode.getNext();
            previousNode.setNext(currentNode.getNext());
            currentNode = null;
        }
        return headNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
