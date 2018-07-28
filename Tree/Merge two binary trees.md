# 617. Merge two binary trees
7/28/18

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

**Example 1:**
```
Input:
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
```
**Note:** The merging process must start from the root nodes of both trees.

## Attempts
* DFS
```
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {
            t1.val = t1.val + t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }  
    }
```

## Solution
* Maybe it's better not to mutate t1.

Modified from my method:
```
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {
            TreeNode newNode = new TreeNode(t1.val + t2.val);
            newNode.left = mergeTrees(t1.left, t2.left);
            newNode.right = mergeTrees(t1.right, t2.right);
            return newNode;
        }  
    }
```
Other options...
```
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null){return null;}
        if(t1 == null){return t2;}
        if(t2 == null){return t1;}
        TreeNode newNode = new TreeNode(t1.val+t2.val);
        newNode.left = mergeTrees(t1.left, t2.left);
        newNode.right = mergeTrees(t1.right, t2.right);
        return newNode;
    }
```     
