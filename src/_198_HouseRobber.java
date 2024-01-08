import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.DynamicProgramming;

/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the
 * police.
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素
 * 就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 
 * Example 1：
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * Example 2：
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5
 * (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * Constraints：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * 
 * 
 * 解析：动态规划
 * 用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
 * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
 * 
 * 动态规划的的四个解题步骤是：
 * 
 * - 定义子问题
 * - 写出子问题的递推关系
 * - 确定 DP 数组的计算顺序
 * - 空间优化（可选）
 */
@Array
@DynamicProgramming
public class _198_HouseRobber extends BaseSolution {

    public static void main(String[] args) {

        _198_HouseRobber houseRobber = new _198_HouseRobber();
        houseRobber.run();
    }

    @Override
    void solution() {

        int[] test1 = new int[] { 2, 7, 9, 3, 1 };
        int res = rob_dp(test1);
        println("res:" + res);
        assertEquals(12, res);

    }

    /**
     * 时间复杂度：O(n)，其中 n 是数组长度。只需要对数组遍历一次。
     * 
     * 空间复杂度O(1)。使用滚动数组，可以只存储前两间房屋的最高总金额，
     * 而不需要存储整个数组的结果，因此空间复杂度是O(1)。
     * 
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * DP 数组也可以叫”子问题数组”，因为 DP 数组中的每一个元素都对应一个子问题。
     * dp[k] 对应子问题 f(k)，即偷前 k 间房子的最大金额。
     */
    public int rob_dp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }
        return dp[N];
    }

}
