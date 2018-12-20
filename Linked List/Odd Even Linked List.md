# 328. Odd Even Linked List
12/20/18
*Medium*

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
```
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
```
Example 2:
```
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
```
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...

## Attempts
* O(n) time, O(1) space
```Java
public ListNode oddEvenList(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode node = head;
    ListNode oddDum = new ListNode(0);
    ListNode evenDum = new ListNode(0);
    ListNode oddCur = oddDum;
    ListNode evenCur = evenDum;
    int num = 1;

    while (node != null) {
        if (num % 2 == 1) {
            oddCur.next = node;
            oddCur = oddCur.next;
        } else {
            evenCur.next = node;
            evenCur = evenCur.next;
        }
        node = node.next;
        num++;
    }
    evenCur.next = null;
    oddCur.next = evenDum.next;
    return oddDum.next;
}
```

### Solutions
* more "in place" code
```Java
public ListNode oddEvenList(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;
    while (even != null && even.next != null) {
        odd.next = even.next;
        odd = odd.next;
        even.next = odd.next;
        even = even.next;
    }
    odd.next = evenHead;
    return head;
}
```
