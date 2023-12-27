import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import tag.HashTable;
import tag.SlidingWindow;
import tag.Strings;

/*
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Tags: Hash Table, Two Pointers, String
 * 
 * Constraints:
 * - 0 <= s.length <= 5 * 104
 * - s consists of English letters, digits, symbols and spaces.
 * 
 */
@HashTable
@Strings
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
