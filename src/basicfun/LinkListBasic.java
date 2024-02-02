package basicfun;

import data.ListNode;
import tag.Recursion;
import tag.TwoPointers;

public class LinkListBasic {

    /**
     * Reverse a singly linked list.
     * 
     * @param head the head of a singly linked list
     * @return the reversed list
     */
    @TwoPointers(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public static ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode pre = null;
        ListNode tmp = null;

        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * Reverse a singly linked list.
     * 
     * @param head the head of a singly linked list
     * @return the reversed list
     */
    @Recursion(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public static ListNode reverseListRecursion(ListNode head) {
        return helper(head);
    }

    private static ListNode helper(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode cur = helper(node.next);
        node.next.next = node;
        node.next = null;
        // System.out.println(node.val);
        return cur;
    }
}
