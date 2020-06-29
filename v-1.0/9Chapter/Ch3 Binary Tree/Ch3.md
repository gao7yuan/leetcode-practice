# Ch3. Binary Tree

## DFS
* pre-order, post-order, in-order
* recursion, iteration
* iteration:
  - stack
  - 是否为空
  - 先放右，再放左

* pre-order 模板
  - traverse
```
public List<Integer> preorderTraversal(TreeNode root) {
    // write your code here
    List<Integer> res = new ArrayList<>();
    traverse(root, res);
    return res;
}

private void traverse(TreeNode root, List<Integer> res) {
    if (root == null) {
        return;
    }
    res.add(root.val);
    traverse(root.left, res);
    traverse(root.right, res);
}
```
  - stack
```
public List<Integer> preorderTraversal(TreeNode root) {
    // write your code here
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    if (root == null) {
        return res;
    }
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        res.add(cur.val);
        if (cur.right != null) {
            stack.push(cur.right);
        }
        if (cur.left != null) {
            stack.push(cur.left);
        }
    }
    return res;
}
```
  - divide and conquer
```
public List<Integer> preorderTraversal(TreeNode root) {
    // write your code here
    List<Integer> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    res.add(root.val);
    if (root.left != null) {
        res.addAll(preorderTraversal(root.left));
    }
    if (root.right != null) {
        res.addAll(preorderTraversal(root.right));
    }
    return res;
}
```

## Divide and conquer
