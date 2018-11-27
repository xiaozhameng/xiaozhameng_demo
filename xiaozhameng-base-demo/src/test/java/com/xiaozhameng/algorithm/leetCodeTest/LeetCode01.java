package com.xiaozhameng.algorithm.leetCodeTest;

import org.junit.Test;

public class LeetCode01 {

    @Test
    public void test_lengthOfLongestSubstring(){
        String s = "abcabcbb";
        if(s == null || "".equals(s)){
            return ;
        }
        String resultStr = "";
        char[] data = s.toCharArray();
        for (int x =0; x< data.length; x++){
            String currStr = data[x]+"";
            for(int i=x+1; i< data.length ;i++){
                // 比较i+1 到 data.length 范围内，与currStr 相同的字符，此时的索引与currIndex之差就是子串的长度
                String subStr = s.substring(i);
                // 截取结果字符串
                if (s.substring(x,i).length() > resultStr.length()){
                    resultStr = s.substring(x, i);
                }
                if(subStr.startsWith(currStr)){
                    // 结束本轮循环
                    break;
                }
            }
        }

        System.out.println(resultStr.length());
    }

    @Test
    public void addTwoNumbersData(){
        ListNode node01 = new ListNode(2);
        node01.next = new ListNode(4);
        node01.next.next = new ListNode(3);
//        node01.next.next.next = new ListNode(1);
//        node01.next.next.next.next = new ListNode(2);

        ListNode node02 = new ListNode(5);
        node02.next = new ListNode(6);
        node02.next.next = new ListNode(4);
//        node02.next.next.next = new ListNode(1);
//        node02.next.next.next.next = new ListNode(2);

        ListNode listNode = addTwoNumbers2(node01, node02);
        System.out.println(listNode);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return null;
        }

        // 3 4 2
        // 4 6 5

        // 两数相加进位
        int temp = 0;

        ListNode rootNode = null;
        ListNode previousNode = null;
        while((l1 != null) && (l2 != null)){
            int data01 = l1.val ;
            int data02 = l2.val ;
            int result = data01 + data02;
            if(temp >0 ){
                result = result +1;
                temp = 0;
            }
            if(result > 9){
                result = result % 10;
                temp = 1;
            }

            ListNode resultNode = new ListNode(result);

            // 将resultCode 插入到rootNode尾部节点
            if(rootNode == null){
                rootNode = resultNode;
                previousNode = rootNode;
            }else {
                previousNode.next = resultNode;
                previousNode = resultNode;
            }

            // 循环结束条件
            l1 = l1.next;
            l2 = l2.next;
        }

        return rootNode;

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return null;
        }

        // 3 4 2
        // 4 6 5

        // 两数相加进位
        int temp = 0;

        ListNode rootNode = null;
        ListNode currNode = null;
        while((l1 != null) && (l2 != null)){
            int data01 = l1.val ;
            int data02 = l2.val ;

            int result = data01 + data02;
            if(temp >0 ){
                result = result +1;
                temp = 0;
            }

            if(result > 9){
                result = result % 10;
                temp = 1;
            }

            ListNode resultNode = new ListNode(result);

            if(rootNode == null){
                rootNode = resultNode;
            }
            if(currNode == null){
                currNode = resultNode;
                rootNode.next = currNode;
            }else{
                currNode.next = resultNode;
                currNode = resultNode;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        return rootNode;

    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
