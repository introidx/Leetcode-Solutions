package leetcode.All_Elements_in_Two_Binary_Search_Trees;

import java.util.*;


/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 * <p>
 * problem Link : https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class Bst {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();

        inorder(root1, list1);
        inorder(root2, list1);

        Collections.sort(list1);
        return list1;

    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
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
