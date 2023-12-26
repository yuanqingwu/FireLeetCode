import tag.MathTag;
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
 * 
 * Example 1:
 * 
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 * Constraints:
 * 1 <= n <= 231 - 1
 */
@TwoPointers
@MathTag
public class _202_HappyNumber extends BaseSolution {

    public static void main(String[] args) {
        _202_HappyNumber happyNumber = new _202_HappyNumber();
        happyNumber.run();
    }

    @Override
    void solution() {

        assert isHappy(19) == true;
        assert isHappy(2) == false;
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
