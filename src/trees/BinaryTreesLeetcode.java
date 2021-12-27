package trees;

import com.sun.source.tree.Tree;
import javafx.util.Pair;

import java.util.*;

public class BinaryTreesLeetcode {
    public static void main(String[] args) {

        int[][] a = {{10,16},{2,8},{1,6},{7,12}};
        findMinArrowShots(a);


    }

    public static boolean isLeafNode(TreeNode node){
        return node.left == null && node.right == null;
    }

    public static void printLeft(TreeNode node, List<Integer> res){
        TreeNode curr = node.left;
        while (curr != null){
            if(isLeafNode(curr) == false) res.add(curr.data);
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;

        }
    }

    public static void printRight(TreeNode node, List<Integer> res){
        TreeNode curr = node.right;
        List<Integer> temp = new ArrayList<>();
        while (curr!= null){
            if(isLeafNode(curr) == false) temp.add(curr.data);
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }

        for (int i = temp.size() -1; i >= 0 ; i--){
            res.add(temp.get(i));
        }
    }

    public static void printLeaves(TreeNode node , List<Integer> res){
        if (isLeafNode(node)){
            res.add(node.data);
            return;
        }
        if(node.left!= null) printLeaves(node.left, res);
        if(node.right!= null) printLeaves(node.right, res);
    }

    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points,(a,b)->Integer.compare(a[1],b[1]));
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }



//        int end = list.get(0)[1];
//        int arrow = 1;
//        for (int i = 0; i < list.size() ; i++){
//            int first = list.get(i)[0];
//            if(first <= end){
//
//            }else{
//                end = list.get(i)[1];
//                arrow++;
//            }
//        }
//        return arrow;





    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String result = t.data + "";
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if (left == "" && right == "") return result;
        if (left == "") return result + "()" + "(" + right + ")";
        if (right == "") return result + "(" + left + ")";
        return result + "(" + left + ")" + "(" + right + ")";

    }

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 0 || d == 1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left = d == 1 ? root : null;
            newNode.right = d == 0 ? root : null;
            return newNode;
        }

        if (d >= 2 && root != null) {
            root.left = addOneRow(root.left, v, d > 2 ? d - 1 : 1);
            root.right = addOneRow(root.right, v, d > 2 ? d - 1 : 0);

        }
        return root;
    }

    int xDepth = 0;
    int yDepth = 0;

    TreeNode xParent = null;
    TreeNode yParent = null;

    public boolean isCousins(TreeNode root, int x, int y) {
        findDepthAndParent(root, 0, x, y, null);
        return xDepth == yDepth && xParent != yParent ? true : false;

    }

    public void findDepthAndParent(TreeNode root, int depth, int x, int y, TreeNode parent) {
        if (root == null) return;
        if (root.data == x) {
            xDepth = depth;
            xParent = parent;
        } else if (root.data == y) {
            yDepth = depth;
            yParent = parent;
        }

        findDepthAndParent(root.left, depth + 1, x, y, root);
        findDepthAndParent(root.right, depth + 1, x, y, root);
    }

    public int goodNodes(TreeNode root) {
        return check(root, -100000);
    }

    private int check(TreeNode root, int max) {
        if (root == null) return 0;
        int res = root.data >= max ? 1 : 0;

        res += check(root.left, Math.max(max, root.data));
        res += check(root.right, Math.max(max, root.data));

        return res;
    }


}

class RangeFreqQuery {

    int[] res;

    public RangeFreqQuery(int[] arr) {
        this.res = Arrays.copyOf(arr, arr.length);

    }

    public int query(int left, int right, int value) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (res[i] == value) count++;
        }
        return count;

    }
}


