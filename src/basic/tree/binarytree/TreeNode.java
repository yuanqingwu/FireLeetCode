package basic.tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        // return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
        return "[" + val + "," + left + "," + right + "]";
    }

    /**
     * 利用层序遍历数组生成二叉树
     * 
     * @param values  层序遍历的二叉树值数组
     * @param rootNum 根节点的下标
     * @return 二叉树根节点
     */
    public static TreeNode gen(int[] values, int rootNum) {
        int len = values.length;
        TreeNode root = new TreeNode(values[rootNum]);
        if (rootNum * 2 + 1 < len) {
            root.left = gen(values, rootNum * 2 + 1);
        }

        if (rootNum * 2 + 2 < len) {
            root.right = gen(values, rootNum * 2 + 2);
        }
        return root;
    }

    /**
     * 利用层序遍历数组生成二叉树
     * 
     * @param values  层序遍历的二叉树值数组
     * @param rootNum 根节点的下标
     * @param asNull  指代null节点的值，随意选取数组中不存在的值用以指代即可
     * @return 二叉树根节点
     */
    public static TreeNode gen(int[] values, int rootNum, int asNull) {
        int len = values.length;
        TreeNode root = values[rootNum] == asNull ? null : new TreeNode(values[rootNum]);
        if (root != null) {
            if (rootNum * 2 + 1 < len) {
                root.left = gen(values, rootNum * 2 + 1);
            }

            if (rootNum * 2 + 2 < len) {
                root.right = gen(values, rootNum * 2 + 2);
            }
        }
        return root;
    }

    /**
     * 层序遍历二叉树，返回层序遍历二叉树值的数组
     * 
     * @param root 二叉树根节点
     * @return 层序遍历二叉树值的数组
     */
    public static int[] toArray(TreeNode root) {

        if (root == null) {
            return new int[] {};
        }
        List<Integer> res = new ArrayList<>();
        bfs(root, res);
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private static void bfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

}