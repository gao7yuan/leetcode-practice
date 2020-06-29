# 147. Insertion Sort List
*Medium*
09/29/18

Sort a linked list using insertion sort.

## Attempts
  * Insertion sort.
```
public ListNode insertionSortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode node = head.next; // the node to look at
    // prevNode: the node before the next node to look at (sorted)
    // nextNode: the next node to look at
    ListNode nextNode, prevNode = head;
    ListNode cur, prev; // for comparison
    while (node != null) {
        cur = dummy.next;
        prev = dummy;
        while (cur != node && node.val > cur.val) {
            cur = cur.next;
            prev = prev.next;
        }
        // if node is smaller than the last node, insert
        if (cur != node) {
            prev.next = node;
            nextNode = node.next; // get the next node to look at
            node.next = cur;
            node = nextNode;
            prevNode.next = node; // the last sorted node point to the first unsorted node
        } else {
            node = node.next;
            prevNode = prevNode.next;
        }
    }
    return dummy.next;
}
```
