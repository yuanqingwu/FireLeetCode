package basic.tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;

/**
 * 二叉树基本操作
 * <p>
 * 二叉树的基本操作，包括遍历、翻转二叉树、判断对称二叉树、二叉树的最大深度等
 * <p>
 * 遍历形式有：前序遍历、中序遍历、后序遍历
 * <p>
 * 遍历方法可以分为：递归法、迭代法、Morris法
 */
public class BinaryTreeBasicFunc {

    public static void main(String[] args) {
        /**
         * 1
         * / \
         * 2 5
         * / \ \
         * 3 4 6
         */
        int[] values = { 1, 2, 5, 3, 4, -1, 6 };
        TreeNode root = TreeNode.gen(values, 0, -1);
        System.out.println(root);
        System.out.println("递归法遍历:");
        System.out.println("递归法前序遍历：" + traversalPreOrder_Recursion(root));
        System.out.println("递归法中序遍历：" + traversalInOrder_Recursion(root));
        System.out.println("递归法后序遍历：" + traversalPostOrder_Recursion(root));
        System.out.println("迭代法遍历:");
        System.out.println("迭代法前序遍历：" + traversalPreOrder(root));
        System.out.println("迭代法中序遍历：" + traversalInOrder(root));
        System.out.println("迭代法后序遍历：" + traversalPostOrder(root));
        System.out.println("Morris法遍历:");
        System.out.println("Morris法前序遍历:" + traversalPreOrder_Morris(root));
        System.out.println("Morris法中序遍历:" + traversalInOrder_Morris(root));
        System.out.println("Morris法后序遍历:" + traversalPostOrder_Morris(root));

        System.out.println(maxDepth(root));
    }

    /**
     * Invert a binary tree.
     * 
     * @param root the root of a binary tree
     * @return the inverted tree
     */
    public static TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * check the tree is symmetric or not.
     * 
     * @param root the root of a binary tree
     * @return true if the tree is symmetric, otherwise false
     */
    @Recursion(timeComplexity = "O(N)", spaceComplexity = "O(N)")
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root.left, root.right);
    }

    private static boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }

        return dfs(root1.left, root2.right) && dfs(root1.right, root2.left);
    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     * 
     * @param root the root of a binary tree
     * @return the maximum depth of the tree
     */
    @DFS_DepthFirstSearch
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     * <p>
     * 二叉树的最大深度
     * 
     * @param root the root of a binary tree
     * @return the maximum depth of the tree
     */
    @BFS_BreadthFirstSearch
    public static int maxDepth_BFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<TreeNode> queue = new LinkedList<>();
        List<TreeNode> tmp = null;
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null)
                    tmp.add(node.left);
                if (node.right != null)
                    tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }

    /**
     * 递归法前序遍历
     */
    public static List<Integer> traversalPreOrder_Recursion(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        traversalPreOrder_Recursion(root, res);
        return res;
    }

    private static void traversalPreOrder_Recursion(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        traversalPreOrder_Recursion(root.left, res);
        traversalPreOrder_Recursion(root.right, res);
    }

    /**
     * 递归法中序遍历
     */
    public static List<Integer> traversalInOrder_Recursion(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        traversalInOrder_Recursion(root, res);
        return res;
    }

    private static void traversalInOrder_Recursion(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        traversalInOrder_Recursion(root.left, res);
        res.add(root.val);
        traversalInOrder_Recursion(root.right, res);
    }

    /**
     * 递归法后序遍历
     */
    public static List<Integer> traversalPostOrder_Recursion(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        traversalPostOrder_Recursion(root, res);
        return res;
    }

    private static void traversalPostOrder_Recursion(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        traversalPostOrder_Recursion(root.left, res);
        traversalPostOrder_Recursion(root.right, res);
        res.add(root.val);
    }

    /**
     * 迭代法前序遍历
     */
    public static List<Integer> traversalPreOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 迭代法中序遍历
     */
    public static List<Integer> traversalInOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 迭代法后序遍历
     * <p>
     * 前序遍历顺序为：根 -> 左 -> 右
     * 后序遍历顺序为：左 -> 右 -> 根
     * 所以我们可以把前序遍历修改为: 根 -> 右 -> 左, 然后将结果存放到栈里, 最后再遍历结果栈就可以输出后序遍历了
     */
    public static List<Integer> traversalPostOrder(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                resStack.push(cur); // root
                s.push(cur);
                cur = cur.right; // right
            } else {
                cur = s.pop();
                cur = cur.left; // left
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!resStack.isEmpty()) {
            res.add(resStack.pop().val);
        }
        return res;
    }

    /**
     * Morris法前序遍历
     */
    public static List<Integer> traversalPreOrder_Morris(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) {
            return list;
        }
        TreeNode cur1 = head;// 当前开始遍历的节点
        TreeNode cur2 = null;// 记录当前结点的左子树
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {// 找到当前左子树的最右侧节点，且这个节点应该在指向根结点之前，否则整个节点又回到了根结点。
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {// 这个时候如果最右侧这个节点的右指针没有指向根结点，创建连接然后往下一个左子树的根结点进行连接操作。
                    list.add(cur1.val);
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {// 当左子树的最右侧节点有指向根结点，此时说明我们已经回到了根结点并重复了之前的操作，同时在回到根结点的时候我们应该已经处理完 左子树的最右侧节点
                        // 了，把路断开。
                    cur2.right = null;
                }
            } else {
                list.add(cur1.val);
            }
            cur1 = cur1.right;// 一直往右边走，参考图
        }
        return list;
    }

    /**
     * Morris法中序遍历
     */
    public static List<Integer> traversalInOrder_Morris(TreeNode head) {
        List<Integer> list = new ArrayList<Integer>();
        if (head == null) {
            return list;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            // 构建连接线
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            list.add(cur1.val);
            cur1 = cur1.right;
        }
        return list;
    }

    /**
     * Morris法后序遍历
     */
    public static List<Integer> traversalPostOrder_Morris(TreeNode head) {
        List<Integer> list = new ArrayList<Integer>();
        if (head == null) {
            return list;
        }
        TreeNode cur1 = head;// 遍历树的指针变量
        TreeNode cur2 = null;// 当前子树的最右节点
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    addPath(list, cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        addPath(list, head);
        return list;
    }

    public static void addPath(List<Integer> res, TreeNode node) {
        int count = 0;
        while (node != null) {
            ++count;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count, right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }

}
