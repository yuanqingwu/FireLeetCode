package data;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 生成无环链表
     * 
     * @param values 链表元素
     * @return 链表头节点
     */
    public static ListNode gen(int[] values) {
        if (values.length == 1) {
            return new ListNode(values[0]);
        } else {
            ListNode pre = new ListNode(0);
            ListNode head = null;
            for (int v : values) {
                ListNode now = new ListNode(v);
                if (head == null) {
                    head = now;
                }
                pre.next = now;
                pre = now;
            }
            return head;
        }
    }

    /**
     * 生成有环链表
     * 
     * @param pos    入环节点
     * @param values 链表元素
     * @return 链表头节点
     */
    public static ListNode gen(int pos, int[] values) {
        if (values.length == 1) {
            return new ListNode(values[0]);
        } else {
            ListNode pre = new ListNode(0);
            ListNode head = null;
            ListNode cycleNode = null;
            for (int i = 0; i < values.length; i++) {
                ListNode now = new ListNode(values[i]);
                if (pos >= 0 && pos == i) {
                    cycleNode = now;
                }
                if (head == null) {
                    head = now;
                }
                pre.next = now;
                pre = now;
                if (i == values.length - 1 && cycleNode != null) {
                    pre.next = cycleNode;
                }
            }

            return head;
        }
    }

    /**
     * 生成以此节点为头节点的链表字符串，便于打印调试。（默认不超过100 个节点，防止有环链表）
     * 
     * @param listNode 链表头节点
     * @return 以此节点为头节点的链表字符串
     */
    public static String toString(ListNode listNode) {
        StringBuilder builder = new StringBuilder();
        int limit = 0;
        do {
            builder.append(listNode.val + ",");
            listNode = listNode.next;
            limit++;
        } while (listNode != null && limit < 100);
        return builder.toString();
    }
}
