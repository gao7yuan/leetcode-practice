# 114. Flatten Binary Tree to Linked List
12/23/18
*Medium* *我的方法的时间复杂度搞不懂*

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:
```
    1
   / \
  2   5
 / \   \
3   4   6
```
The flattened tree should look like:
```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

## Attempts
先flatten到左边，用dfs，再转移到右边
Time complexity: 假如都在左边，1+2+3+4...+n = O(n^2) time in worst case. O(n) space worst case

## Solutions
时间复杂度低一点的解法，不是很理解
```Java
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}
```
