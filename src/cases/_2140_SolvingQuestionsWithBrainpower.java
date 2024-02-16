package cases;

import static org.junit.Assert.assertEquals;

import tag.ArrayTag;
import tag.DynamicProgramming;

/**
 * You are given a 0-indexed 2D integer array questions where questions[i] =
 * [pointsi, brainpoweri].
 * 
 * The array describes the questions of an exam, where you have to process the
 * questions in order (i.e., starting from question 0) and make a decision
 * whether to solve or skip each question. Solving question i will earn you
 * pointsi points but you will be unable to solve each of the next brainpoweri
 * questions. If you skip question i, you get to make the decision on the next
 * question.
 * 
 * - For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * - If question 0 is solved, you will earn 3 points but you will be unable to
 * solve questions 1 and 2.
 * - If instead, question 0 is skipped and question 1 is solved, you will earn 4
 * points but you will be unable to solve questions 2 and 3.
 * 
 * Return the maximum points you can earn for the exam.
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri] 。
 * 
 * 这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。解决问题 i 将让你
 * 获得 pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri
 * 个问题）。如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
 * 
 * 比方说，给你 questions = [[3, 2], [4, 3], [4, 4], [2, 5]] ：
 * 如果问题 0 被解决了， 那么你可以获得 3 分，但你不能解决问题 1 和 2 。
 * 如果你跳过问题 0 ，且解决问题 1 ，你将获得 4 分但是不能解决问题 2 和 3 。
 * 请你返回这场考试里你能获得的 最高 分数。
 * <p>
 * Example 1:
 * 
 * Input: questions = [[3,2],[4,3],[4,4],[2,5]]
 * Output: 5
 * Explanation: The maximum points can be earned by solving questions 0 and 3.
 * - Solve question 0: Earn 3 points, will be unable to solve the next 2
 * questions
 * - Unable to solve questions 1 and 2
 * - Solve question 3: Earn 2 points
 * Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more
 * points.
 * 
 * <p>
 * 
 * Example 2:
 * 
 * Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: 7
 * Explanation: The maximum points can be earned by solving questions 1 and 4.
 * - Skip question 0
 * - Solve question 1: Earn 2 points, will be unable to solve the next 2
 * questions
 * - Unable to solve questions 2 and 3
 * - Solve question 4: Earn 5 points
 * Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more
 * points.
 * 
 * <p>
 * 
 * Constraints:
 * 
 * 1 <= questions.length <= 105
 * questions[i].length == 2
 * 1 <= pointsi, brainpoweri <= 105
 * 
 */
@ArrayTag
@DynamicProgramming
public class _2140_SolvingQuestionsWithBrainpower extends BaseSolution {

    public static void main(String[] args) {
        _2140_SolvingQuestionsWithBrainpower solvingQuestionsWithBrainpower = new _2140_SolvingQuestionsWithBrainpower();
        solvingQuestionsWithBrainpower.run();
    }

    @Override
    void solution() {
        long res = mostPoints(new int[][] { { 3, 2 }, { 4, 3 }, { 4, 4 }, { 2, 5 } });
        assertEquals(5, res);

        long res1 = mostPoints(new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 } });
        assertEquals(7, res1);

        long res2 = mostPoints(new int[][] { { 100, 5 }, { 1, 1 }, { 1, 2 }, { 1, 1 } });
        assertEquals(100, res2);
    }

    /**
     * 填表法适用于大多数 DP：通过当前状态所依赖的状态，来计算当前状态。
     * 
     * 设有 n 个问题，定义 dp[i] 表示解决区间 [i,n−1] 内的问题可以获得的最高分数。
     * 倒序遍历问题列表，对于第 i 个问题，我们有两种决策：跳过或解决。
     * 
     * 若跳过，则有 dp[i]=dp[i+1]。
     * 
     * 若解决，则需要跳过后续 brainpower[i] 个问题。
     * 记 j=i+brainpower[i]+1 ，则有:
     * j < n: dp[i]=points[i]+dp[j]
     * j >= n: dp[i]=points[i]
     * 
     * 取两种情况最大值，最后答案为dp[0]。
     */
    @DynamicProgramming(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public long mostPoints(int[][] questions) {

        int len = questions.length;
        long[] dp = new long[len];
        dp[len - 1] = questions[len - 1][0];

        for (int i = len - 2; i >= 0; i--) {
            if (i + questions[i][1] + 1 < len) {
                dp[i] = dp[i] + dp[i + questions[i][1] + 1];
            }
            int j = i + questions[i][1] + 1;
            long yes = j < len ? dp[j] + questions[i][0] : questions[i][0];
            long no = dp[i + 1];
            dp[i] = Math.max(yes, no);
            // System.out.println(Arrays.toString(dp));
        }
        return dp[0];

        // 以下最易想到的解法错误，原因为：
        // questions = [[100,5],[1,1],[1,2],[1,1]]的情况下计算逻辑为：
        // dp[0] = points[0] = 100
        // dp[1] = dp[0] = 100
        // dp[2] = dp[1] = 100
        // dp[3] = dp[1] + points[3] = 101 (因为dp[1]的brainpower[i]为1，与答第2题冲突，但与答第3题不冲突)
        // 问题出在dp[1]上。因为dp[1]是由dp[0]转移而来，隐式的选择了dp[0]，因此其实际的"脑力恢复期"为max(0 +
        // brainpower[0], 1 + brainbrainpower[1]) = 5，而非1 + brainbrainpower[1] =
        // 2。因为当遍历到第j个元素时，某个小于j的已选元素k的"脑力恢复期"可能仍未结束，因此不能只考虑选择j所造成的"脑力恢复期"。

        // dp[0] = questions[0][0];
        // dp[1] = Math.max(questions[0][0], questions[1][0]);
        // for (int i = 2; i < len; i++) {
        // dp[i] = questions[i][0];
        // for (int j = 0; j < i; j++) {
        // if (j + questions[j][1] < i) {
        // dp[i] = Math.max(dp[j] + questions[i][0], dp[i - 1]);
        // }
        // }
        // dp[i] = Math.max(dp[i], dp[i - 1]);
        // }

        // System.out.println(Arrays.toString(dp));
        // return dp[len - 1];
    }
}
