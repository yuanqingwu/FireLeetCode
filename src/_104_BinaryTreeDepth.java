import data.TreeNode;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 解题思路：递归，DFS
 */
public class _104_BinaryTreeDepth extends BaseSolution {

    public static void main(String[] args) {
        _104_BinaryTreeDepth depth = new _104_BinaryTreeDepth();
        depth.run();
    }

    @Override
    void solution() {

        // 3,9,20,null,null,15,7
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        int depth = maxDepth(root);
        println("depth:" + depth);
        assert depth == 3;

    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}