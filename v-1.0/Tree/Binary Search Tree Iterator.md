# 173. Binary Search Tree Iterator
12/28/18
*Medium*

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

## Attempts
* in order traversal to store all values into a queue, then dequeue when calling `next()`
  - O(1) time for `next()` and `hasNext()`, O(n) space to store all the values
```Java
private Queue<Integer> queue = new LinkedList<>();

public BSTIterator(TreeNode root) {
    inorder(root);
}

private void inorder(TreeNode root) {
    if (root == null) {
        return;
    }
    inorder(root.left);
    queue.add(root.val);
    inorder(root.right);
}

/** @return the next smallest number */
public int next() {
    return queue.remove();
}

/** @return whether we have a next smallest number */
public boolean hasNext() {
    return !queue.isEmpty();
}
```

## Solutions
* Use a stack, push root all the way to the left. When calling `next()`, pop, and push current node.right all the way to the left.
  - O(1) for `hasNext()`, averagely O(1) time for `next()` (in some cases O(h) but for an average case O(1)). O(h) space
```Java
private Stack<TreeNode> stack = new Stack<TreeNode>();

public BSTIterator(TreeNode root) {
    pushAll(root);
}

/** @return whether we have a next smallest number */
public boolean hasNext() {
    return !stack.isEmpty();
}

/** @return the next smallest number */
public int next() {
    TreeNode tmpNode = stack.pop();
    pushAll(tmpNode.right);
    return tmpNode.val;
}

private void pushAll(TreeNode node) {
    for (; node != null; stack.push(node), node = node.left);
}
```
