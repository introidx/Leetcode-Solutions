package leetcode.Sum_of_Nodes_with_Even_Valued_Grandparent;


/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 * Problem link : https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 */

public class EvenValued {
    public int sumEvenGrandparent(TreeNode root) {
        int sum =0;
        if(root == null) return 0;
        if((root.val & 1) == 0){
            if(root.left != null && root.left.left != null) sum += root.left.left.val;
            if(root.left != null && root.left.right != null) sum += root.left.right.val;
            if(root.right != null && root.right.left != null) sum += root.right.left.val;
            if(root.right != null && root.right.right != null) sum += root.right.right.val;
        }
        return sum + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }


    }
}
