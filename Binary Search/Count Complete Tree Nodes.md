# 222. Count Complete Tree Nodes
*Medium*
10/12/18

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:
```
Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
```

## Solutions
* Calculate height, then find height of left and right tree.

* Or, when complete, 2^depth - 1, otherwise recursion.

* To find height: O(lgn) time. To find fully complete tree: O(lgn) time. Total O((lgn)^2) time
```
public int countNodes(TreeNode root) {

    int leftDepth = leftDepth(root);
	int rightDepth = rightDepth(root);

	if (leftDepth == rightDepth)
		return (1 << leftDepth) - 1;
	else
		return 1+countNodes(root.left) + countNodes(root.right);

}

private int rightDepth(TreeNode root) {
	// TODO Auto-generated method stub
	int dep = 0;
	while (root != null) {
		root = root.right;
		dep++;
	}
	return dep;
}

private int leftDepth(TreeNode root) {
	// TODO Auto-generated method stub
	int dep = 0;
	while (root != null) {
		root = root.left;
		dep++;
	}
	return dep;
}
```
