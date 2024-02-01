package cases;

import static org.junit.Assert.assertTrue;

import data.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;
import tag.Tree;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * <p>
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */
@Recursion
@Tree
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

}
