package cases;

import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.Greedy;
import tag.TwoPointers;

/**
 * 11. 盛最多水的容器
 * <p>
 * You are given an integer array height of length n. There are n vertical lines
 * drawn such that the two endpoints of the ith line are (i, 0) and (i,
 * height[i]).
 * 
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 * 
 * Return the maximum amount of water a container can store.
 * 
 * Notice that you may not slant the container.
 * <p>
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 返回容器可以储存的最大水量。
 * 
 * 说明：你不能倾斜容器。
 * <p>
 * Example 1:
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array
 * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
 * container can contain is 49.
 * <p>
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *
 */
@Greedy
@TwoPointers
@Array
public class _11_ContainerWithMostWater extends BaseSolution {

    public static void main(String[] args) {
        _11_ContainerWithMostWater containerWithMostWater = new _11_ContainerWithMostWater();
        containerWithMostWater.run();
    }

    @Override
    void solution() {
        int[] height1 = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        assertEquals(49, maxArea(height1));

        int[] height2 = new int[] { 1, 1 };
        assertEquals(1, maxArea(height2));
    }

    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            max = height[i] < height[j] ? Math.max(max, (j - i) * height[i++]) : Math.max(max, (j - i) * height[j--]);
        }
        return max;
    }

}
