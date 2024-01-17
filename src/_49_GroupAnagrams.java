import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import tag.Array;
import tag.Sorting;
import tag.StringTag;

/**
 * Given an array of strings strs, group the anagrams together. You can return
 * the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * <p>
 * Example 1:
 * 
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * <p>
 * 
 * Example 2:
 * 
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * <p>
 * 
 * Example 3:
 * 
 * Input: strs = ["a"]
 * Output: [["a"]]
 * 
 * <p>
 * 
 * Constraints:
 * 
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
@Array
@StringTag
@Sorting
public class _49_GroupAnagrams extends BaseSolution {

    public static void main(String[] args) {
        _49_GroupAnagrams groupAnagrams = new _49_GroupAnagrams();
        groupAnagrams.run();
    }

    @Override
    void solution() {

        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(groupAnagrams(strs));
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("eat", "tea", "ate"),
                Arrays.asList("bat"),
                Arrays.asList("tan", "nat"));
        List<List<String>> result = groupAnagrams(strs);
        Assert.assertEquals(expected, result);

    }

    @Sorting(timeComplexity = "O(nklogk)", spaceComplexity = "O(nk)")
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] sArray = s.toCharArray();
            Arrays.sort(sArray);
            String sortStr = new String(sArray);
            if (map.containsKey(sortStr)) {
                map.get(sortStr).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sortStr, list);
            }
        }

        return new ArrayList<>(map.values());
    }
}
