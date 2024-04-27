package basic.sort;

/**
 * 1. 在数组中选一个基准数（通常为数组第一个）。
 * 2. 将数组中小于基准数的数据移到基准数左边，大于基准数的移到右边
 * 3. 对于基准数左、右两边的数组，不断重复以上两个过程，直到每个子集只有一个元素，即为全部有序。
 * <p>
 * 时间复杂度: O(nlogn)。 当选择的中心元素是最大或最小的元素时，时间复杂度为O(n^2)。
 * <p>
 * 空间复杂度: O(logn)。(每次递归就要保持一些数据，递归深度为logn)
 * 最优的情况下空间复杂度为：O(logn) ；每一次都平分数组的情况；
 * 最差的情况下空间复杂度为：O( n )；退化为冒泡排序的情况
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = { 3, 2, 1, 5, 6, 4 };
        int start = 0;
        int end = arr.length - 1;
        // quickSort(arr, start, end);
        // quickSort1(arr, start, end);
        quickSort2(arr, start, end);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // 以最后一个元素为基准数，将小于基准数的元素放在左边，大于基准数的元素放在右边
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        // i标识小于参照数的区域末尾位置
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 另一种写法。
     * 若以第一个元素为基准数（就如上面的示例），在哨兵互走过程需右边的哨兵先走。
     */
    public static void quickSort1(int[] arr, int low, int high) {
        // low,high 为每次处理数组时的首、尾元素索引

        // 当low==high是表示该序列只有一个元素，不必排序了
        if (low >= high) {
            return;
        }
        // 选出哨兵元素和基准元素。这里左边的哨兵元素也是基准元素
        int i = low, j = high, base = arr[low];
        while (i < j) {
            // 右边哨兵从后向前找
            while (arr[j] >= base && i < j) {
                j--;
            }
            // 左边哨兵从前向后找
            while (arr[i] <= base && i < j) {
                i++;
            }
            swap(arr, i, j); // 交换元素
        }
        swap(arr, low, j); // 基准元素与右哨兵交换

        // 递归调用，排序左子集合和右子集合
        quickSort1(arr, low, j - 1);
        quickSort1(arr, j + 1, high);

    }

    // 另一种写法，最简便
    public static void quickSort2(int nums[], int start, int end) {
        // 数组有多个元素进行排序
        if (start < end) {
            int base = nums[start];// 以要进行排序数组第0个元素为base
            int left = start;// 左指针
            int right = end;// 右指针
            while (left < right) {
                // 从右向左找，比base大，right--
                while (left < right && nums[right] >= base) {
                    right--;
                }
                // 比base小，替换left所在位置的数字
                nums[left] = nums[right];
                // 从左向右找，比base小，left++
                while (left < right && nums[left] <= base) {
                    left++;
                }
                // 比base大，替换right所在位置的数字
                nums[right] = nums[left];
            }
            nums[left] = base;// 此时left=right，用base替换这个位置的数字
            // 排列比base小的数字的数组
            quickSort2(nums, start, left - 1);
            // 排列比base大的数字的数组
            quickSort2(nums, left + 1, end);
        }
    }

}
