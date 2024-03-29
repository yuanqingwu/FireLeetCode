package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.Tag_Math;
import tag.Memoization;

/**
 * 70. 爬楼梯
 * <p>
 * You are climbing a staircase. It takes n steps to reach the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * <P>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * <p>
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * 
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 * 
 * <p>
 * 解析：
 * 通过观察可以发现，爬楼梯问题形成的数列是斐波那契数列，我们用 f(x) 表示爬到第 x 级台阶的方案数，
 * 考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以我们可以列出如下式子：f(x)=f(x−1)+f(x−2)
 * <p>
 * - 方法 1：通过动态规划解决，以上便是动态规划的转移方程
 * - 方法 2：通过数学公式直接计算
 * 
 */
@DynamicProgramming
@Memoization
@Tag_Math
public class _70_ClimbingStairs extends BaseSolution {

    public static void main(String[] args) {

        _70_ClimbingStairs climbingStairs = new _70_ClimbingStairs();
        climbingStairs.run();

    }

    @Override
    void solution() {

        int res = climbStairs(2);
        println("climbingStairs: " + res);
        assertEquals(2, res);

        int res1 = climbStairs(45);
        println("climbingStairs: " + res1);
        assertEquals(1836311903, res1);

    }

    /**
     * 动态规划 + 滚动数组
     * 
     * 时间复杂度：循环执行 n 次，每次花费常数的时间代价，故渐进时间复杂度为 O(n)
     * 空间复杂度：这里只用了常数个变量作为辅助空间，故渐进空间复杂度为 O(1)。
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 通项公式计算，用斐波那契数列的公式即可解决问题
     * 
     * 时间复杂度：O(logn)
     * 
     */
    public int climbStairs1(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }

}