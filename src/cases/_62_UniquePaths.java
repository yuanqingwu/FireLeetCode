package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;

/**
 * [62. 不同路径 Unique Paths](https://leetcode.com/problems/unique-paths/)
 * <p>
 * There is a robot on an m x n grid. The robot is initially located at the
 * top-left corner (i.e., grid[0][0]). The robot tries to move to the
 * bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 * either down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 * <p>
 * The test cases are generated so that the answer will be less than or equal to
 * 2 * 109.
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 
 * 问总共有多少条不同的路径？
 * <p>
 * Example 1:
 * <p>
 * Input: m = 3, n = 7
 * Output: 28
 * <p>
 * Example 2:
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * <p>
 * Explanation: From the top-left corner, there are a total of 3 ways to reach
 * the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 100
 */
@DynamicProgramming
public class _62_UniquePaths extends BaseSolution {

    public static void main(String[] args) {
        _62_UniquePaths uniquePaths = new _62_UniquePaths();
        uniquePaths.run();
    }

    @Override
    void solution() {
        int m = 3, n = 7;
        int res = uniquePaths(m, n);
        assertEquals(28, res);

        m = 3;
        n = 2;
        res = uniquePaths(m, n);
        assertEquals(3, res);

        m = 7;
        n = 3;
        res = uniquePaths(m, n);
        assertEquals(28, res);

        m = 3;
        n = 3;
        res = uniquePaths(m, n);
        assertEquals(6, res);
    }

    @DynamicProgramming(timeComplexity = "O(m*n)", spaceComplexity = "O(m*n)")
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @DynamicProgramming(timeComplexity = "O(m*n)", spaceComplexity = "O(n)")
    public int uniquePathsOpt(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }

}
