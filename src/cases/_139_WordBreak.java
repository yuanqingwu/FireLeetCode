package cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tag.ArrayTag;
import tag.BFS_BreadthFirstSearch;
import tag.DFS_DepthFirstSearch;
import tag.DynamicProgramming;
import tag.HashTable;
import tag.Memoization;
import tag.StringTag;
import tag.TrieTag;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 * <p>
 * 
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * 
 * <p>
 * 解析：动态规划(完全背包问题)
 * <p>
 * 状态转移方程：
 * dp[i]=dp[j] && check(s[j..i−1])
 * <p>
 * 其中 dp[i] 表示字符串 s 前 i 个字符是否能够拆分成若干个字典中出现的单词,
 * check(s[j..i−1])表示字符串s[j..i−1]是否出现在字典中。
 * j 是小于 i 的正整数，表示对 s 的前 i 个字符进行拆分，而 j 可以取到的范围应该是从 0 到
 * i−1。也就是说，我们枚举所有的可能性，只要在某个位置 j，满足 s 的前 j 个字符可以拆分成若干个字典中出现的单词，并且 s[j..i−1]
 * 这个子串恰好也出现在字典中，那么状态 dp[i] 就为 true。
 * 
 */
@DynamicProgramming
@StringTag
@HashTable
@ArrayTag
@Memoization
@TrieTag
@DFS_DepthFirstSearch
@BFS_BreadthFirstSearch
public class _139_WordBreak extends BaseSolution {

    public static void main(String[] args) {
        _139_WordBreak wordBreak = new _139_WordBreak();
        wordBreak.run();
    }

    @Override
    void solution() {

        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        boolean res = wordBreak(s, wordDict);
        println("res:" + res);
        assertTrue(res);

        String s1 = "catsandog";
        List<String> wordDict1 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean res1 = wordBreak(s1, wordDict1);
        println("res:" + res1);
        assertFalse(res1);
    }

    /**
     * 时间复杂度：O(n^2),其中n为字符串长度，一共有O(n)个状态需要计算，每次计算需要枚举O(n)个分割点，哈希表判断一个字符串是
     * 否出现在给定的字符串列表需要的时间为O(1)的时间。因此总的时间复杂度为O(n^2).
     * 空间复杂度: O(n), 其中n为字符串长度，我们需要O(n)的空间存放dp值，以及哈希表也需要O(n)的空间，所以总空间复杂度为O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        int maxLen = 0;
        Set<String> words = new HashSet<String>();
        for (String str : wordDict) {
            maxLen = Math.max(maxLen, str.length());
            words.add(str);
        }

        for (int i = 1; i <= len; i++) {
            for (int j = i; j >= 0 && j >= i - maxLen; j--) {
                if (words.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[len];
    }

    @BFS_BreadthFirstSearch
    public boolean wordBreak_BFS(String s, List<String> wordDict) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int slength = s.length();
        boolean[] visited = new boolean[slength + 1];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int start = queue.poll().intValue();
                for (String word : wordDict) {
                    int nextStart = start + word.length();
                    if (nextStart > slength || visited[nextStart]) {
                        continue;
                    }

                    if (s.indexOf(word, start) == start) {
                        if (nextStart == slength) {
                            return true;
                        }

                        queue.add(nextStart);
                        visited[nextStart] = true;
                    }
                }
            }
        }

        return false;
    }

    @DFS_DepthFirstSearch
    public boolean wordBreak_DFS(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length() + 1];
        return dfs(s, 0, wordDict, visited);
    }

    private boolean dfs(String s, int start, List<String> wordDict, boolean[] visited) {
        for (String word : wordDict) {
            int nextStart = start + word.length();
            if (nextStart > s.length() || visited[nextStart]) {
                continue;
            }

            if (s.indexOf(word, start) == start) {
                if (nextStart == s.length() || dfs(s, nextStart, wordDict, visited)) {
                    return true;
                }
                visited[nextStart] = true;
            }
        }
        return false;
    }
}
