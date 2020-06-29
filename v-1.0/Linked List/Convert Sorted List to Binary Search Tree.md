# 109. Convert Sorted List to Binary Search Tree
12/21/18
*Medium*

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
```
Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
```

## Attempts
* Recursion and convert to array
  - O(n) time, O(n) space (array)
* convert to array - O(n) time, O(n) space
```Java
public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
        return null;
    }
    int n = size(head);
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
        arr[i] = head.val;
        head = head.next;
    }
    return arrToBST(arr, 0, n - 1);
}
```
* use recursion - O(lgn) time, O(lgn) space
```Java
private TreeNode arrToBST(int[] arr, int start, int end) {
    if (start == end) {
        return new TreeNode(arr[start]);
    }
    TreeNode root;
    if (start + 1 == end) {
        root = new TreeNode(arr[end]);
        root.left = new TreeNode(arr[start]);
        return root;
    }
    int mid = (start + end) / 2;
    root = new TreeNode(arr[mid]);
    root.left = arrToBST(arr, start, mid - 1);
    root.right = arrToBST(arr, mid + 1, end);
    return root;
}
```
* get size - O(n) time
```Java
private int size(ListNode list) {
    int count = 0;
    ListNode cur = list;
    while (cur != null) {
        count++;
        cur = cur.next;
    }
    return count;
}
```

## Solutions
### 1. Recursion
* Use two pointers to find middle node - O(n) time
* Recursively construct the BST
* O(nlgn) time, O(lgn) space from recursion
### 2. Recursion and convert to array
### 3. Simulate in-order traversal
* simulate in-order traversal to build the tree
* loop invariant: 每次build完左孩子之后，this.head指向root node。
* 用private field head追踪遍历head的每一个node
* O(n) time for size, O(lgn) for building tree, O(n) time in total. O(lgn) space for recursion stack
```Java
private ListNode head;

public TreeNode sortedListToBST(ListNode head) {
    int size = size(head);
    this.head = head;
    return helper(0, size - 1);
}

private TreeNode helper(int start, int end) {
    if (start > end) {
        return null;
    }
    int mid = (start + end) / 2;
    TreeNode left = helper(start, mid - 1);
    TreeNode node = new TreeNode(this.head.val);
    node.left = left;
    this.head = this.head.next;
    node.right = helper(mid + 1, end);
    return node;
}

private int size(ListNode head) {
    int count = 0;
    ListNode cur = head;
    while (cur != null) {
        count++;
        cur = cur.next;
    }
    return count;
}
```
