# 104. Maximum Depth of Binary Tree
*Easy* *二刷* *已整理*
7/30/18

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
```
return its depth = 3.

## Attempts
* DFS 没啥好说的
```
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```
