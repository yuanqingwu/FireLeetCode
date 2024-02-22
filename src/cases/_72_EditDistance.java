package cases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import tag.DynamicProgramming;
import tag.Tag_String;

/**
 * 72. 编辑距离
 * <p>
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * 
 * <p>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
 * 
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * 
 * <p>
 * 
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * 
 * <p>
 * 
 * Constraints:
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
@DynamicProgramming
@Tag_String
public class _72_EditDistance extends BaseSolution {

    public static void main(String[] args) {
        _72_EditDistance editDistance = new _72_EditDistance();
        editDistance.run();
    }

    @Override
    void solution() {
        assertEquals(3, minDistance("horse", "ros"));
        assertEquals(5, minDistance("intention", "execution"));
    }

    @DynamicProgramming(timeComplexity = "m*n", spaceComplexity = "m*n")
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int inset = dp[i - 1][j];
                    int delete = dp[i][j - 1];
                    int replace = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(inset, delete), replace) + 1;
                }
            }
            // System.out.println(Arrays.toString(dp[i]));
        }
        return dp[len1][len2];
    }

    @DynamicProgramming(timeComplexity = "m*n", spaceComplexity = "m")
    public int minDistance1(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[] dp = new int[len1 + 1];

        for (int i = 1; i <= len1; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= len2; i++) {
            dp[0] = i;
            int replace = i - 1;
            for (int j = 1; j <= len1; j++) {
                int tmp = dp[j];
                if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]) + 1, replace);
                } else {
                    int inset = dp[j];
                    int delete = dp[j - 1];
                    dp[j] = Math.min(Math.min(inset, delete), replace) + 1;
                }
                replace = tmp;
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[len1];
    }
}
