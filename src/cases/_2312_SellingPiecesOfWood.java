package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.Tag_Array;

/**
 * [2312. 卖木头](https://leetcode.cn/problems/selling-pieces-of-wood/description/)
 * <p>
 * You are given two integers m and n that represent the height and width of a
 * rectangular piece of wood. You are also given a 2D integer array prices,
 * where prices[i] = [hi, wi, pricei] indicates you can sell a rectangular piece
 * of wood of height hi and width wi for pricei dollars.
 * <p>
 * To cut a piece of wood, you must make a vertical or horizontal cut across the
 * entire height or width of the piece to split it into two smaller pieces.
 * After cutting a piece of wood into some number of smaller pieces, you can
 * sell pieces according to prices. You may sell multiple pieces of the same
 * shape, and you do not have to sell all the shapes. The grain of the wood
 * makes a difference, so you cannot rotate a piece to swap its height and
 * width.
 * <p>
 * Return the maximum money you can earn after cutting an m x n piece of wood.
 * <p>
 * Note that you can cut the piece of wood as many times as you want.
 * <p>
 * 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组 prices ，其中 prices[i] = [hi, wi,
 * pricei] 表示你可以以 pricei 元的价格卖一块高为 hi 宽为 wi 的矩形木块。
 * 
 * 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：
 * 
 * 沿垂直方向按高度 完全 切割木块，或
 * 沿水平方向按宽度 完全 切割木块
 * 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 不能
 * 旋转切好后木块来交换它的高度值和宽度值。
 * 
 * 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。
 * 
 * 注意你可以切割木块任意次。
 * <p>
 * Example 1:
 * <p>
 * Input: m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
 * Output: 19
 * <p>
 * Explanation: The diagram above shows a possible scenario. It consists of:
 * - 2 pieces of wood shaped 2 x 2, selling for a price of 2 * 7 = 14.
 * - 1 piece of wood shaped 2 x 1, selling for a price of 1 * 3 = 3.
 * - 1 piece of wood shaped 1 x 4, selling for a price of 1 * 2 = 2.
 * <p>
 * This obtains a total of 14 + 3 + 2 = 19 money earned.
 * It can be shown that 19 is the maximum amount of money that can be earned.
 * <p>
 * Example 2:
 * <p>
 * Input: m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
 * Output: 32
 * <p>
 * Explanation: The diagram above shows a possible scenario. It consists of:
 * - 3 pieces of wood shaped 3 x 2, selling for a price of 3 * 10 = 30.
 * - 1 piece of wood shaped 1 x 4, selling for a price of 1 * 2 = 2.
 * <p>
 * This obtains a total of 30 + 2 = 32 money earned.
 * It can be shown that 32 is the maximum amount of money that can be earned.
 * Notice that we cannot rotate the 1 x 4 piece of wood to obtain a 4 x 1 piece
 * of wood.
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= m, n <= 200
 * 1 <= prices.length <= 2 * 104
 * prices[i].length == 3
 * 1 <= hi <= m
 * 1 <= wi <= n
 * 1 <= pricei <= 106
 * All the shapes of wood (hi, wi) are pairwise distinct.
 */
@Tag_Array
@DynamicProgramming
public class _2312_SellingPiecesOfWood extends BaseSolution {

    public static void main(String[] args) {
        _2312_SellingPiecesOfWood sellingPiecesOfWood = new _2312_SellingPiecesOfWood();
        sellingPiecesOfWood.run();
    }

    @Override
    void solution() {
        int m = 3, n = 5;
        int[][] prices = { { 1, 4, 2 }, { 2, 2, 7 }, { 2, 1, 3 } };
        long res = sellingWood(m, n, prices);
        assertEquals(19, res);

        m = 4;
        n = 6;
        prices = new int[][] { { 3, 2, 10 }, { 1, 4, 2 }, { 4, 1, 3 } };
        res = sellingWood(m, n, prices);
        assertEquals(32, res);
    }

    @DynamicProgramming(timeComplexity = "O(m*n*max(m,n))", spaceComplexity = "O(m*n)")
    public long sellingWood(int m, int n, int[][] prices) {

        int[][] pre = new int[m + 1][n + 1];
        for (int[] i : prices) {
            pre[i[0]][i[1]] = i[2];
        }

        // 定义 f[i][j] 表示切割一块高 i 宽 j 的木块，能得到的最多钱数。
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = pre[i][j];
                // 如果横着切开，枚举切割位置（高度）k，得到两个宽为 j，高分别为 k 和 i−k 的木块
                for (int k = 0; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                // 如果竖着切开，枚举切割位置（宽度）k，得到两个高为 i，宽分别为 k 和 j−k 的木块
                for (int k = 0; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }

}
