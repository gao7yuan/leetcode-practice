# 141. Linked List Cycle
Easy
08/29/18

Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

## Solutions
### Approach 1: Hash Table
* O(n) time, O(n) space.
```
public boolean hasCycle(ListNode head) {
    Set<ListNode> nodeSet = new HashSet<>();
    ListNode cur = head;
    while (cur != null) {
        if (nodeSet.contains(cur)) return true;
        nodeSet.add(cur);
        cur = cur.next;
    }
    return false;
}
```
### Approach 2: Two Pointers
* A slow runner and a fast runner. Slow runner increment 1 while fast runner increment 2 each time. If Slow == Fast return true...
* O(n) time, O(1) space.
* Time complexity analysis:
  - List has no cycle: the fast pointer reaches the end - O(n)
  - List has a cycle:
    - Break it down into non-cyclic part and cyclic part
    - Slow pointer takes "non-cyclic length" steps to enter the cycle. N
    - It takes ```distance between the 2 runners / difference of speed``` loops for the fast runner to catch up with the slow runner. Distance is at most "cyclic length" K and speed difference is 1. So K.
    - O(N + K) = O(n) time.
```
public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
        if (slow == fast) return true;
        slow = slow.next;
        fast = fast.next.next;
    }
    return false;
}
```
