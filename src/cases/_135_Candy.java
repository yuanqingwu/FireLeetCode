package cases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import tag.Greedy;
import tag.Tag_Array;

/**
 * [135. Candy](https://leetcode.com/problems/candy/)
 * <p>
 * There are n children standing in a line. Each child is assigned a rating
 * value given in the integer array ratings.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the
 * candies to the children.
 * <p>
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * Example 1:
 * <p>
 * Input: ratings = [1,0,2]
 * Output: 5
 * <p>
 * Explanation: You can allocate to the first, second and third child with 2, 1,
 * 2 candies respectively.
 * <p>
 * Example 2:
 * <p>
 * Input: ratings = [1,2,2]
 * Output: 4
 * <p>
 * Explanation: You can allocate to the first, second and third child with 1, 2,
 * 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * 
 * <p>
 * Constraints:
 * <p>
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
@Tag_Array
@Greedy
public class _135_Candy extends BaseSolution {

    public static void main(String[] args) {
        _135_Candy candy = new _135_Candy();
        candy.run();
    }

    @Override
    void solution() {
        assertEquals(5, candy(new int[] { 1, 0, 2 }));
        assertEquals(4, candy(new int[] { 1, 2, 2 }));
    }

    @Greedy(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] l = new int[len];
        int[] r = new int[len];
        int res = 0;

        Arrays.fill(l, 1);
        Arrays.fill(r, 1);

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                l[i] = l[i - 1] + 1;
            }
        }

        for (int j = len - 2; j >= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                r[j] = r[j + 1] + 1;
            }
        }

        for (int k = 0; k < len; k++) {
            res += l[k] > r[k] ? l[k] : r[k];
        }
        return res;
    }

}
