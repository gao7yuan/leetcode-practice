# 83. Remove Duplicates from Sorted List
*Easy*
08/30/18

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
```
Input: 1->1->2
Output: 1->2
```
Example 2:
```
Input: 1->1->2->3->3
Output: 1->2->3
```

## Attempts
* Two pointers
  - O(n) time, O(1) space.
```
public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null) {
        if (slow.val != fast.val) {
            slow.next = fast;
            slow = fast;
        }
        fast = fast.next;
    }
    slow.next = null;
    return head;
}
```

## Solutions
* Straighforward approach
```
public ListNode deleteDuplicates(ListNode head) {
    ListNode current = head;
    while (current != null && current.next != null) {
        if (current.next.val == current.val) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }
    return head;
}
```
