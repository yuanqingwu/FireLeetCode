import java.util.Arrays;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m
 * 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * 
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming
 * from nums1.
 * 
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * 
 * Example 3:
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there
 * to ensure the merge result can fit in nums1.
 * 
 * 
 * 提示：
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -10^9 <= nums1[i], nums2[j] <= 109
 * 
 * 
 * 解题思路：逆向双指针
 */
public class _88MergeSortedArray extends BaseSolution {

    public static void main(String[] args) {

        _88MergeSortedArray mergeSortedArray = new _88MergeSortedArray();
        mergeSortedArray.run();
    }

    @Override
    void solution() {

        // nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int m = 3;
        int[] nums2 = new int[] { 2, 5, 6 };
        int n = 3;
        merge(nums1, m, nums2, n);

        println(" res:" + Arrays.toString(nums1));

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0)
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        while (n >= 0)
            nums1[p--] = nums2[n--];
    }

}