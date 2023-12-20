package data;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode gen(int... values) {
        if (values.length == 1) {
            return new ListNode(values[0]);
        } else {
            ListNode pre = new ListNode(0);
            ListNode head = null;
            for (int v : values) {
                ListNode now = new ListNode(v);
                if(head == null){
                    head = now;
                }
                pre.next = now;
                pre = now;
            }
            return head;
        }
    }

    public static String toString(ListNode listNode) {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(listNode.val + ",");
            listNode = listNode.next;
        } while (listNode != null);
        return builder.toString();
    }
}
