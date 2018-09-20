# 94. Binary Tree Inorder Traversal
*Medium*
09/19/18

Given a binary tree, return the inorder traversal of its nodes' values.

Example:
```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
```
Follow up: Recursive solution is trivial, could you do it iteratively?

## Attempts
* Recursion
  - O(n) time, worst space: O(n), average space O(logn)
```
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    if (root.left != null) res.addAll(inorderTraversal(root.left));
    res.add(root.val);
    if (root.right != null) res.addAll(inorderTraversal(root.right));
    return res;
}
```

## Solutions
* Iteration
  - push all the way to the left, then pop...
  - O(n) time, O(n) space
```
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        node = stack.pop();
        res.add(node.val);
        node = node.right;
    }
    return res;
}
```
* Morris Traversal
  - Use a Threaded Binary Tree
>>Step 1: Initialize current as root
  Step 2: While current is not NULL,
  If current does not have left child
    a. Add current’s value
    b. Go to the right, i.e., current = current.right
  Else
    a. In current's left subtree, make current the right child of the rightmost node
    b. Go to this left child, i.e., current = current.left

```
public List < Integer > inorderTraversal(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    TreeNode curr = root;
    TreeNode pre;
    while (curr != null) {
        if (curr.left == null) {
            res.add(curr.val);
            curr = curr.right; // move to next right node
        } else { // has a left subtree
            pre = curr.left;
            while (pre.right != null) { // find rightmost
                pre = pre.right;
            }
            pre.right = curr; // put cur after the pre node
            TreeNode temp = curr; // store cur node
            curr = curr.left; // move cur to the top of the new tree
            temp.left = null; // original cur left be null, avoid infinite loops
        }
    }
    return res;
}
```
