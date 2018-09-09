# 101. Symmetric Tree
*Easy* *二刷*
08/31/18

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```
But the following [1,2,2,null,3,null,3] is not:
```
    1
   / \
  2   2
   \   \
   3    3
```
Note:
Bonus points if you could solve it both recursively and iteratively.

## Attempts
* Recursion.
  - A binary tree is symmetric when its left and right children are mirrors.
  - Two trees are mirrors when: the roots have the same value, and the left child of the first one is mirror with the right child with the second one (vise versa).
  - O(n) time, O(n) space.
```
public boolean isSymmetric(TreeNode root) {
    if (root == null) return true; // 这一行可以省略，然后return isMirror(root, root);
    return isMirror(root.left, root.right);
}

private boolean isMirror(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null ^ right == null) return false;
    return left.val == right.val && isMirror(left.left, right.right)
        && isMirror(left.right, right.left);
}
```

## Solutions
* Recursion
* Iteration
  - Queue - BFS
  - 每次成对地将一个node的left和一个node的right（或者反过来）加入Queue，每次dequeue两个node，如果他们的值相等，则重复这个enqueue的操作。如果不相等，或者单个为null，return false。
