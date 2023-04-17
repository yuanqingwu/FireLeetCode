import java.util.HashMap;

/*
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Tags: Hash Table, Two Pointers, String
 * 
 * 解题思路：滑动窗口
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(m)  m为字符空间
 */
public class _3LongestSubString extends BaseSolution {


    public static void main(String[] args) {
        _3LongestSubString longestSubString = new _3LongestSubString();
        longestSubString.run();
    }

    @Override
    void solution() {

        String test1 = "abcabcbb";
        println("length : "+lengthOfLongestSubstring(test1));

        String test2 = "bbbbb";
        println("length : "+lengthOfLongestSubstring(test2));

        String test3 = "pwwkew";
        println("length : "+lengthOfLongestSubstring(test3));
        
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
