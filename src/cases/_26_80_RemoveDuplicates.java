package cases;

import static org.junit.Assert.assertEquals;

import tag.Tag_Array;
import tag.TwoPointers;

/**
 * 80. 删除有序数组中的重复项 II
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove some
 * duplicates in-place such that each unique element appears at most twice. The
 * relative order of the elements should be kept the same.
 * 
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array
 * nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not
 * matter what you leave beyond the first k elements.
 * 
 * Return k after placing the final result in the first k slots of nums.
 * 
 * Do not allocate extra space for another array. You must do this by modifying
 * the input array in-place with O(1) extra memory.
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * 
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * 
 * int k = removeDuplicates(nums); // Calls your implementation
 * 
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * <p>
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements
 * of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 * 
 */
@TwoPointers
@Tag_Array
public class _26_80_RemoveDuplicates extends BaseSolution {

    public static void main(String[] args) {
        _26_80_RemoveDuplicates removeDuplicates = new _26_80_RemoveDuplicates();
        removeDuplicates.run();
    }

    @Override
    void solution() {

        int[] nums1 = new int[] { 1, 1, 2, 2, 2, 2, 3, 4, 4 };
        int res1 = removeDuplicates(nums1, 1);
        assertEquals(4, res1);

        int[] nums2 = new int[] { 1, 1, 2, 2, 2, 2, 3, 4, 4 };
        int res2 = removeDuplicates(nums2, 2);
        assertEquals(7, res2);

    }

    public int removeDuplicates(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x)
                nums[u++] = x;
        }
        return u;
    }
}