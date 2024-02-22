package cases;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import tag.Tag_HashTable;
import tag.SlidingWindow;
import tag.Tag_String;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a
 * substring.
 * <p>
 * Constraints:
 * - 0 <= s.length <= 5 * 104
 * - s consists of English letters, digits, symbols and spaces.
 * 
 */
@Tag_HashTable
@Tag_String
@SlidingWindow
public class _3_LongestSubString extends BaseSolution {

    public static void main(String[] args) {
        _3_LongestSubString longestSubString = new _3_LongestSubString();
        longestSubString.run();
    }

    @Override
    void solution() {

        String test1 = "abcabcbb";
        int length1 = lengthOfLongestSubstring(test1);
        assertEquals(3, length1);

        String test2 = "bbbbb";
        int length2 = lengthOfLongestSubstring(test2);
        assertEquals(1, length2);

        String test3 = "pwwkew";
        int length3 = lengthOfLongestSubstring(test3);
        assertEquals(3, length3);

    }

    @SlidingWindow(timeComplexity = "O(N)", spaceComplexity = "O(N)")
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
