package cases;

import data.TreeNode;
import tag.BFS_BreadthFirstSearch;
import tag.BinaryTree;
import tag.DFS_DepthFirstSearch;
import tag.Recursion;
import tag.Tree;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 
 * Example:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * 
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * 
 * 解题思路：递归交换左右子树。
 */
@Tree
@DFS_DepthFirstSearch
@BFS_BreadthFirstSearch
@BinaryTree
@Recursion
public class _226_InvertTree extends BaseSolution {

    public static void main(String args[]) {
        _226_InvertTree tree = new _226_InvertTree();
        tree.run();
    }

    @Override
    void solution() {
        // 3,9,20,null,null,15,7
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode res = invertTree(root);
        println(invertTree(res).toString());

        // 2,1,3
        TreeNode root1 = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        TreeNode res1 = invertTree(root1);
        println(res1.toString());
    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
