# 111. Minimum depth of a binary tree
7/28/18

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
```
return its minimum depth = 2.

## Attempts
### First attempt
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import static java.lang.Math.min;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        return min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
```
* This works for binary trees with all nodes having either zero or 2 children.
* This does not work for the case of binary trees with nodes having only 1 child.
* e.g. [1, 2] will return 1 but expected is 2.
* Reason: for node [1], left depth = 1, right depth = 0, will take 0 and add 1 to finish recursion. However, null is not a leaf. Instead, [2] is a leaf.
### Second attempt
* To fix it, add a condition. When the node has 1 child, take the max of the depths of its left and right children (one of them is 0 coming from null). When the node has 0 or 2 children, take the min.
* O(n)
```
import static java.lang.Math.max;
import static java.lang.Math.min;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null ^ root.right == null) {
            return max(minDepth(root.left), minDepth(root.right)) + 1;
        } else {
            return min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
```
## Solution
* Similar logic, but a different way of handling recursion.
```
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }
}
```
