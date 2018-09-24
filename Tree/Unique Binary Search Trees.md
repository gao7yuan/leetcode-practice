# 96. Unique Binary Search Trees
*Medium*
09/20/18

Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:
```
Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

## Attempts
* Recursion (time limit exceeded)
```
public int numTrees(int n) {
    int num = 0;
    if (n == 0 || n == 1) return 1;
    for (int i = 1; i <= n ; i++) {
        num += numTrees(i - 1) * numTrees(n - i);
    }
    return num;
}
```
* dp
  - O(n) time, O(n) space
```
public int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        for (int j = 0; j < i; j++) {
            dp[i] += dp[j] * dp[i - j - 1];
        }
    }
    return dp[n];
}
```

## Solutions
* dp
* Mathematical deduction: Catalan number Cn
