package cases;

import static org.junit.Assert.assertEquals;

import tag.ArrayTag;
import tag.DynamicProgramming;
import tag.Greedy;

/**
 * [122. 买卖股票的最佳时机
 * II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii)
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day.
 * 
 * On each day, you may decide to buy and/or sell the stock. You can only hold
 * at most one share of the stock at any time. However, you can buy it then
 * immediately sell it on the same day.
 * 
 * Find and return the maximum profit you can achieve.
 * <p>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 
 * 返回 你能获得的 最大 利润 。
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * <p>
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit =
 * 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 =
 * 3.
 * Total profit is 4 + 3 = 7.
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * <p>
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
 * 5-1 = 4.
 * Total profit is 4.
 * <p>
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * <p>
 * Explanation: There is no way to make a positive profit, so we never buy the
 * stock to achieve the maximum profit of 0.
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 3 * 104
 * 
 * 0 <= prices[i] <= 104
 * 
 */
@Greedy
@DynamicProgramming
@ArrayTag
public class _122_BestTimeToBuyAndSellStockII extends BaseSolution {

    public static void main(String[] args) {
        _122_BestTimeToBuyAndSellStockII bestTimeToBuyAndSellStockII = new _122_BestTimeToBuyAndSellStockII();
        bestTimeToBuyAndSellStockII.run();
    }

    @Override
    void solution() {
        assertEquals(7, maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        assertEquals(7, maxProfit1(new int[] { 7, 1, 5, 3, 6, 4 }));
    }

    @Greedy(timeComplexity = "n", spaceComplexity = "1")
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int res = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    @DynamicProgramming(timeComplexity = "n", spaceComplexity = "1")
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int buy = -prices[0];
        int sale = 0;

        int prebuy = buy;
        int presale = sale;
        for (int i = 1; i < len; i++) {
            buy = Math.max(prebuy, presale - prices[i]);
            sale = Math.max(prebuy + prices[i], presale);
            prebuy = buy;
            presale = sale;
        }
        return sale;
    }

}
