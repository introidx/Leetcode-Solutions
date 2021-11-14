package linked_lists;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Linked {
    public ListNode insert(ListNode node, int val) {
        if (node == null) {
            ListNode newNode = new ListNode(val);
            newNode.next = null;
            return newNode;
        } else {
            node.next = insert(node.next, val);
        }
        return node;
    }

    public ListNode insertAtTheBeginning(ListNode node, int val) {
        ListNode firstNode = new ListNode(val);
        firstNode.next = node;
        printNodes(firstNode);
        return firstNode;
    }

    public ListNode insertAtThePosition(ListNode node, int val, int position) {
        if (node == null && position > 1) return null;
        if (node == null && position == 1) {
            ListNode newNode = new ListNode(val);
            newNode.next = null;
            return newNode;
        }
        if (position == 1) {
            ListNode newNode = new ListNode(val);
            newNode.next = node;
            return newNode;
        }

        node.next = insertAtThePosition(node.next, val, position - 1);
        return node;
    }

    public ListNode deleteNodeAtPosition(ListNode node, int position) {
        if (position < 0) return node;
        if (node == null && position > 0) return null;
        if (position == 1) return node.next;
        node.next = deleteNodeAtPosition(node.next, position - 1);
        return node;
    }

    public ListNode deleteNodeAtEnd(ListNode node) {
        ListNode temp = node;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        printNodes(node);
        return node;
    }

    public void printNodes(ListNode node) {
        String s = "";
        while (node != null) {
            if(node.next == null){
                s += node.val;
            }else{
                s += node.val + " -> ";
            }

            node = node.next;
        }


        System.out.println(s);
    }

    public boolean searchNode(ListNode node, int key) {
        if (node == null) return false;
        while (node != null) {
            if (node.val == key) {
                System.out.println("Yes " + key + " is Present");
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        int count = 0;
        ListNode curr = head;

        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            curr = reverseKGroup2(curr, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            head = curr;
        }
        return head;
    }


    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode cur = head.next;
        ListNode next = cur.next;
        int i = 0, first = -1, last = -1, mn = Integer.MAX_VALUE;
        while (next != null) {
            if ((cur.val > prev.val && cur.val > next.val) || (cur.val < prev.val && cur.val < next.val)) {
                if (last != -1)
                    mn = Math.min(mn, i - last);
                else
                    first = i;
                last = i;
            }
            prev = prev.next;
            cur = cur.next;
            next = next.next;
            i++;
        }
        if (first == last)
            return new int[]{-1, -1};
        return new int[]{mn, last - first};
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

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val < head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
        }
        return deleteDuplicates(head.next);

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p1 = head;
        ListNode p2 = head; // speed double of p1
        ListNode pre = head;

        while (p2 != null && p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        pre.next = null;

        ListNode h1 = sortList(head);
        ListNode h2 = sortList(p1);

        return merge(h1, h2);
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.val < h2.val) {
            h1.next = merge(h1.next, h2);
            return h1;
        } else {
            h2.next = merge(h1, h2.next);
            return h2;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            curr = reverseKGroup(curr, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            head = curr;
        }

        return head;
    }




    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            count++;
            curr = curr.next;
        }

        if (count == k) {
            curr = reverseKGroup1(curr, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = curr;
                curr = head;
                head = temp;
            }
            head = curr;

        }
        return head;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = result;

        int carry =0;
        while (l1 != null || l2 != null || carry != 0){
            if(l1 != null){
                carry += l1.val;
                l1= l1.next;
            }
            if(l2 != null){
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry % 10);
            carry = carry / 10;
            p = p.next;
        }
        return result.next;
    }

    public static ListNode deleteK(ListNode node, int k) {
        ListNode dummy = node;
        int sum = 0;
        while (dummy != null) {
            sum += dummy.val;
            dummy = dummy.next;
        }

        while (sum > k && node != null) {
            sum -= node.val;
            node = node.next;
        }
        return node;
    }


    public static ListNode moveKNodesStartingFromNToFront(ListNode head, int n, int k) {
       // Input: head = [1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 ] , n = 3 , k = 4
       // output: 4 -> 5 -> 6 -> 7 -> 1 -> 2 -> 3 -> 8 -> 9

        ListNode dummy = head;
        ListNode dummy2 = dummy; // 1->2->3

        int countN = 1;
        while (countN < n) {
            countN++;
            dummy = dummy.next;
        }

        head = dummy.next;
        ListNode firstHead = head; // 4 -> 5 -> 6 -> 7
        dummy.next = null; // separate the first part

        int countK = 1;
        while (countK < k) {
            firstHead = firstHead.next;
            countK++;
        }

        ListNode lastHead = firstHead.next;
        firstHead.next = dummy2; // this gives 4567... 123

        while (firstHead.next != null){
            firstHead = firstHead.next;
        }

        firstHead.next =lastHead;
        return head;
    }


}


class LinkedListPractice {


    public static void main(String[] args) {
        ListNode root = new ListNode();
        Linked linkedList = new Linked();
        linkedList.insert(root, 1);
        linkedList.insert(root, 2);
        linkedList.insert(root, 3);
        linkedList.insert(root, 4);
        linkedList.insert(root, 5);
        linkedList.insert(root, 6);
        linkedList.insert(root, 7);
        linkedList.insert(root, 8);
        linkedList.insert(root, 9);

        linkedList.printNodes(root.next);
        ListNode result = Linked.moveKNodesStartingFromNToFront(root.next, 3,4);
        linkedList.printNodes(result);


        // new Node
        /*ListNode l1 = new ListNode(2);
        Linked obj1 = new Linked();
        obj1.insert(l1, 4);
        obj1.insert(l1, 3);

        obj1.printNodes(l1);

        ListNode l2 = new ListNode(5);
        Linked obj2 = new Linked();
        obj2.insert(l2, 6);
        obj2.insert(l2, 4);

        obj1.printNodes(l2);

        ListNode result = Linked.addTwoNumbers1(l1,l2);
        obj1.printNodes(result);*/

     /*   ListNode l1 = new ListNode(8);
        Linked obj1 = new Linked();
        obj1.insert(l1, 7);
        obj1.insert(l1, 6);
        obj1.insert(l1, 5);
        obj1.insert(l1, 4);
        obj1.insert(l1, 3);
        obj1.insert(l1, 2);

        obj1.printNodes(l1);
        ListNode result = Linked.deleteK(l1, 14);
        obj1.printNodes(result);
*/
    }


}
