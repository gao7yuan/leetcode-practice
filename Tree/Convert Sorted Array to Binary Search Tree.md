# 108. Convert Sorted Array to Binary Search Tree
*Easy* *二刷*
09/04/18

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
```
Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
```

## Attempts
* Recursion
* 每次取array的中间值赋给root，左半边的中间值给左孩子，右半边的中间值给右孩子，循环往复。
* 实现需要一个helper method，因为需要对index进行操作。
```
public TreeNode sortedArrayToBST(int[] nums) {
    TreeNode root = createNode(nums, 0, nums.length - 1);
    return root;
}
```
  - 中间值给root，左边中间值给left child，右边中间值给right child。直到low大于high。mid值每次要加一或者减一否则无法终止。
```
private TreeNode createNode(int[]nums, int low, int high) {
    if (low > high) {
        return null;
    }
    int mid = low + (high - low) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = createNode(nums, low, mid - 1);
    node.right = createNode(nums, mid + 1, high);
    return node;
}
```
