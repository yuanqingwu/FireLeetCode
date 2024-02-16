package cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import tag.ArrayTag;
import tag.DynamicProgramming;
import tag.Greedy;

/**
 * 55. 跳跃游戏
 * <p>
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum
 * jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
@Greedy
@DynamicProgramming
@ArrayTag
public class _55_JumpGame extends BaseSolution {

    public static void main(String[] args) {
        _55_JumpGame jumpGame = new _55_JumpGame();
        jumpGame.run();
    }

    @Override
    void solution() {
        boolean res = canJump(new int[] { 2, 3, 1, 1, 4 });
        assertTrue(res);

        boolean res1 = canJump(new int[] { 3, 2, 1, 0, 4 });
        assertFalse(res1);

    }

    /**
     * time complexity:O(n)
     */
    @Greedy(timeComplexity = "O(n)")
    public boolean canJump(int[] nums) {
        int maxPos = 0;
        for (int i = 0; i < nums.length && i <= maxPos; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (maxPos >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * time complexity:O(n^2)
     */
    @DynamicProgramming(timeComplexity = "O(n^2)")
    public boolean canJump1(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;

        for (int i = 1; i < len; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len - 1];
    }
}
