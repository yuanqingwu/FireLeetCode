import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import data.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.Tree;

/**
 * Given the root of a binary tree, return its maximum depth.
 * 
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * 
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * 
 * 解题思路：递归，DFS
 */
@Tree
@BFS_BreadthFirstSearch
@DFS_DepthFirstSearch
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
        assertEquals(3, depth);

    }

    @DFS_DepthFirstSearch
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @BFS_BreadthFirstSearch
    public int maxDepth_BFS(TreeNode root) {
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
}