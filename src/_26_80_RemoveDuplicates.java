/**
 * topic:remove-duplicates-from-sorted-array [删除有序数组中的重复项]
 * 
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持
 * 一致 。然后返回 nums 中唯一元素的个数。
 * 
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * - 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与
 * nums的大小不重要。
 * - 返回 k 。
 * 
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -10^4 <= nums[i] <= 10^4
 * nums 已按 非严格递增 排列
 * 
 * 
 * 解题思路：双指针
 */
public class _26_80_RemoveDuplicates extends BaseSolution {

    public static void main(String[] args) {
        _26_80_RemoveDuplicates removeDuplicates = new _26_80_RemoveDuplicates();
        removeDuplicates.run();
    }

    @Override
    void solution() {

        int[] nums1 = new int[] { 1, 1, 2, 2, 2, 2, 3, 4, 4 };
        int res1 = removeDuplicates(nums1, 1);
        println("removeDuplicates:" + res1);
        assert res1 == 4;

        int[] nums2 = new int[] { 1, 1, 2, 2, 2, 2, 3, 4, 4 };
        int res2 = removeDuplicates(nums2, 2);
        println("removeDuplicates:" + res2);
        assert res2 == 7;

    }

    public int removeDuplicates(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x)
                nums[u++] = x;
        }
        return u;
    }
}