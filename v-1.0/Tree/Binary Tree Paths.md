# 257. Binary Tree Paths
*Easy* *二刷* *已整理*
09/05/18

Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:
```
Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
```

## Attempts
* 思路：
  - 先建一个StringBuilder，每个path用逗号分开，最后用split转化成array再转化成list
```
private StringBuilder btPaths(TreeNode root) {
    StringBuilder str = new StringBuilder();
    if (root == null) return str;
    str.append(root.val);
    if (root.left == null && root.right == null) return str.append(",");
    if (root.left != null)
        str.append("->").append(btPaths(root.left).toString());
    if (root.right != null)
        str.append("->").append(btPaths(root.right).toString());
    return str;
}
```
* 问题：
  - 这样每次不会将分叉的node之前的node再重复走一遍
  - 例如：
```
[1,2,3,null,5]
```
Expected:
```
["1->2->5","1->3"]
```
My answer:
```
["1->2->5","->3"]
```
* 解决：
  - 用一个String path来记录已经遍历过的node

## Solutions
```
public List<String> binaryTreePaths(TreeNode root) {
    List<String> list = new ArrayList<>();
    if (root != null) btPathHelper(root, "", list);
    return list;
}
```
  - 每次update path，res作为accumulator存在argument里面
```
private void btPathHelper(TreeNode root, String path, List<String> res) {
    if (root.left == null && root.right == null) res.add(path + root.val);
    if (root.left != null)
        btPathHelper(root.left, path + root.val + "->", res);
    if (root.right != null)
        btPathHelper(root.right, path + root.val + "->", res);
}
```
