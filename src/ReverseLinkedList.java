/*
* 206. Reverse Linked List
* Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000
*
* */

public class ReverseLinkedList {
    public static void main(String[] args) {

       ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
       ListNode listNode = reverseLinkedList.reverseList(reverseLinkedList.createListNodeHead());
//        System.out.println(listNode);
    }

    public ListNode createListNodeHead() {
        ListNode listNode5 = new ListNode(5,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        return listNode1;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        // iterate through the list till the last node
        // store the last node in a ListNode variable stored outside
        ListNode finalHead=null;
        while(head != null) {
            ListNode temp = head.next;
            head.next = finalHead;
            finalHead = head;
            head = temp;
        }
        return finalHead;
    }

}
