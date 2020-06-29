# 206. Reverse Linked List
*Easy*
08/30/18

Reverse a singly linked list.

Example:
```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

## Attempts
* Iteration
```
public ListNode reverseList(ListNode head) {
    if (head == null) return head;
    ListNode tailHead = head;
    ListNode rest = head.next;
    tailHead.next = null;
    while (rest != null) {
        ListNode node = rest;
        rest = rest.next;
        node.next = tailHead;
        tailHead = node;
    }
    return tailHead;
}
```
* Recursion
  - 重要的一步：head.next = null.不然结束不了。。。
```
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode partial = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return partial;
}
```

## Solutions
* Iteration
  - O(n) time, O(1) space.
```
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}
```
* Recursion
  - O(n) time, O(n) space. Extra space comes from implicit stack space due to recursion.
```
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
```  
