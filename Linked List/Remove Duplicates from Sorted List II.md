# 82. Remove Duplicates from Sorted List II
12/21/18
*Medium*

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:
```
Input: 1->2->3->3->4->4->5
Output: 1->2->5
```
Example 2:
```
Input: 1->1->1->2->3
Output: 2->3
```

## Attempts
* Use a `compare` node to compare with other nodes. Use a runner `cur` to iterate the list. Use `pre`.
* O(n) time, O(1) space
```Java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode compare = pre.next;
    ListNode cur = compare.next;
    int flag = 0;
    while (cur != null) {
        if (cur.val == compare.val) {
            flag = 1;
            cur = cur.next;
        } else {
            if (flag == 1) {
                pre.next = cur;
                flag = 0;
            } else {
                pre = pre.next;
            }
            compare = cur;
            cur = cur.next;
        }
    }
    if (flag == 1) {
        pre.next = null;
    }
    return dummy.next;
}
```
