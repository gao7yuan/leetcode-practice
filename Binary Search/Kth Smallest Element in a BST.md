# 230. Kth Smallest Element in a BST
*Medium*
10/13/18

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```
Example 2:
```
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

## Solutions
* in-order traversal
  - O(n) time
```
int count = 0;
int ans = 0;
public int kthSmallest(TreeNode root, int k) {
    traverse(root, k);
    return ans;
}

private void traverse(TreeNode root, int k) {
    if (root == null) {
        return;
    }
    if (root.left != null) {
        traverse(root.left, k);
    }
    count++;
    if (count == k) {
        ans = root.val;
        return;
    }
    if (root.right != null) {
        traverse(root.right, k);
    }
}
```
