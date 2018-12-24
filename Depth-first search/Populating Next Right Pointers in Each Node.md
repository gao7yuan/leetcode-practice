# 116. Populating Next Right Pointers in Each Node
12/23/18
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
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
Example:

Given the following perfect binary tree,
```
     1
   /  \
  2    3
 / \  / \
4  5  6  7
```
After calling your function, the tree should look like:
```
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
```

## Solutions
`pre` points to root. At this point, the `next`'s on the level of `pre` are already handled.
If `pre` has children, set `next`'s of `pre`'s children level.
On the same level, use next to access siblings.
finish when `pre` has no more children.
* O(n) time - visit all the nodes, O(1) space

```Java
public void connect(TreeLinkNode root) {
    if (root == null) {
        return;
    }
    TreeLinkNode pre = root;
    TreeLinkNode cur;
    while (pre.left != null) {
        cur = pre;
        while (cur != null) {
            cur.left.next = cur.right;
            if (cur.next != null) {
                cur.right.next = cur.next.left;
            }
            cur = cur.next;
        }
        pre = pre.left;
    }
}
```
