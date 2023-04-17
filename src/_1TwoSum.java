import java.util.Arrays;
import java.util.HashMap;

/**
 * <p> Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * <p>Tags: Array, Hash Table
 *
 * @implNote <p>Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class _1TwoSum extends BaseSolution {

    public static void main(String[] args) {

        _1TwoSum a1TwoSum = new _1TwoSum();
        a1TwoSum.run();

    }

    @Override
    void solution() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum(nums, target);

        println(Arrays.toString(res));
    }


    /**
     * 时间复杂度：
     * 空间复杂度：
     */
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            final Integer value = map.get(nums[i]);
            if (value != null) {
                return new int[]{value, i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }
}
