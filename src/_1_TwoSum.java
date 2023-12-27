import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;

import tag.Array;
import tag.HashTable;

/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * Tags: Array, Hash Table
 */
@Array
@HashTable
public class _1_TwoSum extends BaseSolution {

    public static void main(String[] args) {
        _1_TwoSum a1TwoSum = new _1_TwoSum();
        a1TwoSum.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 2, 7, 11, 15 };
        int target = 9;
        int[] res = twoSum(nums, target);

        Assert.assertArrayEquals(new int[] { 0, 1 }, res);
        println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            final Integer value = map.get(nums[i]);
            if (value != null) {
                return new int[] { value, i };
            }
            map.put(target - nums[i], i);
        }
        return null;
    }
}