# 105. Construct Binary Tree from Preorder and Inorder Traversal
12/22/18
*Medium*

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```
Return the following binary tree:
```
    3
   / \
  9  20
    /  \
   15   7
```

## Solutions
preorder: 3 9 20 15 7
inorder:  9 3 15 20 7

preorder的第一个数一定是root。
在inorder里，root讲inorder左右两边分成root的左右孩子两部分。
recursively do this.
`preStart` to iterate preorder
`inStart` and `inEnd` denotes the start and end index of inorder to work with
Algorithm:
1. get `preorder[preStart]`, make it `root`
2. search `inorder` from `inStart` to `inEnd` for the index that has value matching `root.val`, call it `inIndex`
3. recursively call this method to get the left child.
  - `inStart` is still `inStart`
  - `inEnd` becomes `inIndex - 1`
  - `preStart` becomes `preStart + 1`
4. recursively call this method to get the right child.
  **Tricky!**
  - `inStart` is `inIndex + 1`
  - `inEnd` is `inEnd`
  - `preStart`: when we start working with right child, this means all the vals to the left of `inIndex` are done, we know that there are `inIndex - inStart` number of vals to the left of `inIndex`, then plus 1 (`inIndex` itself). Then the `preStart` index we should be working with is `preStart + inIndex - inStart + 1`
5. base case:
  - when preStart > preorder.length - 1 or inStart > inEnd, return null
6. main method calls `buildTree(0, 0, preorder.length - 1, preorder, inorder)`

* O(n^2) time (visit each val in preorder takes O(n), searching takes O(n) for each value). can use binary search to reduce to O(nlgn) time. Or use a hashmap (for inorder, value - index pairs) to enable O(1) search, so O(n) time. O(lgn) space from recursion stack. (If using hashmap then O(n) space).

```Java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(0, 0, preorder.length - 1, preorder, inorder);
}

private TreeNode buildTree(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = 0;
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
            break;
        }
    }
    root.left = buildTree(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    root.right = buildTree(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
    return root;
}
```
