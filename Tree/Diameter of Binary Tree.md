# 543. Diameter of Binary Tree
12/28/18
*Easy*

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
```
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
```
Note: The length of path between two nodes is represented by the number of edges between them.

## Solutions
* For a node, 通过这个node的最长path是左边的depth加上右边的depth
* 计算一个node的depth，要经过左右的最长path
* O(n) time visited each node for once. O(n) space for call stack.
```Java
private int res;
public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
        return 0;
    }
    res = 1;
    depth(root);
    return res - 1;
}

private int depth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int left = depth(root.left);
    int right = depth(root.right);
    res = Math.max(res, left + right + 1);
    return Math.max(left, right) + 1;
}
```
