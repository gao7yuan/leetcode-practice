# 21. Merge Two Sorted Lists
*Easy*
08/30/18

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

## Attempts
* Create a new list
```
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    ListNode list = new ListNode(0);
    ListNode node = list;
    ListNode node1 = l1;
    ListNode node2 = l2;
    while (node1 != null && node2 != null) {
        if (node1.val <= node2.val) {
            node.val = node1.val;
            node1 = node1.next;
        } else {
            node.val = node2.val;
            node2 = node2.next;
        }
        node.next = new ListNode(0);
        node = node.next;
    }
    node.val = (node1 == null) ? node2.val : node1.val;
    node.next = (node1 == null) ? node2.next : node1.next;
    return list;
}
```

## Solutions
* Recursion
```
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
```
* Iteration
  - Similar as my method.
  - Use a dummy head.
```
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
        return null;
    }
    if (l1 == null) {
        return l2;
    }
    if (l2 == null) {
        return l1;
    }
    ListNode result = new ListNode(0);
    ListNode prev = result;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            prev.next = l1;
            l1 = l1.next;
        } else {
            prev.next = l2;
            l2 = l2.next;
        }
        prev = prev.next;
    }
    if (l1 != null) {
        prev.next = l1;
    }
    if (l2 != null) {
        prev.next = l2;
    }
    return result.next;
}
```    
