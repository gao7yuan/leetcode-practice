# 113. Path Sum II
12/24/18
*Medium* *Backtracking*

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:
```
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
```

## Solutions
* Backtracking, DFS. Remember to remove last integer of `path`.
* `path` is a reference. So when constructing the result, remember to use deep copy `new ArrayList<>(path)`.
* O(n) time, O(n) space worst case
```Java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    pathSum(root, sum, path, res);
    return res;
}

private void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
    if (root == null) {
        return;
    }
    path.add(root.val);
    if (root.left == null && root.right == null && root.val == sum) {
        res.add(new ArrayList<>(path));
    } else {
        pathSum(root.left, sum - root.val, path, res);
        pathSum(root.right, sum - root.val, path, res);     
    }

    path.remove(path.size() - 1);
}
```
