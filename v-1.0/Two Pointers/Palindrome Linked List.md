# 234. Palindrome Linked List
*Easy*
08/29/18

Given a singly linked list, determine if it is a palindrome.

Example 1:
```
Input: 1->2
Output: false
```
Example 2:
```
Input: 1->2->2->1
Output: true
```
Follow up:
Could you do it in O(n) time and O(1) space?

## Attempts
* Use an array to record the first half of the list
  - O(n) time and space.
```
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;
    int size = 0;
    ListNode cur = head;
    while (cur != null) {
        size++;
        cur = cur.next;
    }
    cur = head;
    ListNode[] halfList = new ListNode[size / 2];
    for (int i = 0; i < size / 2; i++) {
        halfList[i] = cur;
        cur = cur.next;
    }
    if (size % 2 != 0) cur = cur.next;
    for (int i = size / 2 - 1; i >= 0; i--) {
        if (cur.val != halfList[i].val) return false;
        cur = cur.next;
    }
    return true;
}
```
* Two pointers (需要自己implement一下)
  - fast runner moves 2 times faster than slow runner.
  - when fast runner reaches end, slow runner is at mid point.
  - reverse the list from mid point.
  - compare the reversed list with the original one until the mid point.
  - To reverse a singly-linked list:
```
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```
  - method:
```
public boolean isPalindrome(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    if (fast != null) { // odd nodes: let right half smaller
        slow = slow.next;
    }
    slow = reverse(slow);
    fast = head;

    while (slow != null) {
        if (fast.val != slow.val) {
            return false;
        }
        fast = fast.next;
        slow = slow.next;
    }
    return true;
}
```
