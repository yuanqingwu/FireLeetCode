package cases;

import static org.junit.Assert.assertEquals;

import tag.DynamicProgramming;
import tag.Tag_String;

/**
 * [32. Longest Valid Parentheses
 * 最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)
 * <p>
 * Given a string containing just the characters '(' and ')', return the length
 * of the longest valid (well-formed) parentheses
 * substring.
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * <p>
 * Explanation: The longest valid parentheses substring is "()".
 * <p>
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * <p>
 * Explanation: The longest valid parentheses substring is "()()".
 * <p>
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * 
 * Constraints:
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
@Tag_String
@DynamicProgramming
public class _32_LongestValidParentheses extends BaseSolution {

    public static void main(String[] args) {
        _32_LongestValidParentheses longestValidParentheses = new _32_LongestValidParentheses();
        longestValidParentheses.run();
    }

    @Override
    void solution() {
        assertEquals(2, longestValidParentheses("(()"));
        assertEquals(4, longestValidParentheses(")()())"));
        assertEquals(0, longestValidParentheses(""));
        assertEquals(2, longestValidParentheses1("(()"));
        assertEquals(4, longestValidParentheses1(")()())"));
        assertEquals(0, longestValidParentheses1(""));
    }

    /**
     * dp[i] 表示以 s[i] 结尾的最长有效括号的长度.
     * 显然有效的子串一定以 ‘)’ 结尾,所以当s[i]是')"的情况下，考虑s[i-1]的情况：
     * 1. s[i-1] = '(' 时, dp[i] = dp[i-2] + 2
     * 2. s[i-1] = ')' 时, 尝试找到与 s[i] 对应的 '(' 的位置 j = i - dp[i-1] - 1,
     * 如果 s[j] = '('，则 dp[i] = dp[i-1] + 2 + dp[j-1]。由于dp数组保存的是最长有效子串，所以此时j-1一定为‘)’
     * 
     */
    @DynamicProgramming(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public int longestValidParentheses(String s) {
        int len = s.length();
        int max = 0;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i > 2 ? dp[i - 2] + 2 : 2;
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2 + (j >= 2 ? dp[j - 1] : 0);
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 我们利用两个计数器 left 和 right。首先，我们从左到右遍历字符串，对于遇到的每个 ‘(’，我们增加 left
     * 计数器，对于遇到的每个 ‘)’ ，我们增加 right 计数器。每当left 计数器与
     * right计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当 right 计数器比
     * left 计数器大时，我们将 left 和right 计数器同时变回 0。
     * 
     * 但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (()
     * 
     * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来
     */
    public int longestValidParentheses1(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

}
