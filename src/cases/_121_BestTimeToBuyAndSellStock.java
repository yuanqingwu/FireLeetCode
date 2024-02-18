package cases;

import static org.junit.Assert.assertEquals;

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

}
