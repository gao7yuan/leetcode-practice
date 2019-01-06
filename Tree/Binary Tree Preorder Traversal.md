# 144. Binary Tree Preorder Traversal
1/5/19
*Medium*

Given a binary tree, return the preorder traversal of its nodes' values.

Example:
```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
```
Follow up: Recursive solution is trivial, could you do it iteratively?

## Attempts
* DFS using stack
  - O(n) time, O(n) space from stack (and list)
```Java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        res.add(node.val);
        if (node.right != null) {
            stack.push(node.right);
        }
        if (node.left != null) {
            stack.push(node.left);
        }
    }
    return res;
}
```

## Solutions
* Morris Traversal - no additional space
  - O(n) time, O(n) space only from result list, not additional space
