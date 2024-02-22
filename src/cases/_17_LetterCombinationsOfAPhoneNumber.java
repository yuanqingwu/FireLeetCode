package cases;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import tag.BackTracking;
import tag.Tag_String;

/**
 * [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/)
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent. Return the answer in any
 * order.
 * 
 * A mapping of digits to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 
 * <p>
 * 
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * 
 * <p>
 * 
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * 
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
@Tag_String
@BackTracking
public class _17_LetterCombinationsOfAPhoneNumber extends BaseSolution {

    public static void main(String[] args) {
        _17_LetterCombinationsOfAPhoneNumber lettercom = new _17_LetterCombinationsOfAPhoneNumber();
        lettercom.run();
    }

    @Override
    void solution() {
        List<String> res = letterCombinations("23");
        List<String> expected = new ArrayList<String>(List.of("ad","ae","af","bd","be","bf","cd","ce","cf"));
        assertArrayEquals(expected.toArray(), res.toArray());
    }

    String[] letter_map = { " ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        backtracking(digits, 0);
        return res;
    }

    @BackTracking
    public void backtracking(String digits, int index) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        char c = digits.charAt(index);
        int pos = c - '0';
        String letter = letter_map[pos];
        for (int i = 0; i < letter.length(); i++) {
            path.append(letter.charAt(i));
            backtracking(digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
