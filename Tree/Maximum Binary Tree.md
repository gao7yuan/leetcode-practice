# 654. Maximum Binary Tree
1/5/19
*Medium*

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
```
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0   
       \
        1
```
Note:
The size of the given array will be in the range [1,1000].

## Attempts
* Brute force, recursion
  1. find max index between start index and index of array nums
  2. nums[max] will be the value of the root
  3. recursively do this on the left part and right part
  - Time complexity: find max takes O(n) time for array of size n. Therefore, for a sorted array, it takes n + (n-1) + (n-2) + ... + 1 = O(n^2) time to find max, in the worst case. In the average case O(nlgn) time, because of divide and conquer.
  - Space complexity: O(n) for worst case and O(nlgn) for avg case
* O(n) solution using monotonic stack
  1. For each number, make a TreeNode
  2. If top of stack smaller than current number, set current.left = stack.pop(). This is to find the largest number in the left part.
  3. If coming across a number larger than current number, this larger number would be the parent of the current number
  ```java
  public TreeNode constructMaximumBinaryTree(int[] nums) {
    Deque<TreeNode> stack = new LinkedList<>();
    for(int i = 0; i < nums.length; i++) {
        TreeNode curr = new TreeNode(nums[i]);
        while(!stack.isEmpty() && stack.peek().val < nums[i]) {
            curr.left = stack.pop();
        }
        if(!stack.isEmpty()) {
            stack.peek().right = curr;
        }
        stack.push(curr);
    }

    return stack.isEmpty() ? null : stack.removeLast();
}
```
