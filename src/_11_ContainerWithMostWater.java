import static org.junit.Assert.assertEquals;

import tag.Array;
import tag.Greedy;
import tag.TwoPointers;

/**
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
 * 
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array
 * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
 * container can contain is 49.
 * 
 * TAG: Greedy; Array; TwoPointers
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
