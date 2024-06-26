package cases;

import basic.linklist.ListNode;
import tag.Tag_LinkedList;
import tag.Tag_Math;
import tag.Recursion;

/**
 * [2. Add Two Numbers 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)
 * <p>
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked
 * list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 
 * Example:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * <p>
 * Explanation: 342 + 465 = 807.
 * <p>
 * 
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading
 * zeros.
 * <p>
 * 将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
 * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
 * 如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1
 * <p>
 * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。
 * 使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
 */
@Tag_LinkedList
@Tag_Math
@Recursion
public class _2_AddTwoNumbers extends BaseSolution {

    public static void main(String[] args) {
        _2_AddTwoNumbers a2AddTwoNumbers = new _2_AddTwoNumbers();
        a2AddTwoNumbers.run();
    }

    @Override
    void solution() {
        // 987 + 23 = 987 + 023 = 1010
        ListNode l1Node1 = ListNode.gen(new int[] { 7, 8, 9 });
        ListNode l2Node1 = ListNode.gen(new int[] { 3, 2 });

        ListNode listNode = addTwoNumbersRecursion(l1Node1, l2Node1);

        System.out.println(ListNode.toString(l1Node1));
        System.out.println(ListNode.toString(l2Node1));
        System.out.println(ListNode.toString(listNode));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            // carry = sum / 10;
            carry = sum > 9 ? 1 : 0;// opt : carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * recursion solution
     */
    @Recursion(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public ListNode addTwoNumbersRecursion(ListNode l1, ListNode l2) {
        return addTwo(l1, l2, 0);
    }

    public ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        int val = carry;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        ListNode node = new ListNode(val % 10);
        node.next = addTwo(l1, l2, val / 10);
        return node;
    }
}
