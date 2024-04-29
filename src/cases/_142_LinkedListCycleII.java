package cases;

import static org.junit.Assert.assertEquals;

import basic.linklist.ListNode;
import tag.Tag_LinkedList;
import tag.TwoPointers;

/**
 * [142. Linked List Cycle II 环形链表
 * II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If
 * there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can
 * be reached again by continuously following the next pointer. Internally, pos
 * is used to denote the index of the node that tail's next pointer is connected
 * to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as
 * a parameter.
 * <p>
 * Do not modify the linked list.
 * <p>
 * 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos
 * 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * <p>
 * Explanation: There is a cycle in the linked list, where tail connects to the
 * second node.
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * <p>
 * Explanation: There is a cycle in the linked list, where tail connects to the
 * first node.
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1], pos = -1
 * Output: no cycle
 * <p>
 * Explanation: There is no cycle in the linked list.
 * 
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 * 
 */
@Tag_LinkedList
@TwoPointers
public class _142_LinkedListCycleII extends BaseSolution {

    public static void main(String[] args) {
        _142_LinkedListCycleII linkedListCycleII = new _142_LinkedListCycleII();
        linkedListCycleII.run();
    }

    @Override
    void solution() {
        ListNode head = ListNode.gen(new int[] { 3, 2, 0, -4 });
        ListNode tail = head.next.next.next;
        tail.next = head.next;
        assertEquals(head.next, detectCycle(head));
    }

    @TwoPointers(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }
}
