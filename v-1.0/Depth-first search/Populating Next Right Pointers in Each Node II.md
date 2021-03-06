# 117. Populating Next Right Pointers in Each Node II
12/26/18
*Medium*

Given a binary tree

struct TreeLinkNode {
  TreeLinkNode left;
  TreeLinkNode right;
  TreeLinkNode next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,
```
     1
   /  \
  2    3
 / \    \
4   5    7
```
After calling your function, the tree should look like:
```
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
```

## Solutions
* O(n) time, O(1) space. level order traversal
```Java

// level order traversal
public void connect(TreeLinkNode root) {
    TreeLinkNode cur = root; // current node
    TreeLinkNode head = null; // head of next level
    TreeLinkNode prev = null; // previous node of next level

    while (cur != null) {
        // traverse to right
        while (cur != null) {
            if (cur.left != null) {
                if (prev != null) {
                    prev.next = cur.left;
                } else {
                    head = cur.left;
                }
                prev = cur.left;
            }
            if (cur.right != null) {
                if (prev != null) {
                    prev.next = cur.right;
                } else {
                    head = cur.right;
                }
                prev = cur.right;
            }
            cur = cur.next;
        }
        cur = head;
        head = null;
        prev = null;
    }
}
```
