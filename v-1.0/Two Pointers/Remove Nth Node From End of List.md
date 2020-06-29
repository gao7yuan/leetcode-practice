# 19. Remove Nth Node From End of List
*Medium*
10/22/18

Given a linked list, remove the n-th node from the end of list and return its head.

Example:
```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

## Attempts
* fast向前推进n次，之后同时推进slow和fast直到fast到达null
```
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
    ListNode slow = head;
    ListNode prev = head;
    for (int i = 0; i < n; i++) {
        if (fast == null) {
            return null;
        }
        fast = fast.next;
    }
    while (fast != null) {
        prev = slow;
        fast = fast.next;
        slow = slow.next;
    }
    if (slow == head) {
        return head.next;
    }
    prev.next = slow.next;
    return head;
}
```

## Solutions
### 1. two pass
* 先找长度，然后找length - n
  - O(2n) = O(n) time, O(1) space
  - 注意dummy node的运用 避免讨论是否delete head的情况
```
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int length  = 0;
    ListNode first = head;
    while (first != null) {
        length++;
        first = first.next;
    }
    length -= n;
    first = dummy;
    while (length > 0) {
        length--;
        first = first.next;
    }
    first.next = first.next.next;
    return dummy.next;
}
```
### 2. one pass
* 类似我的方法
```
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n + 1; i++) {
        first = first.next;
    }
    // Move first to the end, maintaining the gap
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    second.next = second.next.next;
    return dummy.next;
}
```
