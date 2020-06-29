# 445. Add Two Numbers II
12/21/18
*Medium*

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
```
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
```

## Attempts
* Stack
  - want to add two numbers in reverse order of singly-linked list, think of stack data structure
  - add each digit together and construct the list
  - O(n) time, O(n) space
```Java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    ListNode cur = l1;
    while (cur != null) {
        stack1.push(cur.val);
        cur = cur.next;
    }
    cur = l2;
    while (cur != null) {
        stack2.push(cur.val);
        cur = cur.next;
    }

    int carry = 0;

    ListNode list = null;
    while (!stack1.isEmpty() || !stack2.isEmpty()) {
        int num1 = stack1.isEmpty() ? 0 : stack1.pop();
        int num2 = stack2.isEmpty() ? 0 : stack2.pop();
        int num = (num1 + num2 + carry) % 10 ;
        carry = (num1 + num2 + carry) / 10;
        cur = new ListNode(num);
        cur.next = list;
        list = cur;
    }
    if (carry != 0) {
        cur = new ListNode(carry);
        cur.next = list;
        list = cur;
    }
    return list;
}
```
* Use offset
  - store sum in each node, if greater than 10 handle it.
  - O(n) time, O(n) space (recursion)
  - have to use recursion
```Java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int size1 = size(l1);
    int size2 = size(l2);
    ListNode res = new ListNode(1);
    res.next = size1 < size2 ? helper(l2, l1, size2 - size1) : helper(l1, l2, size1 - size2);
    if (res.next != null && res.next.val > 9) {
        res.next.val %= 10;
        return res;
    }
    return res.next;
}

private ListNode helper(ListNode l1, ListNode l2, int offset) {
    if (l1 == null) {
        return null;
    }
    ListNode result = offset == 0 ? new ListNode(l1.val + l2.val) : new ListNode(l1.val);
    ListNode post = offset == 0 ? helper(l1.next, l2.next, 0) : helper(l1.next, l2, offset - 1);
    if (post != null && post.val > 9) {
        result.val += 1;
        post.val = post.val % 10;
    }
    result.next = post;
    return result;
}

private int size(ListNode node) {
    int size = 0;
    while (node != null) {
        size++;
        node = node.next;
    }
    return size;
}
```
* Reverse and add
