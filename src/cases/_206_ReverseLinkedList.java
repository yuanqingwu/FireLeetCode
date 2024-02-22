package cases;

import static org.junit.Assert.assertEquals;

import basic.linklist.ListNode;
import tag.Tag_LinkedList;
import tag.Recursion;
import tag.TwoPointers;

/**
 * Given the head of a singly linked list, reverse the list, and return the
 * reversed list.
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * 
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * 
 */
@Tag_LinkedList
@Recursion
public class _206_ReverseLinkedList extends BaseSolution {

    public static void main(String[] args) {
        _206_ReverseLinkedList solution = new _206_ReverseLinkedList();
        solution.run();
    }

    @Override
    void solution() {
        ListNode head = ListNode.gen(new int[] { 1, 2, 3, 4, 5 });
        assertEquals("5,4,3,2,1", ListNode.toString(reverseList(head)));
        ListNode head1 = ListNode.gen(new int[] { 1, 2, 3, 4, 5 });
        assertEquals("5,4,3,2,1", ListNode.toString(reverseListRecursion(head1)));
    }

    @TwoPointers(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public ListNode reverseList(ListNode head) {

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

    @Recursion(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public ListNode reverseListRecursion(ListNode head) {
        return helper(head);
    }

    public ListNode helper(ListNode node) {
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
