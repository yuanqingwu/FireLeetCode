package basicfun;

import java.util.LinkedList;
import java.util.List;

import data.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;

public class BinaryTreeBasic {

    /**
     * Invert a binary tree.
     * 
     * @param root the root of a binary tree
     * @return the inverted tree
     */
    public TreeNode invertTree(TreeNode root) {

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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     * 
     * @param root the root of a binary tree
     * @return the maximum depth of the tree
     */
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
