# 92. Reverse Linked List II
12/21/18
*Medium*

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:
```
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
```

## Attempts
* one pass O(n) time
```Java
public ListNode reverseBetween(ListNode head, int m, int n) {
    // find position = m - 1
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode anchor = dummy;
    for (int i = 0; i < m - 1; i++) {
        anchor = anchor.next;
    }
    ListNode pre = anchor.next; // pre is the tail in m - n
    // reverse
    for (int i = 0; i < n - m; i++) {
        ListNode cur = pre.next; // the one to insert before current head
        ListNode first = anchor.next; // the one after anchor, the current head
        anchor.next = cur;
        pre.next = cur.next;
        cur.next = first;
    }
    return dummy.next;
}
```

## Solutions
* better code
```Java
public ListNode reverseBetween(ListNode head, int m, int n) {
    // find position = m - 1
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode anchor = dummy;
    for (int i = 0; i < m - 1; i++) {
        anchor = anchor.next;
    }
    ListNode start = anchor.next; // the start of sublist to reverse
    ListNode then = start.next; // the next node to insert as head
    for (int i = 0; i < n - m; i++) {
        start.next = then.next;
        then.next = anchor.next;
        anchor.next = then;
        then = start.next;
    }
    return dummy.next;
}
```
