# 538. Convert BST to Greater Tree
*Easy* *二刷* *只看了recursion* *已整理*
08/23/18

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

**Example:**
```
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
```

## Solution
* visit each node once to make it efficient
* visit nodes in descending order, keep a sum of all values and add it to the node's values
* *reverse in-order traversal*

* Recursion
```
class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
```
  - O(n) time, O(n) space.
* Iteration with a Stack
  - push all nodes to the rightmost leaf onto stack
  - visit the node on the top of stack
  - consider left subtree
  - O(n) time, O(n) space.
```  
  public TreeNode convertBST(TreeNode root) {
          int sum = 0;
          TreeNode node = root;
          Stack<TreeNode> stack = new Stack<TreeNode>();

          while (!stack.isEmpty() || node != null) {
              /* push all nodes up to (and including) this subtree's maximum on
               * the stack. */
              while (node != null) {
                  stack.add(node);
                  node = node.right;
              }

              node = stack.pop();
              sum += node.val;
              node.val = sum;

              /* all nodes with values between the current and its parent lie in
               * the left subtree. */
              node = node.left;
          }

          return root;
      }
```
* Reverse Morris In-order Traversal
  - O(n) time, O(1) space.
