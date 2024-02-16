package cases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tag.ArrayTag;
import tag.DynamicProgramming;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * 
 * For each step, you may move to an adjacent number of the row below. More
 * formally, if you are on index i on the current row, you may move to either
 * index i or index i + 1 on the next row.
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1
 * 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined
 * above).
 * 
 * <p>
 * 
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 * 
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 */
@DynamicProgramming
@ArrayTag
public class _120_Triangle extends BaseSolution {

    public static void main(String[] args) {
        _120_Triangle triangle = new _120_Triangle();
        triangle.run();
    }

    @Override
    void solution() {
        Integer[][] triangle = new Integer[][] { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Integer> row = new ArrayList<>();
            row.addAll(Arrays.asList(triangle[i]));
            data.add(row);
        }

        assertEquals(11, minimumTotal(data));
    }

    @DynamicProgramming(timeComplexity = "O(N^2)", spaceComplexity = "O(N)")
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowLen = triangle.size();
        // 1. 首先使用二维数组求解：定义dp[i][j] 表示从点 (i,j)
        // 到底边的最小路径和。自底向上迭代。（自底向上的优势是：1.可以规避边界问题，2.出口dp[0]就是最终答案。）
        // dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
        // 2. 空间优化：由于求解dp[i][j] 时，只用到了下一行的 dp[i+1][j] 和 dp[i+1][j+1]。
        // 所以可以将二维数组降维成一维数组。
        int[] dp = new int[rowLen + 1];
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

}
