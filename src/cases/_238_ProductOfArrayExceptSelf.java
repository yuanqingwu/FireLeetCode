package cases;

import static org.junit.Assert.assertArrayEquals;

import tag.PrefixSum;
import tag.Tag_Array;

/**
 * [238. Product of Array Except
 * Self](https://leetcode.com/problems/product-of-array-except-self/)
 * 
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 * 
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 * <p>
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * 
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * <p>
 * -30 <= nums[i] <= 30
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 */
@Tag_Array
@PrefixSum
public class _238_ProductOfArrayExceptSelf extends BaseSolution {

    public static void main(String[] args) {
        _238_ProductOfArrayExceptSelf productOfArrayExceptSelf = new _238_ProductOfArrayExceptSelf();
        productOfArrayExceptSelf.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 1, 2, 3, 4 };
        int[] result = productExceptSelf(nums);
        assertArrayEquals(new int[] { 24, 12, 8, 6 }, result);
    }

    @PrefixSum(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int r = 1;
        for (int j = len - 2; j >= 0; j--) {
            r = r * nums[j + 1];
            ans[j] = ans[j] * r;
        }
        return ans;
    }

}
