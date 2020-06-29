# 2. Add Two Numbers
*Easy*
8/2/18

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example:**
```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

## Attempts & Solution
* Singly-linked list: need an extra node to keep track of the previous node.
* Use extra (or called carry) to store 1 if two digits sums up greater than 10.
```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode cur = l1;
        int extra = 0;
        int sum;

        while (cur1 != null && cur2 != null) {
            sum = cur1.val + cur2.val + extra;
            if (sum <= 9) {
                cur1.val = sum;
                extra = 0;
            } else {
                cur1.val = sum - 10;
                extra = 1;
            }
            cur = cur1;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        while (cur1 != null) {
            sum = cur1.val + extra;
            if (sum <= 9) {
                cur1.val = sum;
                extra = 0;
            } else {
                cur1.val = sum - 10;
                extra = 1;
            }
            cur = cur1;
            cur1 = cur1.next;
        }


        while (cur2 != null) {
            sum = cur2.val + extra;
            if (sum <= 9) {
                cur.next = new ListNode(sum);
                extra = 0;
            } else {
                cur.next = new ListNode(sum - 10);
                extra = 1;
            }
            cur = cur.next;
            cur2 = cur2.next;
        }

        if (extra == 1) {
            cur.next = new ListNode(1);
        }

        return l1;
    }
```    

## Solution
* Same thoughts, but a neater way to handle l1 and l2 with different length: use x and y to represent the value in each node. If the node is null, x or y = 0.
* Can also use ```/``` and ```%``` to simplify checking whether sum <= 9 or not.
```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
```
