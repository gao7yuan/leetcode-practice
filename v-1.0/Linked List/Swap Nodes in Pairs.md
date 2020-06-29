# 24. Swap Nodes in Pairs
12/20/18
*Medium*

Given a linked list, swap every two adjacent nodes and return its head.

Example:
```
Given 1->2->3->4, you should return the list as 2->1->4->3.
```
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the list's nodes, only nodes itself may be changed.

## Attempts
```Java
public ListNode swapPairs(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode odd = head;
    ListNode even = head.next;
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    ListNode nextOdd;
    while (even != null) {
        cur.next = even;
        cur = cur.next;
        nextOdd = even.next;
        cur.next = odd;
        cur = cur.next;
        if (nextOdd == null) {
            break;
        }
        odd = nextOdd;
        even = nextOdd.next;
    }
    if (odd != null && even == null) {
        cur.next = odd;
        cur = cur.next;
    }
    cur.next = null;
    return dummy.next;
}
```

## Solutions
* in place
```Java
public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    dummy.next = head;
    while (cur.next != null && cur.next.next != null) {
        ListNode first = cur.next;
        ListNode second = cur.next.next;
        first.next = second.next;
        cur.next = second;
        second.next = first;
        cur = cur.next.next;
    }
    return dummy.next;
}
```
