package cases;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tag.BackTracking;

/**
 * 46. 全排列
 * <p>
 * Given an array nums of distinct integers, return all the possible
 * permutations. You can return the answer in any order.
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
@BackTracking
public class _46_Permutations extends BaseSolution {

    public static void main(String[] args) {
        _46_Permutations permutations = new _46_Permutations();
        permutations.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 1, 2, 3 };
        List<List<Integer>> res = permute(nums);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<Integer>(List.of(1, 2, 3)));
        expected.add(new ArrayList<Integer>(List.of(1, 3, 2)));
        expected.add(new ArrayList<Integer>(List.of(2, 1, 3)));
        expected.add(new ArrayList<Integer>(List.of(2, 3, 1)));
        expected.add(new ArrayList<Integer>(List.of(3, 1, 2)));
        expected.add(new ArrayList<Integer>(List.of(3, 2, 1)));
        assertArrayEquals(expected.toArray(), res.toArray());
    }

    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backtracking(nums, 0, used);
        return res;
    }

    @BackTracking(timeComplexity = "O(n!)", spaceComplexity = "O(n)")
    public void backtracking(int[] nums, int start, boolean[] used) {

        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                backtracking(nums, i + 1, used);
                path.removeLast();
                used[i] = false;
            }

        }
    }

}
