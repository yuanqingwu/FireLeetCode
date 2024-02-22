package cases;

import static org.junit.Assert.assertEquals;

import tag.Tag_Array;
import tag.DynamicProgramming;

/**
 * 53. 最大子数组和
 * <p>
 * 
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 子数组 是数组中的一个连续部分。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * 
 * <p>
 * 
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * 
 * <p>
 * 
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
@DynamicProgramming
@Tag_Array
public class _53_MaximumSubarray extends BaseSolution {

    public static void main(String[] args) {
        _53_MaximumSubarray maximumSubarray = new _53_MaximumSubarray();
        maximumSubarray.run();
    }

    @Override
    void solution() {
        assertEquals(23, maxSubArray(new int[] { 5, 4, -1, 7, 8 }));
    }

    @DynamicProgramming(timeComplexity = "O(N)", spaceComplexity = "O(1)")
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1; i < len; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

}
