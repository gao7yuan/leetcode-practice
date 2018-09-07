# 226. Invert binary tree
*Easy* *二刷*
7/30/18

Invert a binary tree.

**Example:**
```
Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
```
**Trivia:**
This problem was inspired by this original tweet by Max Howell:

> Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.

## Attempts
* DFS.
```
class Solution {
    public TreeNode invertTree(TreeNode root) {
        TreeNode newRoot;
        if (root == null) return root;
        newRoot = new TreeNode(root.val);
        newRoot.right = invertTree(root.left);
        newRoot.left = invertTree(root.right);
        return newRoot;
    }
}
```

## Solution
* DFS (recursion) - Straightforward but not scalable. May cause stack overflow.
* DFS (iteration using stack).
  - ```Deque``` is implemented by ```LinkedList```, and can be a stack. It has ```push()``` and ```pop()``` methods. (It has both queue methods and stack methods.)
```
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            // swap left and right
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }
}
```
* BFS.
```
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
```
