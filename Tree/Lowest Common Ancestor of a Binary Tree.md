# 236. Lowest Common Ancestor of a Binary Tree
1/5/19
*Medium*

## Notes
* Pair<K, V>

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```
Example 2:
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

## Solutions
基本思想：Use normal tree traversal to find p and q, and backtrack to find LCA.
### 1. Recursion approach
* DFS. If found one node, use a boolean flag.
* Algorithm:
  1. Start traversal from root
  2. If root == one of p and q, `mid` = true. Traverse `left` and `right` branches
  3. If `left` or `right` == true -> the other node is found below
  4. If at any point of traversal, 2 of the 3 flags `left` `right` `mid` are true, then we have found the LCA
* O(n) time, O(n) space
```Java
private TreeNode res;

public Solution() {
    this.res = null;
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    LCA(root, p, q);
    return res;
}

private boolean LCA(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return false;
    }
    // DFS
    int mid = (root == p || root == q) ? 1 : 0;
    int left = LCA(root.left, p, q) ? 1 : 0;
    int right = LCA(root.right, p, q) ? 1 : 0;
    if (mid + left + right >= 2) {
        this.res = root;
    }
    return mid == 1 || left == 1 || right == 1; // or return (mid + left + right > 0);
}
```
### 2. Iterative using parent pointers
* Find p and q, traverse back to find ancestors. The first common node would be LCA.
* Algorithm:
  1. Traverse the tree to find p and q. Record parents in a hashmap.
  2. Once p and q are both found, find p's ancestors and store in a set. Trace q's ancestors and check existence in the set.
* O(n) time, O(n) space
```Java
private Map<TreeNode, TreeNode> parents = new HashMap<>();

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    DFS(root, p);
    DFS(root, q);
    Set<TreeNode> set = new HashSet<>();
    TreeNode cur = p;
    set.add(cur);
    while (parents.containsKey(cur)) {
        cur = parents.get(cur);
        set.add(cur);
    }
    cur = q;
    while (!set.contains(cur)) {
        cur = parents.get(cur);
    }
    return cur;
}

private void DFS(TreeNode root, TreeNode node) {
    if (root == null || root == node) {
        return;
    }
    if (root.left != null) {
        parents.put(root.left, root);
    }
    if (root.right != null) {
        parents.put(root.right, root);
    }
    DFS(root.left, node);
    DFS(root.right, node);
}
```
### 3. Iterative without parent pointers
* Post-order traversal
* Algorithm:
  1. Start from root
  2. Put (root, root_state) onto stack (root_state = both_pending, left_done, or both_done)
  3. Peek top of stack as (parent, parent_state). Check if parent is one of p and q.
  4. If first time found p or q, set `LCA_index` to top of stack. If second time found p or q, return `LCA_index` node
  5. Whenever visit a child of parent, update (parent, updated_parent_state)
* O(n) time, O(n) space
```Java
import javafx.util.*;

class Solution {

    // Three static flags to keep track of post-order traversal.

    // Both left and right traversal pending for a node.
    // Indicates the nodes children are yet to be traversed.
    private static int BOTH_PENDING = 2;

    // Left traversal done.
    private static int LEFT_DONE = 1;

    // Both left and right traversal done for a node.
    // Indicates the node can be popped off the stack.
    private static int BOTH_DONE = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

        // Initialize the stack with the root node.
        stack.push(new Pair<TreeNode, Integer>(root, Solution.BOTH_PENDING));

        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;

        // This is used to keep track of the LCA.
        TreeNode LCA = null;

        // Child node
        TreeNode child_node = null;

        // We do a post order traversal of the binary tree using stack
        while (!stack.isEmpty()) {

            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parent_node = top.getKey();
            int parent_state = top.getValue();

            // If the parent_state is not equal to BOTH_DONE,
            // this means the parent_node can't be popped off yet.
            if (parent_state != Solution.BOTH_DONE) {

                // If both child traversals are pending
                if (parent_state == Solution.BOTH_PENDING) {

                    // Check if the current parent_node is either p or q.
                    if (parent_node == p || parent_node == q) {

                        // If one_node_found was set already, this means we have found
                        // both the nodes.
                        if (one_node_found) {
                            return LCA;
                        } else {
                            // Otherwise, set one_node_found to True,
                            // to mark one of p and q is found.
                            one_node_found = true;

                            // Save the current top element of stack as the LCA.
                            LCA = stack.peek().getKey();
                        }
                    }

                    // If both pending, traverse the left child first
                    child_node = parent_node.left;
                } else {
                    // traverse right child
                    child_node = parent_node.right;
                }

                // Update the node state at the top of the stack
                // Since we have visited one more child.
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

                // Add the child node to the stack for traversal.
                if (child_node != null) {
                    stack.push(new Pair<TreeNode, Integer>(child_node, Solution.BOTH_PENDING));
                }
            } else {

                // If the parent_state of the node is both done,
                // the top node could be popped off the stack.
                // Update the LCA node to be the next top node.
                if (LCA == stack.pop().getKey() && one_node_found) {
                    LCA = stack.peek().getKey();
                }

            }
        }

        return null;
    }
}
```
