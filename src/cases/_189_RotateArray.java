package cases;

import static org.junit.Assert.assertArrayEquals;

import tag.Tag_Array;
import tag.Tag_Math;
import tag.TwoPointers;

/**
 * [189. 轮转数组](https://leetcode-cn.com/problems/rotate-array/)
 * <p>
 * Given an integer array nums, rotate the array to the right by k steps, where
 * k is non-negative.
 * <p>
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * <p>
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * <p>
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */
@Tag_Array
@Tag_Math
@TwoPointers
public class _189_RotateArray extends BaseSolution {

    public static void main(String[] args) {
        _189_RotateArray rotateArray = new _189_RotateArray();
        rotateArray.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
        rotate(nums, k);
        assertArrayEquals(new int[] { 5, 6, 7, 1, 2, 3, 4 }, nums);
    }

    @TwoPointers(timeComplexity = "O(n)", spaceComplexity = "O(1)")
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

}
