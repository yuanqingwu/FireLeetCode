import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.StringTag;

/**
 * Given two strings text1 and text2, return the length of their longest common
 * subsequence. If there is no common subsequence, return 0.
 * 
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative
 * order of the remaining characters.
 * 
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both
 * strings.
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 
 * <p>
 * Example 1:
 * 
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * <p>
 * Example 2:
 * 
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * <p>
 * Example 3:
 * 
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * 
 * <p>
 * Constraints:
 * 
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 * 
 */
@DynamicProgramming
@StringTag
public class _1143_LongestCommonSubsequence extends BaseSolution {

    public static void main(String[] args) {
        _1143_LongestCommonSubsequence longestCommonSubsequence = new _1143_LongestCommonSubsequence();
        longestCommonSubsequence.run();
    }

    @Override
    void solution() {
        assertEquals(3, longestCommonSubsequence("abcde", "ace"));
        assertEquals(1, longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    }

    @DynamicProgramming(timeComplexity = "O(n^2)", spaceComplexity = "O(m*n)")
    public int longestCommonSubsequence(String text1, String text2) {

        int len1 = text1.length();
        int len2 = text2.length();
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // for (int i = 0; i <= len1; i++) {
        // System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[len1][len2];
    }

}
