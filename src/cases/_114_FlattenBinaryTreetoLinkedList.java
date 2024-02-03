package cases;

import static org.junit.Assert.assertEquals;

import basic.tree.TreeNode;
import tag.BinaryTree;
import tag.DFS_DepthFirstSearch;
import tag.Tree;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * 
 * The "linked list" should use the same TreeNode class where the right child
 * pointer points to the next node in the list and the left child pointer is
 * always null.
 * The "linked list" should be in the same order as a pre-order traversal of the
 * binary tree.
 * <p>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: root = [0]
 * Output: [0]
 * 
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * 
 */
@Tree
@BinaryTree
@DFS_DepthFirstSearch
public class _114_FlattenBinaryTreetoLinkedList extends BaseSolution {

    public static void main(String[] args) {
        _114_FlattenBinaryTreetoLinkedList solution = new _114_FlattenBinaryTreetoLinkedList();
        solution.run();
    }

    @Override
    void solution() {
        TreeNode root = TreeNode.gen(new int[] { 1, 2, 5, 3, 4, 6 }, 0);
        flatten(root);
        assertEquals("[1,null,[2,null,[3,null,[4,null,[5,null,[6,null,null]]]]]]", root.toString());
    }

    @DFS_DepthFirstSearch(timeComplexity = "O(n)", spaceComplexity = "O(n)")
    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        // 将左子树变为链表，递归逻辑
        flatten(root.left);
        // 将右子树变为链表，递归逻辑
        flatten(root.right);

        // 下面的将树变为链表的逻辑利用三个节点的最普遍简单二叉树来思考即可。

        // 暂存右子树
        TreeNode tmp = root.right;
        // 将左子树移动到右子树
        root.right = root.left;
        // 同时左子树置空
        root.left = null;
        // 处理完左子树之后找到此时树的最右节点
        while (root.right != null) {
            root = root.right;
        }
        // 将之前暂存的右子树连接上去
        root.right = tmp;
    }
}
