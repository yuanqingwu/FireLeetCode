package cases;

import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.DynamicProgramming;

/**
 * You are given an integer array nums. You want to maximize the number of
 * points you get by performing the following operation any number of times:
 * 
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must
 * delete every element equal to nums[i] - 1 and every element equal to nums[i]
 * + 1.
 * Return the maximum number of points you can earn by applying the above
 * operation some number of times.
 * 
 * <p>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i]
 * + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 
 * Example 1:
 * 
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * 
 * <p>
 * 
 * Example 2:
 * 
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums =
 * [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 * 
 * <p>
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
@DynamicProgramming
@Array
public class _740_DeleteandEarn extends BaseSolution {

    public static void main(String[] args) {
        _740_DeleteandEarn deleteandEarn = new _740_DeleteandEarn();
        deleteandEarn.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 2, 2, 3, 3, 3, 4 };
        assertEquals(9, deleteAndEarn(nums));
    }

    @DynamicProgramming(timeComplexity = "O(N+M)", spaceComplexity = "O(M)")
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, n);
        }

        /*
         * 关键技巧：构建数组 sum，以nums[i]为下标，所有相等的nums[i]相加的和为 value.
         * 如：nums =[2,2,3,3,3,4] 处理后：sums = [0, 0, 4, 9, 4]
         * 经过处理之后的sum数组处理思路就和#198题打家劫舍一样了。
         */
        int[] sums = new int[max + 1];
        for (int n : nums) {
            sums[n] += n;
        }

        int len = sums.length;
        // int[] dp = new int[len];
        // dp[0] = sum[0];
        // dp[1] = Math.max(sum[0], sum[1]);

        int p = Math.max(sums[0], sums[1]), pp = sums[0], res = p;
        for (int i = 2; i < len; i++) {
            // dp[i] = Math.max(dp[i - 2] + sum[i], dp[i - 1]);
            res = Math.max(pp + sums[i], p);
            pp = p;
            p = res;
        }
        return res;
    }
}
