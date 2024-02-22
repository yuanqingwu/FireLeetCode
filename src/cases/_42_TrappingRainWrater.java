package cases;

import org.junit.Assert;

import tag.Tag_Array;
import tag.DynamicProgramming;
import tag.TwoPointers;

/**
 * 42. 接雨水
 * <p>
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * Example 1:
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array
 * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
 * are being trapped.
 * <p>
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
@Tag_Array
@TwoPointers
@DynamicProgramming
public class _42_TrappingRainWrater extends BaseSolution {

    public static void main(String[] args) {
        _42_TrappingRainWrater trappingRainWrater = new _42_TrappingRainWrater();
        trappingRainWrater.run();
    }

    @Override
    void solution() {
        int[] height = new int[] { 4, 2, 0, 3, 2, 5 };
        assert trap(height) == 9;
        Assert.assertEquals(9, trap(height));
    }

    public int trap(int[] height) {
        int len = height.length;
        int[] maxl = new int[len];
        int[] maxr = new int[len];
        for (int i = 1; i < len - 1; i++) {
            maxl[i] = Math.max(maxl[i - 1], height[i - 1]);
        }

        for (int j = len - 2; j > 0; j--) {
            maxr[j] = Math.max(maxr[j + 1], height[j + 1]);
        }

        int max = 0;
        for (int k = 1; k < len - 1; k++) {
            int min = Math.min(maxl[k], maxr[k]);
            if (min > height[k]) {
                max = max + min - height[k];
            }
        }
        return max;
    }

}
