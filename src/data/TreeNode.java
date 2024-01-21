package data;

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

}