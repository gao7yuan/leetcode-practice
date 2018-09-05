# 112. Path Sum
*Easy*
09/05/18

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:
```
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

## Attempts
* Recursion
* 思路：
  - 非leaf:如果左孩子不是空，则左孩子的值等于root的值加上左孩子的值。右孩子亦然。这样到达leaf的时候，leaf的值就是这条path的和。
  - return的值为recursively call ```hasSumPath(root.left, sum) || hasSumPath(root.right, sum)```. **!!**
  - leaf:左右孩子都为null，如果值等于sum，return true。
  - edge case:如果root == null, return false.
```
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    if (root.left == null && root.right == null) {
        if (root.val == sum) return true;
    }
    if (root.left != null) root.left.val = root.val + root.left.val;  
    if (root.right != null) root.right.val = root.val + root.right.val;
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
}
```

## Solutions
* 一样的思路，只是recursion的时候sum update成sum - root.val.
```
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null && sum == root.val) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```
