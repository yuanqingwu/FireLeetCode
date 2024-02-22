package cases;

import static org.junit.Assert.assertEquals;

import tag.Tag_Array;
import tag.DynamicProgramming;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right, which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步
 * <p>
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * <p>
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 * <p>
 * Constraints:
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
@Tag_Array
@DynamicProgramming
public class _64_MinimumPathSum extends BaseSolution {

    public static void main(String[] args) {
        _64_MinimumPathSum minimumPathSum = new _64_MinimumPathSum();
        minimumPathSum.run();
    }

    @Override
    void solution() {
        int[][] grid = new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int res = minPathSumOpt(grid);
        assertEquals(7, res);

        int[][] grid1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        int res1 = minPathSum(grid1);
        assertEquals(12, res1);
    }

    @DynamicProgramming(timeComplexity = "O(m*n)", spaceComplexity = "O(m*n)")
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (i != 0 && j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if (i != 0 && j != 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @DynamicProgramming(timeComplexity = "O(m*n)", spaceComplexity = "O(n)")
    public int minPathSumOpt(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        dp[0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) {
                    dp[j] = grid[i][j] + dp[j - 1];
                } else if (i != 0 && j == 0) {
                    dp[j] = grid[i][j] + dp[j];
                } else if (i != 0 && j != 0) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }

        return dp[n - 1];
    }
}
