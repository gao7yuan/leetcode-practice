# 669. Trim a Binary Search Tree
*Easy* *二刷* *已整理*
7/29/18

Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

**Example 1:**
```
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
```
**Example 2:**
```
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2   
  /
 1
```

## Attempts
* Select the root of the new tree first by comparing the root with L and R
* Then get the left tree and the right tree of the new tree.
* To get left tree
  - if the root val is greater than L, then we want it and its all right children. Now only have to determine its left tree.
  - if the root val is smaller than L, then we discard this value and check its right children.
```
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode newRoot;
        if (root == null) return root;
        // who is root
        if (root.val > R) return trimBST(root.left, L, R);
        if (root.val < L) return trimBST(root.right, L, R);
        newRoot = root;
        newRoot = leftTree(newRoot, L);
        newRoot = rightTree(newRoot, R);
        return newRoot;
    }

    TreeNode leftTree(TreeNode root, int L) {
        TreeNode newRoot;
        if (root == null) return root;
        if (root.val >= L) {
            newRoot = root;
            newRoot.left = leftTree(root.left, L);
            return newRoot;
        } else return leftTree(root.right, L);
    }

    TreeNode rightTree(TreeNode root, int R) {
        TreeNode newRoot;
        if (root == null) return root;
        if (root.val <= R) {
            newRoot = root;
            newRoot.right = rightTree(root.right, R);
            return newRoot;
        } else return rightTree(root.left, R);
    }
}
```

## Solution
* Neater approach. Find the subtree to trim first, and then make sure its left and right trees are trimmed as well. Idea of **RECURSION**!
```
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
```
