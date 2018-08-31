# 160. Intersection of Two Linked Lists
*Easy*
08/30/18

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:
```
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
```
begin to intersect at node c1.


Notes:

* If the two linked lists have no intersection at all, return null.
* The linked lists must retain their original structure after the function returns.
* You may assume there are no cycles anywhere in the entire linked structure.
* Your code should preferably run in O(n) time and use only O(1) memory.

## Attempts
* Get the difference in size of two lists.
* Let the longer list iterate earlier than the shorther one.
* If two nodes meet, return true. If reaches the end, that means there's no intersection.
```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int sizeA = size(headA);
    int sizeB = size(headB);
    int diff = sizeA - sizeB;
    ListNode nodeA = headA;
    ListNode nodeB = headB;
    if (diff < 0) {
        for (int i = 0; i < (-diff); i++) {
            nodeB = nodeB.next;
        }
    } else {
        for (int i = 0; i < diff; i++) {
            nodeA = nodeA.next;
        }
    }
    while (nodeA != null) {
        if (nodeA == nodeB) return nodeA;
        nodeA = nodeA.next;
        nodeB = nodeB.next;
    }
    return null;
}
```
  - To get the size of a list:
```
private int size(ListNode list) {
    int size = 0;
    ListNode cur = list;
    while (cur != null) {
        cur = cur.next;
        size++;
    }
    return size;
}
```

## Solutions
### Approach 1: Brute Force
* For each node a in A, check if any node in B coincides with a.
* O(mn) time, O(1) space.
### Approache 2: Hash Table
* Traverse list A and store the address to each node in a hash set.
* Check every node in B.
* O(m+n) time, O(n) space.
### Approach 3: Two Pointers
* 两个list从头开始遍历，一个list到末尾之后指向另一个list的头，继续遍历，直到相遇，就是intersection
* 需要check两个list的末尾是不是同一个node，如果不是，return null.
**My implementation**
```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    ListNode node1 = headA;
    ListNode node2 = headB;
    ListNode tail1 = null;
    ListNode tail2 = null;
    while(true) {
        if (tail1 != null && tail2 != null && tail1 != tail2) return null;
        if (node1 == node2) return node1;
        if (node1.next == null) {
            tail1 = node1;
            node1 = headB;
        } else {
            node1 = node1.next;
        }
        if (node2.next == null) {
            tail2 = node2;
            node2 = headA;
        } else {
            node2 = node2.next;
        }
    }
}
```
**别人的implementation**
  - 每个list各自iterate一遍之后，将同时到达intersection或者同时到达null。如果到达null，则也达到了terminating condition，于是return a相当于return null，无需check tail是否一样。
```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;

    ListNode a = headA;
    ListNode b = headB;

    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }

    return a;
}
```
