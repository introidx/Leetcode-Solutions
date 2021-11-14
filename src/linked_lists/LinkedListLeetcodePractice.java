package linked_lists;

import java.util.PriorityQueue;

public class LinkedListLeetcodePractice {

    public static void main(String[] args) {
        // new Node
        ListNode l1 = new ListNode(1);
        Linked obj1 = new Linked();
        obj1.insert(l1, 2);
        obj1.insert(l1, 4);

        obj1.printNodes(l1);

        ListNode l2 = new ListNode(1);
        Linked obj2 = new Linked();
        obj2.insert(l2, 3);
        obj2.insert(l2, 4);

        obj1.printNodes(l2);

        ListNode result = mergeTwoListsRecursive(l1, l2);
        obj1.printNodes(result);

/*        ListNode l1 = new ListNode(8);
        Linked obj1 = new Linked();
        obj1.insert(l1, 7);
//        obj1.insert(l1, 6);
//        obj1.insert(l1, 5);
//        obj1.insert(l1, 4);
//        obj1.insert(l1, 3);
//        obj1.insert(l1, 2);

        obj1.printNodes(l1);
        ListNode result = removeNthFromEnd(l1,1);
        obj1.printNodes(result);*/

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode p = head;
        int temp;
        while (true) {
            if (p == null || p.next == null) break;

            temp = p.val;
            p.val = p.next.val;
            p.next.val = temp;

            if (p.next.next == null) break;
            p = p.next.next;
        }
        return head;
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode p1 = head, p2 = head.next, p3 = head.next.next;
        p2.next = p1;
        p1.next = p3;

        if (p3 != null) {
            p1.next = swapPairs(p3);
        }
        return p2;
    }

    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode l3 = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 != null) {
            l3.next = l1;
        }
        if (l2 != null) {
            l3.next = l2;
        }

        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1) return null;
        ListNode dummy = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = result;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry % 10);
            carry = carry / 10;
            p = p.next;
        }
        return result.next;
    }
}
