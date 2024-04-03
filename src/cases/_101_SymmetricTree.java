package cases;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import basic.tree.binarytree.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;
import tag.Tag_Tree;

/**
 * [101. Symmetric Tree 对称二叉树](https://leetcode.cn/problems/symmetric-tree)
 * <p>
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * <p>
 * 给你一个二叉树的根节点 root, 检查它是否轴对称。
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 * <p>
 * Follow up: Could you solve it both recursively and iteratively?
 */
@Recursion
@Tag_Tree
@DFS_DepthFirstSearch
@BFS_BreadthFirstSearch
public class _101_SymmetricTree extends BaseSolution {

    public static void main(String[] args) {
        _101_SymmetricTree symmetricTree = new _101_SymmetricTree();
        symmetricTree.run();
    }

    @Override
    void solution() {
        TreeNode root = TreeNode.gen(new int[] { 1, 2, 2, 3, 4, 4, 3 }, 0);
        assertTrue(isSymmetric(root));
        assertTrue(isSymmetric1(root));
    }

    @Recursion(timeComplexity = "O(N)", spaceComplexity = "O(N)")
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode root1, TreeNode root2) {
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

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return bfs(root);
    }

    @BFS_BreadthFirstSearch(timeComplexity = "O(N)", spaceComplexity = "O(N)")
    public boolean bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            TreeNode start = queue.poll();
            TreeNode end = queue.poll();
            if (start == null && end == null) {
                continue;
            }
            if (start == null || end == null) {
                return false;
            }
            if (start.val != end.val) {
                return false;
            }
            queue.offer(start.left);
            queue.offer(end.right);
            queue.offer(start.right);
            queue.offer(end.left);
        }
        return true;
    }

}
