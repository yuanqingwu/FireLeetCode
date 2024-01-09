import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.DynamicProgramming;

/**
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 * 
 * Return the number of combinations that make up that amount. If that amount
 * of money cannot be made up by any combination of the coins, return 0.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * 
 * <p>
 * 
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。题目数据保证结果符合 32 位带符号整数。
 * 
 * <p>
 * Example 1:
 * 
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 
 * <p>
 * 
 * Example 2:
 * 
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * 
 * <p>
 * Example 3:
 * 
 * Input: amount = 10, coins = [10]
 * Output: 1
 * 
 * <p>
 * Constraints:
 * 
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */
@Array
@DynamicProgramming
public class _518_CoinChangeII extends BaseSolution {

    public static void main(String[] args) {
        _518_CoinChangeII coinChangeII = new _518_CoinChangeII();
        coinChangeII.run();
    }

    @Override
    void solution() {
        int res = change(5, new int[] { 1, 2, 5 });
        assertEquals(4, res);

        int res1 = change(3, new int[] { 2 });
        assertEquals(0, res1);

        int res2 = change(10, new int[] { 10 });
        assertEquals(1, res2);
    }

    @DynamicProgramming(timeComplexity = "O(n*amount)", spaceComplexity = "O(amount)")
    public int change(int amount, int[] coins) {

        int len = coins.length;
        // dp[i][j] 表示用前i个硬币组成j的组合数:dp[i][j] = dp[i-1][j]+dp[i][j-coins[i]]
        // 空间优化后：dp[j] = dp[j]+dp[j-coins[i]]
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
        // 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
        for (int i = 0; i < len; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

}
