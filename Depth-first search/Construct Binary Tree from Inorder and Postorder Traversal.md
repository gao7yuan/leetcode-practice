# 106. Construct Binary Tree from Inorder and Postorder Traversal
12/22/18
*Medium*

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
```
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
```
Return the following binary tree:
```
    3
   / \
  9  20
    /  \
   15   7
```

## Attempts
* similar as 105. end of postorder is the root.
```Java
public TreeNode buildTree(int[] inorder, int[] postorder) {
    return helper(postorder.length - 1, 0, postorder.length - 1, inorder, postorder);
}

private TreeNode helper(int postStart, int inStart, int inEnd, int[] inorder, int[] postorder) {
    if (postStart < 0 || inStart > inEnd) {
        return null;
    }
    TreeNode root = new TreeNode(postorder[postStart]);
    int inIndex = 0;
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
        }
    }
    root.right = helper(postStart - 1, inIndex + 1, inEnd, inorder, postorder);
    root.left = helper(postStart - inEnd + inIndex - 1, inStart, inIndex - 1, inorder, postorder);
    return root;
}
```
