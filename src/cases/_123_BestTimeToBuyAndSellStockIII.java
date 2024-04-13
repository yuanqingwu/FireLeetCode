package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.Tag_Array;

/**
 * [123. Best Time to Buy and Sell Stock III
 * 买卖股票的最佳时机III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two
 * transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * <p>
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit =
 * 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 =
 * 3.
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * <p>
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
 * 5-1 = 4.
 * <p>
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you
 * are engaging multiple transactions at the same time. You must sell before
 * buying again.
 * <p>
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * <p>
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
@Tag_Array
@DynamicProgramming
public class _123_BestTimeToBuyAndSellStockIII extends BaseSolution {

    public static void main(String[] args) {
        _123_BestTimeToBuyAndSellStockIII solution = new _123_BestTimeToBuyAndSellStockIII();
        solution.run();
    }

    @Override
    void solution() {
        int[] prices = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
        assertEquals(6, maxProfit(prices));
        assertEquals(6, maxProfitDp1(prices));
    }

    @DynamicProgramming(timeComplexity = "n", spaceComplexity = "n")
    public int maxProfit(int[] prices) {
        int len = prices.length;

        /*
         * 定义 5 种状态:
         * 0: 没有操作, 1: 第一次持有股票, 2: 第一次不持有股票, 3: 第二次持有股票, 4: 第二次不持有股票
         * 
         * 一天一共就有五个状态:
         * 
         * 1. 没有操作 （其实我们也可以不设置这个状态）
         * 2. 第一次持有股票
         * 3. 第一次不持有股票
         * 4. 第二次持有股票
         * 5. 第二次不持有股票
         * dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金。
         * 
         * dp[i][1]，表示的是第i天，买入股票的状态，并不是说一定要第i天买入股票，例如 dp[i][1]有可能 第 i-1天
         * 就买入了，那么 dp[i][1] 延续买入股票的这个状态。
         */
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        // 初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        dp[0][3] = -prices[0];

        for (int i = 1; i < len; i++) {
            // max(第 i 天有操作情况与无操作情况)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i][3] + prices[i]);
        }

        return dp[len - 1][4];
    }

    @DynamicProgramming(timeComplexity = "n", spaceComplexity = "1")
    public int maxProfitDp1(int[] prices) {
        int len = prices.length;
        int buy1 = -prices[0];
        int sale1 = 0;
        int buy2 = -prices[0];
        int sale2 = 0;
        for (int i = 1; i < len; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sale1 = Math.max(sale1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sale1 - prices[i]);
            sale2 = Math.max(sale2, buy2 + prices[i]);
        }
        return sale2;
    }

}
