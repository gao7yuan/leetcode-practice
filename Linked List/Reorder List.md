# 143. Reorder List
12/21/18
*Medium*

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
```
Given 1->2->3->4, reorder it to 1->4->2->3.
```
Example 2:
```
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
```

## Attempts
* Find mid point, reverse second half, then construct a new list
```Java
public void reorderList(ListNode head) {
    if (head == null || head.next == null ||  head.next.next == null) {
        return;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    // find second half
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode mid = slow; // want to reverse the part after this
    ListNode start = slow.next; // start of sublist to reverse
    ListNode then = start.next; // the next node to insert as head to sublist

    // reverse second half
    while (start.next != null) {
        start.next = then.next;
        then.next = mid.next;
        mid.next = then;
        then = start.next;
    }

    // construct new list
    ListNode first = head;
    ListNode second = mid.next;

    while (first != mid) {
        mid.next = second.next;
        second.next = first.next;
        first.next = second;
        first = second.next;
        second = mid.next;
    }
}
```
