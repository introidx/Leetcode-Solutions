package leetcode.Deepest_Leaves_Sum_1302;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * problem Link : https://leetcode.com/problems/deepest-leaves-sum/
 */

public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        int level_sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            level_sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                level_sum += temp.val;
                if (temp.left != null) q.offer(temp.left);
                if (temp.right != null) q.offer(temp.right);
            }
        }

        return level_sum;
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
