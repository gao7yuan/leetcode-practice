# 142. Linked List Cycle II
*Medium*
08/29/18

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

## Attempts
* Hash table
  - O(n) time, O(n) space.
```
public ListNode detectCycle(ListNode head) {
    Set<ListNode> nodeSeen = new HashSet<>();
    ListNode cur = head;
    while (cur != null) {
        if (nodeSeen.contains(cur)) return cur;
        nodeSeen.add(cur);
        cur = cur.next;
    }
    return null;
}
```

## Solutions
* Hash table
  - O(n) time, O(n) space.
* Two pointers.
  - O(n) time, O(1) space.
  - Two runners - fast and slow.
  - From start node to start of cycle: A
  - From start of cycle to first meeting point: B
  - Size of cycle: N
  - N = fast-slow = 2(A+B)-(A+B) = A+B
  - So if use a runner starting from start node, a runner starting from the meeting point, each has to travel A to the beginning of cycle.
  - **Note**: the starting node is important in this question. Slow and fast runners both have to start at head, otherwise they meet at A + B + 1, and the following operation will never end...
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
