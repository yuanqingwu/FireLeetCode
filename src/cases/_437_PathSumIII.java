package cases;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import basic.tree.binarytree.TreeNode;
import tag.DFS_DepthFirstSearch;
import tag.PrefixSum;
import tag.Tag_BinaryTree;
import tag.Tag_Tree;

/**
 * [437. 路径总和 III](https://leetcode.com/problems/path-sum-iii/)
 * 
 * <p>
 * Given the root of a binary tree and an integer targetSum, return the number
 * of paths where the sum of the values along the path equals targetSum.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * Example 1:
 * <p>
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * <p>
 * Explanation: The paths that sum to 8 are shown.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * 
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
@Tag_Tree
@Tag_BinaryTree
@DFS_DepthFirstSearch
@PrefixSum
public class _437_PathSumIII extends BaseSolution {
    public static void main(String[] args) {
        _437_PathSumIII pathSumIII = new _437_PathSumIII();
        pathSumIII.run();
    }

    @Override
    void solution() {
        TreeNode node = TreeNode.gen(new int[] { 10, 5, -3, 3, 2, 0, 11, 3, -2, 0, 1 }, 0, 0);
        int targetSum = 8;
        assertEquals(3, pathSum(node, targetSum));
    }

    // key:presum value:num of presum
    Map<Long, Integer> map = new HashMap<>();
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        map.put(0L, 1);
        dfs(root, targetSum, root.val);
        return res;
    }

    @DFS_DepthFirstSearch(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public void dfs(TreeNode root, int targetSum, long preSum) {
        res += map.getOrDefault(preSum - targetSum, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);

        if (root.left != null) {
            dfs(root.left, targetSum, preSum + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, targetSum, preSum + root.right.val);
        }
        map.put(preSum, map.getOrDefault(preSum, 0) - 1);
    }

}
