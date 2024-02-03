package cases;

import static org.junit.Assert.assertEquals;

import basic.linklist.ListNode;
import tag.TwoPointers;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node
 * at which the two lists intersect. If the two linked lists have no
 * intersection at all, return null.
 * Note that the linked lists must retain their original structure after the
 * function returns.
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 
 * Constraints:
 * <p>
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 * 
 * 
 */
public class _160_IntersectionofTwoLinkedLists extends BaseSolution {

    public static void main(String[] args) {
        _160_IntersectionofTwoLinkedLists intersectionofTwoLinkedLists = new _160_IntersectionofTwoLinkedLists();
        intersectionofTwoLinkedLists.run();
    }

    @Override
    void solution() {
        ListNode a = ListNode.gen(new int[] { 4, 1, 8, 4, 5 });
        ListNode b = ListNode.gen(new int[] { 5, 6, 1, 8, 4, 5 });
        System.out.println(getIntersectionNode(a, b));
        // assertEquals(8, getIntersectionNode(a, b).val);//fixme
    }

    @TwoPointers(timeComplexity = "m+n", spaceComplexity = "1")
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

}
