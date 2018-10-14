# 142. Linked List Cycle II
*Medium*
10/18/18

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

## Attempts
* Floyd's Tortoise and Hare
```
public ListNode detectCycle(ListNode head) {
    if (head == null) return null;
    ListNode slow = head;
    ListNode fast = head;
    ListNode meet = null;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            meet = slow;
            break;
        }
    }
    if (meet == null) return null;
    ListNode start = head;
    while (start != meet) {
        start = start.next;
        meet = meet.next;
    }
    return start;
}
```
