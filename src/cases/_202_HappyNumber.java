package cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import tag.Tag_Math;
import tag.TwoPointers;

/**
 * Write an algorithm to determine if a number n is happy.
 * 
 * A happy number is a number defined by the following process:
 * 
 * Starting with any positive integer, replace the number by the sum of the
 * squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 
 * 「快乐数」 定义为：
 * 
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * <p>
 * Example 1:
 * <p>
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * Constraints:
 * 1 <= n <= 231 - 1
 */
@TwoPointers
@Tag_Math
public class _202_HappyNumber extends BaseSolution {

    public static void main(String[] args) {
        _202_HappyNumber happyNumber = new _202_HappyNumber();
        happyNumber.run();
    }

    @Override
    void solution() {
        assertTrue(isHappy(19));
        assertFalse(isHappy(2));
    }

    public boolean isHappy(int n) {

        int slow = n;
        int fast = step(n);
        while (fast != 1 && slow != fast) {
            slow = step(slow);
            fast = step(step(fast));
        }
        return fast == 1;
    }

    public int step(int n) {
        int res = 0;
        while (n > 0) {
            int d = n % 10;
            res = res + d * d;
            n = n / 10;
        }
        return res;
    }

}
