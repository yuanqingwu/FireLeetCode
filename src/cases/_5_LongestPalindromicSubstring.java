package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.Tag_String;

/**
 * 5. 最长回文子串
 * <p>
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
@Tag_String
@DynamicProgramming
public class _5_LongestPalindromicSubstring extends BaseSolution {

    public static void main(String[] args) {
        _5_LongestPalindromicSubstring longestPalindromicSubstring = new _5_LongestPalindromicSubstring();
        longestPalindromicSubstring.run();
    }

    @Override
    void solution() {
        String s = "babad";
        assertEquals("bab", longestPalindrome1(s));
    }

    @DynamicProgramming(timeComplexity = "O(N^2)", spaceComplexity = "O(N^2)")
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        int begin = 0;
        int max = 1;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (charArray[i] == charArray[j]) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    // center spread
    public String longestPalindrome1(String s) {
        int len = s.length();
        char[] ss = s.toCharArray();
        int[] res = new int[2];
        for (int i = 0; i < len - 1; i++) {
            int[] one = getMaxString(ss, i, i);
            int[] two = getMaxString(ss, i, i + 1);
            int[] max = two[1] - two[0] > one[1] - one[0] ? two : one;
            if (max[1] - max[0] > res[1] - res[0]) {
                res = max;
            }
        }

        return s.substring(res[0], res[1] + 1);
    }

    public int[] getMaxString(char[] s, int start, int end) {
        int[] res = new int[2];
        while (start >= 0 && end < s.length) {
            if (s[start] == s[end]) {
                start--;
                end++;
            } else {
                break;
            }
        }
        res[0] = start + 1;
        res[1] = end - 1;
        return res;
    }

}
