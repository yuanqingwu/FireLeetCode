package cases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import tag.Tag_Array;
import tag.DynamicProgramming;
import tag.Greedy;
import tag.Sorting;

/**
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and
 * lefti < righti.
 * 
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can
 * be formed in this fashion.
 * 
 * Return the length longest chain which can be formed.
 * 
 * You do not need to use up all the given intervals. You can select pairs in
 * any order.
 * 
 * <p>
 * 给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。
 * 
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造
 * 数对链 。
 * 
 * 找出并返回能够形成的 最长数对链的长度 。
 * 
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * <p>
 * 
 * Example 1:
 * 
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * 
 * <p>
 * 
 * Example 2:
 * 
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 * 
 * <p>
 * Constraints:
 * 
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 */
@Greedy
@DynamicProgramming
@Tag_Array
@Sorting
public class _646_MaximumLengthofPairChain extends BaseSolution {

    public static void main(String[] args) {
        _646_MaximumLengthofPairChain maximumLengthofPairChain = new _646_MaximumLengthofPairChain();
        maximumLengthofPairChain.run();
    }

    @Override
    void solution() {
        int[][] pairs = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
        System.out.println(findLongestChain(pairs));
        assertEquals(2, findLongestChain(pairs));

        int[][] pairs2 = { { 1, 2 }, { 7, 8 }, { 4, 5 } };
        System.out.println(findLongestChainDp(pairs2));
        assertEquals(3, findLongestChain(pairs2));
    }

    /*
     * 要挑选最长数对链的第一个数对时，最优的选择是挑选第二个数字最小的，
     * 这样能给挑选后续的数对留下更多的空间。挑完第一个数对后，要挑第二个数对时，
     * 也是按照相同的思路，是在剩下的数对中，第一个数字满足题意的条件下，
     * 挑选第二个数字最小的。按照这样的思路，可以先将输入按照第二个数字排序，
     * 然后不停地判断第一个数字是否能满足大于前一个数对的第二个数字即可。
     */
    @Greedy(timeComplexity = "O(nlogn)", spaceComplexity = "O(logn)")
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int len = pairs.length;
        int end = pairs[0][1];

        int res = 1;
        for (int i = 1; i < len; i++) {
            if (pairs[i][0] > end) {
                res++;
                end = pairs[i][1];
            }
        }
        return res;
    }

    /*
     * 定义 dp[i]为以 pairs[i]
     * 为结尾的最长数对链的长度。计算 dp[i] 时，可以先找出所有的满足
     * pairs[i][0]>pairs[j][1] 的 j，并求出最大的
     * dp[j]，dp[i] 的值即可赋为这个最大值加一。这种动态规划的思路要求计算
     * dp[i] 时，所有潜在的 dp[j] 已经计算完成，可以先将
     * pairs 进行排序来满足这一要求。初始化时，dp 需要全部赋值为 111。
     */
    @DynamicProgramming(timeComplexity = "O(n^2)", spaceComplexity = "O(n)")
    public int findLongestChainDp(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }

}
