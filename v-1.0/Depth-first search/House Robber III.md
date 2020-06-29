# 337. House Robber III
12/26/18
*Medium*

## Notes
* Two conditions for dynamic programming: optimal substructure + overlapping of subproblems

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
```
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
```
Example 2:
```
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
```

## Solutions
### 1. Naive intuition
* naive than DP
  - exponentially increasing running time
```Java
public int rob(TreeNode root) {
    if (root == null) return 0;

    int val = 0;

    if (root.left != null) {
        val += rob(root.left.left) + rob(root.left.right);
    }

    if (root.right != null) {
        val += rob(root.right.left) + rob(root.right.right);
    }

    return Math.max(val + root.val, rob(root.left) + rob(root.right));
}
```

### 2. DP
* Use a hashmap to record results for visited subtrees
  - O(n) time, O(n) space for hashmap
```Java
public int rob(TreeNode root) {
    return rob(root, new HashMap<TreeNode, Integer>());
}

private int rob(TreeNode root, HashMap<TreeNode, Integer> map) {
    if (root == null) {
        return 0;
    }
    if (map.containsKey(root)) {
        return map.get(root);
    }
    int value = 0;
    if (root.left != null) {
        value += rob(root.left.left, map) + rob(root.left.right, map);
    }
    if (root.right != null) {
        value += rob(root.right.left, map) + rob(root.right.right, map);
    }
    return Math.max(value + root.val, rob(root.left, map) + rob(root.right, map));
}
```
### 3. DP
* Use an array to record the money not robbing (0) or robbing (1) this house.
```Java
public int rob(TreeNode root) {
    int[] res = helper(root);
    return Math.max(res[0], res[1]);
}

private int[] helper(TreeNode root) {
    if (root == null) {
        return new int[2];
    }
    int[] left = helper(root.left);
    int[] right = helper(root.right);
    int[] res = new int[2];
    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    res[1] = left[0] + right[0] + root.val;
    return res;
}
```
