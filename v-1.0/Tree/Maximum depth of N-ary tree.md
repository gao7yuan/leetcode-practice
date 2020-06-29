# 559. Maximum Depth of N-ary Tree
*Easy* *二刷* *已整理*
7/30/18

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:**
The depth of the tree is at most 1000.
The total number of nodes is at most 5000.

## Attempts
* Tried to use for each for children and a helper method to find maximum from a list of integers but somehow does not pass.

## Solution
```
public int maxDepth(Node root) {
        if (root == null) return 0;

        int max = 0;
        for (Node child : root.children) { //replace left&right to for loop
            int value = maxDepth(child);

            if (value > max) max = value;
        }
        return max +1;
    }
```    
