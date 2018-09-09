# 404. Sum of Left Leaves
*Easy* *二刷*
08/24/18

Find the sum of all left leaves in a given binary tree.

**Example:**
```
    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
```

## Solution
* Recursion
  - if the left child does not have left and right child, add the val to sum; if it has, call ```sumOfLeftLeaves```.
  - recursively call ```sumOfLeftLeaves``` on the right child.
```
public int sumOfLeftLeaves(TreeNode root) {
    int sum = 0;
    if (root == null) {
        return sum;
    }
    if (root.left != null) {
        if (root.left.left != null || root.left.right != null) {
            sum += sumOfLeftLeaves(root.left);
        } else {
            sum += root.left.val;   
        }
    }
    if (root.right != null) { // can leave out if statement
        sum += sumOfLeftLeaves(root.right);
    }
    return sum;
}
```
* Iteration with a Stack
```
public int sumOfLeftLeaves(TreeNode root) {
    int sum = 0;
    if (root == null) {
        return sum;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        if (cur.left != null) {
            stack.push(cur.left);
            if (stack.peek().left == null && stack.peek().right == null) {
                sum += stack.pop().val;
            }
        }
        if (cur.right != null) {
            stack.push(cur.right);
        }
    }
    return sum;
}
```
