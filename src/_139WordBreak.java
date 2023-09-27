import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 * 
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * 
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * 
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * 
 * 解析：动态规划(完全背包问题)
 * 
 * 状态转移方程：
 * dp[i]=dp[j] && check(s[j..i−1])
 *
 * 其中 dp[i] 表示字符串 s 前 i 个字符是否能够拆分成若干个字典中出现的单词,
 * check(s[j..i−1])表示字符串s[j..i−1]是否出现在字典中。
 * j 是小于 i 的正整数，表示对 s 的前 i 个字符进行拆分，而 j 可以取到的范围应该是从 0 到
 * i−1。也就是说，我们枚举所有的可能性，只要在某个位置 j，满足 s 的前 j 个字符可以拆分成若干个字典中出现的单词，并且 s[j..i−1]
 * 这个子串恰好也出现在字典中，那么状态 dp[i] 就为 true。
 * 
 */
public class _139WordBreak extends BaseSolution {

    public static void main(String[] args) {
        _139WordBreak wordBreak = new _139WordBreak();
        wordBreak.run();
    }

    @Override
    void solution() {

        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        boolean res = wordBreak(s, wordDict);
        println("res:" + res);

        String s1 = "catsandog";
        List<String> wordDict1 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean res1 = wordBreak(s1, wordDict1);
        println("res:" + res1);
    }

    /**
     * 时间复杂度：O(n^2),其中n为字符串长度，一共有O(n)个状态需要计算，每次计算需要枚举O(n)个分割点，哈希表判断一个字符串是
     * 否出现在给定的字符串列表需要的时间为O(1)的时间。因此总的时间复杂度为O(n^2).
     * 空间复杂度: O(n), 其中n为字符串长度，我们需要O(n)的空间存放dp值，以及哈希表也需要O(n)的空间，所以总空间复杂度为O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}