package cases;

import static org.junit.Assert.assertEquals;

import basic.linklist.ListNode;
import tag.LinkedListTag;
import tag.TwoPointers;

/**
 * Given head, the head of a linked list, determine if the linked list has a
 * cycle in it.
 * 
 * There is a cycle in a linked list if there is some node in the list that can
 * be reached again by continuously following the next pointer. Internally, pos
 * is used to denote the index of the node that tail's next pointer is connected
 * to. Note that pos is not passed as a parameter.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * 
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * 
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to
 * the 1st node (0-indexed).
 * 
 * <p>
 * Example 2:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * 
 * <p>
 * Constraints:
 * 
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 */
@LinkedListTag
@TwoPointers
public class _141_LinkedListCycle extends BaseSolution {

    public static void main(String[] args) {
        _141_LinkedListCycle linkedListCycle = new _141_LinkedListCycle();
        linkedListCycle.run();
    }

    @Override
    void solution() {
        ListNode list1 = ListNode.gen(1, new int[] { 3, 2, 0, -4 });
        println(ListNode.toString(list1));
        assertEquals(true, hasCycle(list1));
    }

    @TwoPointers(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

}
