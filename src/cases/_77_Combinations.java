package cases;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import tag.BackTracking;

/**
 * Given two integers n and k, return all possible combinations of k numbers
 * chosen from the range [1, n].
 * 
 * You may return the answer in any order.
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 
 * 你可以按 任何顺序 返回答案。
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * <p>
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * <p>
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to
 * be the same combination.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 * <p>
 * 
 * Constraints:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 */
@BackTracking
public class _77_Combinations extends BaseSolution {

    public static void main(String[] args) {
        _77_Combinations solution = new _77_Combinations();
        solution.run();
    }

    @Override
    void solution() {
        List<List<Integer>> res = combine(4, 2);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<Integer>(List.of(1, 2)));
        expected.add(new ArrayList<Integer>(List.of(1, 3)));
        expected.add(new ArrayList<Integer>(List.of(1, 4)));
        expected.add(new ArrayList<Integer>(List.of(2, 3)));
        expected.add(new ArrayList<Integer>(List.of(2, 4)));
        expected.add(new ArrayList<Integer>(List.of(3, 4)));
        assertArrayEquals(expected.toArray(), res.toArray());
    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    @BackTracking(timeComplexity = "O(n!)", spaceComplexity = "O(n)")
    public void backtracking(int n, int k, int start) {
        if (path.size() == k) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

}
