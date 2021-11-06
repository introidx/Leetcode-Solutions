package trees;

import com.sun.source.tree.Tree;

import java.util.*;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int data) {
        this.data = data;
    }
}

class BST {
    // insert into binary tree
    public TreeNode insert(TreeNode treeNode, int val) {
        if (treeNode == null) {
            // if node is null create a new node and assign left and right
            // node to null
            return createNewNode(val);
        }

        if (val < treeNode.data) {
            treeNode.left = insert(treeNode.left, val);
        } else if ((val > treeNode.data)) {
            treeNode.right = insert(treeNode.right, val);
        }

        return treeNode;
    }

    public TreeNode createNewNode(int val) {
        TreeNode treeNode = new TreeNode();
        treeNode.data = val;
        treeNode.left = null;
        treeNode.right = null;

        return treeNode;
    }

    // delete node
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = deleteNode(root.right, root.data);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    // In order Traversal recursive
    public void inOrderTraversalRecursive(TreeNode root) {
        if (root == null) return;

        inOrderTraversalRecursive(root.left);
        System.out.println(root.data);
        inOrderTraversalRecursive(root.right);
    }

    // In order Traversal Iterative
    public void inOrderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            list.add(root.data);
            System.out.println(root.data);
            root = root.right;
        }
        // return list if want to
    }

    // preOrder traversal recursive
    public void preOrderRecursive(TreeNode root) {
        if (root == null) return;

        System.out.println(root.data);
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    // preOrder traversal Iterative
    public void preOrderIterative(TreeNode root) {
        if (root == null) return;
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.println(temp.data);
            list.add(temp.data);

            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }

        }
        //return list if want to
    }

    // post order Recursive
    public void postOrderRecursive(TreeNode root) {
        if (root == null) return;

        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.println(root.data);
    }

    // post order Iterative
    public void postOrderIterative(TreeNode root) {
        if (root == null) return;
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(0, temp.data);

            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }
        // return list
    }

    // level Order traversal Iterative
    public void levelOrder(TreeNode root) {
        if (root == null) return;

        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> oneLevel = new ArrayList<>();

            while (size > 0) {
                TreeNode temp = queue.poll();
                oneLevel.add(temp.data);

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                size--;
            }

            list.add(oneLevel);
        }
        // return list
    }

    // search node in tree node
    public boolean searchNode(TreeNode node, int val) {
        if (node == null) {
            return false;
        }

        boolean isPresent = false;

        while (node != null) {
            if (val < node.data) {
                node = node.left;
            } else if (val > node.data) {
                node = node.right;
            } else {
                isPresent = true;
                break;
            }
        }

        return isPresent;
    }

    // print alternate level nodes in binary tree
    public static void printAlternateLevelNode(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue1 = new LinkedList<>(); // even level nodes
        Queue<TreeNode> queue2 = new LinkedList<>(); // odd level nodes

        queue1.add(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                TreeNode temp = queue1.poll();
                System.out.println(temp.data);
                if (temp.left != null) {
                    queue2.add(temp.left);
                }
                if (temp.right != null) {
                    queue2.add(temp.right);
                }
            }
            while (!queue2.isEmpty()) {
                TreeNode temp = queue2.poll();
                if (temp.left != null) {
                    queue1.add(temp.left);
                }
                if (temp.right != null) {
                    queue1.add(temp.right);
                }

            }
        }
    }

    public static TreeNode sortedArrayToTree(int[] nums) {
        if (nums == null) return null;
        int n = nums.length - 1;
        return helper(nums, 0, n);
    }

    public static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) return null;
        int midPoint = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[midPoint]);
        node.left = helper(nums, left, midPoint - 1);
        node.right = helper(nums, midPoint + 1, right);
        return node;
    }

    public static void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            if (!stack.isEmpty()) {
                current.right = stack.peek();
            }
            current.left = null;
        }
    }

    private static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode node = new TreeNode(t1.data + t2.data);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    private static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.data == sum) return true;
        else
            return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data
            );
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        helper(root, targetSum, sol, res);
        return res;
    }

    private static void helper(TreeNode root, int targetSum, List<Integer> sol, List<List<Integer>> res) {
        if (root == null) return;
        if (root.left == null && root.right == null && root.data == targetSum) {
            res.add(new ArrayList<>(sol));
        } else {
            helper(root.left, targetSum - root.data, sol, res);
            helper(root.right, targetSum - root.data, sol, res);
        }
        sol.remove(sol.size() - 1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) return null;
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    private TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode node = new TreeNode(t1.data + t2.data);
        node.left = mergeTrees1(t1.left, t2.left);
        node.right = mergeTrees1(t1.right, t2.right);
        return node;
    }

    private TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.data < L) trimBST(root.right, L, R);
        if (root.data > R) trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (pre != null && root.data <= pre.data) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    TreeNode first;
    TreeNode second;
    TreeNode pre;

    public void recoverTree(TreeNode root) {
        first = null;
        second = null;
        pre = null;
        helper(root);

        int temp = first.data;
        first.data= second.data;
        second.data = temp;

    }

    public void helper(TreeNode root){
        if(root == null) return;
        helper(root.left);

        if(first == null && (pre == null || pre.data >= root.data)){
            first = pre;
        }
        if(first != null && pre.data >= root.data){
            second = root;
        }
        pre =root;
        helper(root.right);

    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right= temp;

            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }

        }
        return root;
    }


}

public class BinarySearchTree {
    public static void main(String[] args) {
        BST bst = new BST();
        TreeNode root = null;

        root = bst.insert(root, 8);
        root = bst.insert(root, 3);
        root = bst.insert(root, 6);
        root = bst.insert(root, 10);
        root = bst.insert(root, 4);
        root = bst.insert(root, 7);
        root = bst.insert(root, 1);
        root = bst.insert(root, 14);
        root = bst.insert(root, 13);

//        root = bst.deleteNode(root, 4);
//        bst.inOrderTraversalRecursive(root);
//        bst.inOrderTraversalIterative(root);
//        bst.preOrderRecursive(root);
//        bst.preOrderIterative(root);
//        System.out.println(bst.searchNode(root, 4));


    }
}
