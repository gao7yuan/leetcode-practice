# 203. Remove Linked List Elements
*Easy*
08/30/18

Remove all elements from a linked list of integers that have value val.

Example:
```
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```

## Attempts
* Find the new head first, then iterate all the nodes.
* O(n) time, O(1) space.
```
public ListNode removeElements(ListNode head, int val) {
    ListNode newHead = head;
    while (newHead != null && newHead.val == val) {
        newHead = newHead.next;
    }
    ListNode cur = newHead;
    while (cur != null && cur.next != null) {
        if (cur.next.val == val) {
            cur.next = cur.next.next;
        } else {
            cur = cur.next;
        }
    }
    return newHead;
}
```

## Solutions
* Recursion
```
public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
}
```
* Iteration
  - Use a fake head, then iterate the following nodes.
```
public ListNode removeElements(ListNode head, int val) {
    ListNode fakeHead = new ListNode(-1);
    fakeHead.next = head;
    ListNode curr = head, prev = fakeHead;
    while (curr != null) {
        if (curr.val == val) {
            prev.next = curr.next;
        } else {
            prev = prev.next;
        }
        curr = curr.next;
    }
    return fakeHead.next;
}
```
