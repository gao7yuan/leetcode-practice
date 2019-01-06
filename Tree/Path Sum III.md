# 437. Path Sum III
12/29/18
*Easy* *I don't think it is easy* *最优解一知半解*

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
```

## Solutions
* DFS
  - 对于一个root，call dfs on root, root.left, root.right
  - dfs: recursively call dfs on root.left, root.right
  - Time complexity: O(n^2) if linear, O(nlgn) if balanced
  - Space: O(n) due to recursion stack
  - Dual DFS, not efficient.
```Java
public int pathSum(TreeNode root, int sum) {
    if (root == null) {
        return 0;
    }
    return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
}

private int pathSumFrom(TreeNode root, int sum) {
    if (root == null) {
        return 0;
    }
    return (root.val == sum ? 1 : 0)
        + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
}
```
