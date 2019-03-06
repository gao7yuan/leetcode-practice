# 72. Edit Distance
3/6/19
*Hard*

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:
```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```
Example 2:
```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

## Solutions
- d(i, j) = minD(word1[0..i-1], word2[0..j-1])
- d(i, j) = d(i-1, j-1) if word1[i] == word2[j]
- d(i, j) = 1 + min(d(i-1, j), d(i, j-1), d(i-1, j-1)) if word1[i] != word2[j]
- base case
  - i == 0 -> d(i, j) = j
  - j == 0 -> d(i, j) = i
- O(nm) time, O(nm) space

```Java
public int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();
    if (n == 0) {
        return m;
    }
    if (m == 0) {
        return n;
    }

    // or
    // if one of the strings is empty
    // if (n * m == 0)
    // return n + m;
    
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
        dp[i][0] = i;
    }
    for (int j = 1; j <= m; j++) {
        dp[0][j] = j;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                min = Math.min(min, dp[i - 1][j - 1]);
                dp[i][j] = 1 + min;
            }
        }
    }
    return dp[n][m];
}
```

- optimize space
  - only need two rows or columns
  - O(min(n, m)) space
```Java
public int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();
    if (n == 0) {
        return m;
    }
    if (m == 0) {
        return n;
    }
    int[][] dp = new int[2][m + 1];
    // first row: previous computation. second row: current computation
    // init first row
    for (int j = 0; j <= m; j++) {
        dp[0][j] = j;
    }
    // starting from second row
    for (int i = 1; i <= n; i++) {
        // fill second row
        dp[1][0] = i;
        for (int j = 1; j <= m; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[1][j] = dp[0][j - 1];
            } else {
                int min = Math.min(dp[0][j], dp[1][j - 1]);
                min = Math.min(min, dp[0][j - 1]);
                dp[1][j] = 1 + min;
            }
        }
        // after filling second row, copy to first row
        for (int j = 0; j <= m; j++) {
            dp[0][j] = dp[1][j];
        }
    }
    return dp[0][m];
}
```
