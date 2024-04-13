package cases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import tag.DynamicProgramming;
import tag.Tag_Array;

/**
 * [188. Best Time to Buy and Sell Stock IV
 * 买卖股票的最佳时机IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day, and an integer k.
 * 
 * Find the maximum profit you can achieve. You may complete at most k
 * transactions: i.e. you may buy at most k times and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 * <p>
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * <p>
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit =
 * 4-2 = 2.
 * <p>
 * Example 2:
 * <p>
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * <p>
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit =
 * 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit
 * = 3-0 = 3.
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
@Tag_Array
@DynamicProgramming
public class _188_BestTimeToBuyAndSellStockIV extends BaseSolution {

    public static void main(String[] args) {
        _188_BestTimeToBuyAndSellStockIV solution = new _188_BestTimeToBuyAndSellStockIV();
        solution.run();
    }

    @Override
    void solution() {
        int k = 2;
        int[] prices = new int[] { 2, 4, 1 };
        int res = maxProfit(k, prices);
        assertEquals(2, res);

        prices = new int[] { 3, 2, 6, 5, 0, 3 };
        res = maxProfit(k, prices);
        assertEquals(7, res);

    }

    @DynamicProgramming(timeComplexity = "n*k", spaceComplexity = "k")
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        // buy[k]是持有第k次交易的股票时（可以是今天买的，也可以是以前买的），获取的最大利益；
        int[] buy = new int[k];
        // sell[k]是不持有第k次交易的股票时（可以是今天卖的，也可以是以前卖的），获取的最大利益。
        int[] sale = new int[k];
        Arrays.fill(buy, -prices[0]);
        for (int i = 1; i < len; i++) {
            buy[0] = Math.max(buy[0], -prices[i]);
            sale[0] = Math.max(sale[0], buy[0] + prices[i]);
            for (int j = 1; j < k; j++) {
                buy[j] = Math.max(buy[j], sale[j - 1] - prices[i]);
                sale[j] = Math.max(sale[j], buy[j] + prices[i]);
            }
        }
        return sale[k - 1];
    }

}
