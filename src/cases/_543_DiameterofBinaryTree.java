package cases;

import static org.junit.Assert.assertEquals;

import basic.tree.binarytree.TreeNode;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;
import tag.Tag_Tree;

/**
 * Given the root of a binary tree, return the length of the diameter of the
 * tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any
 * two nodes in a tree. This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 * <p>
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 
 * 两节点之间路径的 长度 由它们之间边数表示。
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,2]
 * Output: 1
 * 
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */
@Tag_Tree
@DFS_DepthFirstSearch
@Recursion
public class _543_DiameterofBinaryTree extends BaseSolution {

    public static void main(String[] args) {
        _543_DiameterofBinaryTree diameterofBinaryTree = new _543_DiameterofBinaryTree();
        diameterofBinaryTree.run();
    }

    @Override
    void solution() {
        TreeNode root = TreeNode.gen(new int[] { 1, 2, 3, 4, 5 }, 0);
        assertEquals(3, diameterOfBinaryTree(root));
    }

    int max = 0;

    @Recursion
    @DFS_DepthFirstSearch(timeComplexity = "O(N)", spaceComplexity = "O(N)")
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left + right > max) {
            max = left + right;
        }
        return Math.max(left, right) + 1;
    }
}
