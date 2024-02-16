package cases;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import tag.ArrayTag;
import tag.BackTracking;

/**
 * 78. 子集
 * <p>
 * Given an integer array nums of unique elements, return all possible
 * subsets
 * (the power set).
 * 
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
@BackTracking
@ArrayTag
public class _78_Subsets extends BaseSolution {

    public static void main(String[] args) {
        _78_Subsets subsets = new _78_Subsets();
        subsets.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 1, 2, 3 };
        List<List<Integer>> res = subsets(nums);
        println("subsets: " + res);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<Integer>(List.of()));
        expected.add(new ArrayList<Integer>(List.of(1)));
        expected.add(new ArrayList<Integer>(List.of(1, 2)));
        expected.add(new ArrayList<Integer>(List.of(1, 2, 3)));
        expected.add(new ArrayList<Integer>(List.of(1, 3)));
        expected.add(new ArrayList<Integer>(List.of(2)));
        expected.add(new ArrayList<Integer>(List.of(2, 3)));
        expected.add(new ArrayList<Integer>(List.of(3)));
        assertArrayEquals(expected.toArray(), res.toArray());
    }

    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    @BackTracking
    public void backtracking(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }
}
