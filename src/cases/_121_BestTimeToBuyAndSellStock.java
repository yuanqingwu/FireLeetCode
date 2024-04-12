package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;

/**
 * [121.
 * 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return 0.
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * <p>
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =
 * 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you
 * must buy before you sell.
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * <p>
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class _121_BestTimeToBuyAndSellStock extends BaseSolution {

    public static void main(String[] args) {
        _121_BestTimeToBuyAndSellStock solution = new _121_BestTimeToBuyAndSellStock();
        solution.run();
    }

    @Override
    void solution() {
        int[] prices = new int[] { 7, 1, 5, 3, 6, 4 };
        int res = maxProfit(prices);
        assertEquals(5, res);
        assertEquals(5, maxProfitDp(prices));
        assertEquals(5, maxProfitDp1(prices));
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int i : prices) {
            max = Math.max(max, i - min);
            min = Math.min(min, i);
        }
        return max;
    }

    @DynamicProgramming(timeComplexity = "n", spaceComplexity = "n")
    public int maxProfitDp(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];

        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    @DynamicProgramming(timeComplexity = "n", spaceComplexity = "1")
    public int maxProfitDp1(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }

}
