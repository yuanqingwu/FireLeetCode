package cases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import tag.ArrayTag;
import tag.BinarySearch;
import tag.DynamicProgramming;
import tag.Greedy;

/**
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7]
 * 的子序列。
 * 
 * <p>
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
 * length is 4.
 * <p>
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * <p>
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * Constraints:
 * 
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
@ArrayTag
@DynamicProgramming
@BinarySearch
public class _300_LongestIncreasingSubsequence extends BaseSolution {

    public static void main(String[] args) {
        _300_LongestIncreasingSubsequence longestIncreasingSubsequence = new _300_LongestIncreasingSubsequence();
        longestIncreasingSubsequence.run();
    }

    @Override
    void solution() {
        int[] nums = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
        assertEquals(4, lengthOfLIS(nums));
    }

    @Greedy
    @BinarySearch
    @DynamicProgramming(timeComplexity = "O(NlogN)", spaceComplexity = "O(N)")
    public int lengthOfLIS(int[] nums) {
        // tails数组是以当前长度连续子序列的最小末尾元素，如此我们接下去找到一个比该数大的数的几率肯定会更大
        // 如tail[0]是求长度为1的连续子序列时的最小末尾元素
        // 例：在 1 6 4中 tail[0]=1 tail[1]=4 没有tail[2] 因为无法到达长度为3的连续子序列
        int tails[] = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            // 每个元素开始遍历 看能否插入到之前的tails数组的位置，如果能插到合适位置
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            println("i = " + i + "  j= " + j + "  res= " + res + " num= " + num + " array:" + Arrays.toString(tails));
            // 如果没有到达j==res这个条件 就说明tail数组里只有部分比这个num要小 那么就把num插入到tail数组合适的位置即可
            // 但是由于这样的子序列长度肯定是没有res长的 因此res不需要更新
            // 注意：tail数组并没有显式表示出具体序列的模样，只记录了每种长度序列中结尾元素最小的那个序列的结尾元素，tails一定是递增的
            tails[i] = num;
            // j==res 说明目前tail数组的元素都比当前的num要小 因此最长子序列的长度可以增加了
            if (j == res) {
                res++;
            }
        }
        return res;
    }

    @DynamicProgramming(timeComplexity = "O(N^2)", spaceComplexity = "O(N)")
    public int lengthOfLIS1(int[] nums) {
        int maxValue = 0;
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;

    }

    @DynamicProgramming(timeComplexity = "O(N^2)", spaceComplexity = "O(N)")
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] <= dp[res - 1]) {
                // 当下一个数小于等于当前数组最大数的时候，需要遍历当前数组，将此值按照递增的顺序放入合适的位置。此时只是优化了当前数组，有效长度值保持不变。
                for (int j = 0; j < res; j++) {
                    if (dp[j] >= nums[i]) {
                        dp[j] = nums[i];
                        break;
                    }
                }
            } else {
                // 当下一个数大于当前数组最大数的时候，直接加到数组后面，同时有效长度值加1.
                dp[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}
