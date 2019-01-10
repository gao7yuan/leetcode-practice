- Knapsack
  - input: n items, values `v[i]`, weight `w[i]`
  - find maximum value within the weight of a knapsack

  - base problem: smaller bags, fewer items
  - f(i, j) = the total value, choose item [1..i] with knapsack capacity of j as max
    - if taking ith item: f(i-1, j-w[i]) + v[i]
    - if not taking ith item: f(i-1, j)
    - base case: f(0, j) = f(i, 0) = 0.
```Java
public int knapsack(int[] values, int[] weights, int W, int n) {
  int[][] dp = new int[n + 1][W + 1];
  for (int i = 0; i <= n; i++) {
    dp[i][0] = 0;
  }
  for (int j = 0; j <= W; j++) {
    dp[0][j] = 0;
  }
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= W; j++) {
      dp[i][j] = dp[i - 1][j];
      if (j >= weights[i]) {
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i]] + values[i]);
      }
    }
  }
  return dp[n][W];
}
```

- Knapsack with repetition
  - f(i) = choose from n items with maximum weight i
  - f(i) = f(i-w[j])+v[j] i >= w[j]
  - base case: f(i) = 0
```Java
public int knapsack(int[] v, int[] w, int n, int W) {
  int[] dp = new int[W + 1];
  dp[0] = 0;
  for (int i = 1; i <= W; i++) {
    for (int j = 0; j < n; j++) {
      if (i >= w[j]) {
        dp[i] = Math.max(dp[i], dp[i - w[j]] + v[j]);
      }
    }
  }
  return dp[W];
}
