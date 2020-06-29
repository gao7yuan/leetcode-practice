# 100. Same Tree
*Easy* *二刷* *已整理*
07/19/18

Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

**Example 1:**
```
Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
```
**Example 2:**
```
Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
```
**Example 3:**
```
Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
```
## Attempts
Recursion
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        TreeNode node1 = p;
        TreeNode node2 = q;

        if (node1 == null) {
            return node2 == null;
        } else if (node2 == null) {
            return false;
        } else {
            return node1.val == node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
        }

    }
}
```
## Solution
Do not need to declare variables...:(
```
public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    if(p == null || q == null) return false;
    if(p.val == q.val)
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    return false;
}
```
