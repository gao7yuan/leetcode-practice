# 110. Balanced Binary Tree
*Easy* *二刷* *已整理*
7/28/18

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

> a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

**Example 1:**

Given the following tree [3,9,20,null,null,15,7]:
```
    3
   / \
  9  20
    /  \
   15   7
Return true.
```
**Example 2:**
```
Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
 ```
Return false.

## Attempts
* Got hint from discussion:
  - Strictly following the description of the requirement:
    - Depth of left and right tree differ no more than 1
    - Left and right trees are also balanced
  - Need to get depth: use recursion
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
import static java.lang.Math.abs;
import static java.lang.Math.max;
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int left = depth(root.left);
        int right = depth(root.right);

        return abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    int depth(TreeNode root) {
        if (root == null) return 0;

        return max(depth(root.left), depth(root.right)) + 1;
    }
}
```
* Java notes:
  - ```import static java.lang.Math.abs;```
  - ```import static java.lang.Math.max;```

## Solution
* Top down approach as stated above.
  - Analysis:
    - To get depth of left and right trees we need to traverse all the nodes, so O(n). We do this for each node, so **O(n^2)** in total.
* Bottom up method, DFS
  - ```dfsHeight()```: for a node, if it is balanced, return the height of the node; if it is not balanced, return -1.
  - Tree concepts:
    - The **depth** of a node is the number of edges from the node to the tree's **root** node. A root node will have a depth of 0.
    - The **height** of a node is the number of edges on the longest path from the node to a **leaf**. A leaf node will have a height of 0.

  - Only need to visit each node for once, **O(n)**.  
```
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    int dfsHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = dfsHeight(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfsHeight(root.right);
        if (rightHeight == -1) return -1;

        if (abs(leftHeight - rightHeight) > 1) return -1;
        return max(leftHeight, rightHeight) + 1;
    }
```
