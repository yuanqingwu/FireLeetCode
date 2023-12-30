import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.DynamicProgramming;

/**
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 * 
 * Return the fewest number of coins that you need to make up that amount. If
 * that amount of money cannot be made up by any combination of the coins,
 * return -1.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 
 * 你可以认为每种硬币的数量是无限的。
 * 
 * <p>
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 * 
 * <p>
 * Constraints:
 * 
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
@DynamicProgramming
@Array
public class _322_CoinChange extends BaseSolution {

    public static void main(String[] args) {
        _322_CoinChange coinChange = new _322_CoinChange();
        coinChange.run();
    }

    @Override
    void solution() {
        int[] coins = new int[] { 1, 2, 5 };
        assertEquals(3, coinChange(coins, 11));
    }

    public int coinChange(int[] coins, int amount) {

        // 凑成 amount 金额的数最多只可能等于 amount（全用 1 元面值的），
        // 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
        int max = amount + 1;
        int[] dp = new int[max];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = max;// 初始化为最大值，便于下方min计算
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
