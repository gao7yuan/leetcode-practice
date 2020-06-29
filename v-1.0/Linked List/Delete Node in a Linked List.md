# 237. Delete Node in a Linked List
*Easy*
08/30/18

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:
```
    4 -> 5 -> 1 -> 9
```
Example 1:
```
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list
             should become 4 -> 1 -> 9 after calling your function.
```
Example 2:
```
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list
             should become 4 -> 5 -> 9 after calling your function.
```
Note:

* The linked list will have at least two elements.
* All of the nodes' values will be unique.
* The given node will not be the tail and it will always be a valid node of the linked list.
* Do not return anything from your function.

## Attempts
* 这道题的意识是给的arguments里面的node就是要delete的node。。。呵呵
* 将这个node的值设置成下一个node的值，并且这个node的下一个node指向node.next.next。因为不是tail所以一定有node.next.next。如果是head也不要紧。
* O(1) time and space.
```
public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```
