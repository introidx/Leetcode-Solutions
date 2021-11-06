package leetcode.Swapping_Nodes_in_a_Linked_List;

import java.util.ArrayList;
import java.util.Collections;

public class Swap {

//    Problem Link : https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

    public ListNode swapNodes(ListNode head, int k) {
        int l =0;
        ListNode slow, fast, first, second;
        slow = head;
        fast= head;
        first = head;
        second = head;

        ArrayList<Integer> e= new ArrayList<>();
        for(int i = 0; i < k-1; ++i){
            fast = fast.next;
        }

        first = fast;
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        second = slow;
        int temp1 = first.val;
        first.val = second.val;
        second.val = temp1;

        return head;

    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
