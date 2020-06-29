# 148. Sort List
*Medium*
09/29/18

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:
```
Input: 4->2->1->3
Output: 1->2->3->4
```
Example 2:
```
Input: -1->5->3->4->0
Output: -1->0->3->4->5
```

## Attempts
* Merge sort
  - O(nlgn) time, O(lgn) space for recursion

```
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode l1 = head;
    ListNode l2 = slow.next;
    slow.next = null;
    l1 = sortList(l1);
    l2 = sortList(l2);
    return merge(l1, l2);
}
```
```
ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return l1;
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    ListNode cur1 = l1;
    ListNode cur2 = l2;
    int val1, val2;
    while (cur1 != null || cur2 != null) {
        val1 = cur1 == null ? Integer.MAX_VALUE : cur1.val;
        val2 = cur2 == null ? Integer.MAX_VALUE : cur2.val;
        cur.next = val1 < val2 ? cur1 : cur2;
        cur = cur.next;
        cur1 = val1 < val2 ? cur1.next : cur1;
        cur2 = val1 < val2 ? cur2 : cur2.next;
    }
    return dummy.next;
}
```

## Solutions
* Merge sort, bottom up - iterative
  - O(nlgn) time, O(1) space
```
public ListNode sortList(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev;
    ListNode cur = head;
    ListNode left, right;

    // get the length of the list
    int len = 0;
    while (cur != null) {
        len++;
        cur = cur.next;
    }

    // merge sublists of size 1, and then 2, and then 4, and ... len
    for (int step = 1; step < len; step <<= 1) {
        cur = dummy.next; // start at the new head
        prev = dummy;
        while (cur != null) {
            left = cur;
            right = split(left, step);
            cur = split(right, step);
            prev = merge(left, right, prev);
        }  
    }
    return dummy.next;
}
```
  - split
```
// split it so that left is the first "step" number of nodes
// return the first node in right
private ListNode split(ListNode list, int step) {
    if (list == null) return list;
    ListNode cur = list;
    for (int i = 1; i < step && list.next != null; i++) {
        list = list.next;
    }
    ListNode right = list.next;
    list.next = null;
    return right;
}
```
  - merge
```
// merge l1, l2, and attach to prev. return the last node
private ListNode merge(ListNode l1, ListNode l2, ListNode prev) {
    if (l1 == null && l2 == null) return prev;
    ListNode cur1 = l1;
    ListNode cur2 = l2;
    int val1, val2;
    ListNode cur = prev;
    while (cur1 != null || cur2 != null) {
        val1 = cur1 == null ? Integer.MAX_VALUE : cur1.val;
        val2 = cur2 == null ? Integer.MAX_VALUE : cur2.val;
        cur.next = val1 < val2 ? cur1 : cur2;
        cur = cur.next;
        cur1 = val1 < val2 ? cur1.next : cur1;
        cur2 = val1 < val2 ? cur2 : cur2.next;
    }

    return cur;
}
```
