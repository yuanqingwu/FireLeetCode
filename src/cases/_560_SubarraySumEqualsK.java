package cases;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import tag.PrefixSum;
import tag.Tag_Array;
import tag.Tag_HashTable;

/**
 * [560. 和为K的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)
 * <p>
 * Given an array of integers nums and an integer k, return the total number of
 * subarrays whose sum equals to k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
@Tag_Array
@Tag_HashTable
@PrefixSum
public class _560_SubarraySumEqualsK extends BaseSolution {

    public static void main(String[] args) {
        _560_SubarraySumEqualsK subarraySumEqualsK = new _560_SubarraySumEqualsK();
        subarraySumEqualsK.run();
    }

    @Override
    void solution() {
        int[] nums = { 1, 1, 1 };
        int k = 2;
        int res = subarraySum(nums, k);
        assertEquals(2, res);
    }

    @PrefixSum(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);// presum == k
        int preSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum = preSum + nums[i];
            count += map.getOrDefault(preSum - k, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

}
