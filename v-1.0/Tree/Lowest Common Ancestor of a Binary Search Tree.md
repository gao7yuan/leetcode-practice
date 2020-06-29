# 235. Lowest Common Ancestor of a Binary Search Tree
*Easy* *二刷* *已整理*
09/05/18

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
```
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
```
Example 1:
```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
```
Example 2:
```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
             according to the LCA definition.
```
Note:

* All of the nodes' values will be unique.
* p and q are different and both values will exist in the BST.

## Attempts
* Recursion
  - 如果root值在p，q之间，则这就是要找的node
  - 如果root值小于p，q中的较小值，则在右孩子中寻找
  - 反之在左孩子中寻找
```
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    int small = p.val < q.val ? p.val : q.val;
    int big = p.val > q.val ? p.val : q.val;
    if (root.val >= small && root.val <= big) return root;
    if (root.val < small) return lowestCommonAncestor(root.right, p, q);
    else return lowestCommonAncestor(root.left, p, q);
}
```

## Solutions
* Iterative
  - O(1) space
```
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    return root;
}
```
* Recursion
```
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return (root.val - p.val) * (root.val - q.val) < 1 ? root :
           lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
}
```
* 我的总结：因为每次只需要更新root，不需要更新p和q，所以用iteration也可以简单实现。
