package cases;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import basic.tree.TreeNode;
import tag.DFS_DepthFirstSearch;

/**
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a
 * height-balanced
 * binary search tree.
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */
public class _108_ConvertSortedArraytoBinarySearchTree extends BaseSolution {

    public static void main(String[] args) {
        _108_ConvertSortedArraytoBinarySearchTree convertSortedArraytoBinarySearchTree = new _108_ConvertSortedArraytoBinarySearchTree();
        convertSortedArraytoBinarySearchTree.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { -10, -3, 0, 5, 9 };
        TreeNode res = sortedArrayToBST(nums);
        System.out.println(Arrays.toString(TreeNode.toArray(res)));

        assertArrayEquals(new int[] { 0, -10, 5, -3, 9 }, TreeNode.toArray(res));
    }

    @DFS_DepthFirstSearch(timeComplexity = "O(n)", spaceComplexity = "O(logn)")
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // just always choose left as root.
        int index = (left + right) / 2;

        TreeNode node = new TreeNode(nums[index]);
        node.left = dfs(nums, left, index - 1);
        node.right = dfs(nums, index + 1, right);
        return node;
    }

}
