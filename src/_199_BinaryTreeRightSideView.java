import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import data.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.BinaryTree;
import tag.DFS_DepthFirstSearch;
import tag.Tree;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side
 * of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 * <p>
 * Input: root = [1,null,3]
 * Output: [1,3]
 * <p>
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * 
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
@Tree
@BinaryTree
@BFS_BreadthFirstSearch
@DFS_DepthFirstSearch
public class _199_BinaryTreeRightSideView extends BaseSolution {

    public static void main(String[] args) {
        _199_BinaryTreeRightSideView solution = new _199_BinaryTreeRightSideView();
        solution.run();
    }

    @Override
    void solution() {
        TreeNode root = TreeNode.gen(new int[] { 1, 2, 3, 5, 4 }, 0);
        assertArrayEquals(new int[] { 1, 3, 4 }, rightSideView(root).stream().mapToInt(Integer::intValue).toArray());
        assertArrayEquals(new int[] { 1, 3, 4 }, rightSideViewDfs(root).stream().mapToInt(Integer::intValue).toArray());
    }

    @BFS_BreadthFirstSearch(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode tmp = queue.poll();
                if (i == n - 1) {
                    res.add(tmp.val);
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }

            }

        }
        return res;
    }

    List<Integer> res = new ArrayList<>();

    @DFS_DepthFirstSearch(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public List<Integer> rightSideViewDfs(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) { // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

}
