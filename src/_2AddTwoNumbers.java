/**
 * 将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
 * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
 * 如果两个链表全部遍历完毕后，进位值为 11，则在新链表最前方添加节点 11
 * <p>小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。
 * 使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
 *
 *
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * </p>
 */
public class _2AddTwoNumbers extends BaseSolution {

    public static void main(String[] args) {

        _2AddTwoNumbers a2AddTwoNumbers = new _2AddTwoNumbers();
        a2AddTwoNumbers.run();

    }

    @Override
    void solution() {

        /**
         * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
         * Output: [8,9,9,9,0,0,0,1]
         */

        /**
         * Input: l1 = [0], l2 = [0]
         * Output: [0]
         */

        //987 + 23 = 987 + 023 = 1010

        ListNode l1Node1 = new ListNode(7);
        ListNode l1Node2 = new ListNode(8);
        ListNode l1Node3 = new ListNode(9);
        l1Node1.next = l1Node2;
        l1Node2.next = l1Node3;

        ListNode l2Node1 = new ListNode(3);
        ListNode l2Node2 = new ListNode(2);
//        ListNode l2Node3 = new ListNode(4);
        l2Node1.next = l2Node2;
//        l2Node2.next = l2Node3;

        ListNode listNode = addTwoNumbers(l1Node1, l2Node1);

        System.out.println(getListNodeString(l1Node1));
        System.out.println(getListNodeString(l2Node1));
        System.out.println(getListNodeString(listNode));
    }

    public String getListNodeString(ListNode listNode){

        StringBuilder builder = new StringBuilder();
        do {
            builder.append(listNode.val + ",");
            listNode = listNode.next;
        } while (listNode != null);

        return builder.toString();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
//            carry = sum >9 ? 1 : 0 ;//把carry = sum / 10; 换成 carry = sum >9 ? 1 : 0 ; 速度会快很多
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
}
