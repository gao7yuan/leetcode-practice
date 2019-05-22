# DP3

## 序列型动态规划
- 有状态的序列型动态规划
  - paint house

- 状态划分

- 特点
  - 变种多
  - 没有固定模板

- 序列型动态规划
  - 给定一个序列
  - f(i)的i表示前i个元素a[0], a[1], ..., a[i - 1]的某种性质
    - 坐标型f(i)的i表示以ai为结尾的某种性质
  - 初始化，f(0)表示空序列的性质
    - 坐标型f(0)指以a0为结尾的子序列的性质

## Problems
- Digital flip
  - 给定长度为N的01串
  - 要求翻转其中最少的01，使得结果中没有"01"这样的子串

  - 例子：
  - 输入：[1, 0, 1, 0, 1, 0]
  - 输出：2 （变成[1, 1, 1, 1, 1, 0]）

  - 翻->1-ai；不翻->ai
  - brute force: 2^n

  - 最后一步：最优策略中，最后一位数是否翻转
  - 但需要知道前一位数已经变成0还是1
  - 并且前N-1位数最少翻转多少次，满足要求（无01子串）
    - 并且要知道an-2是0还是1
  - **不知道的信息加入状态里**
  - 状态
  - 用f(i, 0)表示A[i-1]变成0的情况下，前i位最少翻转多少个能满足要求
  - 用f(i, 1)表示A[i-1]变成1的情况下，前i位最少翻转多少个能满足要求

  - 转移方程
    - f(i, j) = min_(k, j) != (0, 1){f(i-1, k), I_A[i-1] != j}
    - A[i-1]是j，前i位最少翻转多少个能满足要求；A[i-2]是k，前i-1位最少翻转多少个能满足要求；如果A[i-1]需要翻转，加1

  - 初始条件
    - f(0, 0) = f(0, 1) = 0
  - 最终答案min{f(N, 0), f(N, 1)}

  - O(n) time O(n) space -> O(1) space

```Java
public int flipDigit(int[] A) {
  int n = A.length;
  if (n <= 1) {
    return 0;
  }

  int[][] f = new int[n + 1][2];
  f[0][0] = f[0][1] = 0;
  int i, j, k, t;
  for (i = 1; i <= n; i++) {
    // change A[i - 1] to j
    for (j = 0; j < 2; j++) {
      f[i][j] = Integer.MAX_VALUE;

      // change A[i - 2] to k
      for (k = 0; k < 2; k++) {
        // (k, j) is not (0, 1)
        if (k == 0 && j == 1) {
          continue;
        }
        t = f[i - 1][k];
        if (j != A[i - 1]) { // we need to flip A[i - 1]
          t++;
        }
        f[i][j] = Math.min(f[i][j], t);
      }
    }
  }
  return Math.min(f[n][0], f[n][1]);
}
```

  - 滚动数组
```Java
public int flipDigit(int[] A) {
  int n = A.length;
  if (n <= 1) {
    return 0;
  }

  int[][] f = new int[2][2];
  int now = 0, old;
  f[now][0] = f[now][1] = 0;
  int i, j, k, t;
  for (i = 1; i <= n; i++) {
    old = now;
    now = 1 - now;
    // change A[i - 1] to j
    for (j = 0; j < 2; j++) {
      f[i][j] = Integer.MAX_VALUE;

      // change A[i - 2] to k
      for (k = 0; k < 2; k++) {
        // (k, j) is not (0, 1)
        if (k == 0 && j == 1) {
          continue;
        }
        t = f[old][k];
        if (j != A[i - 1]) { // we need to flip A[i - 1]
          t++;
        }
        f[now][j] = Math.min(f[now][j], t);
      }
    }
  }
  return Math.min(f[now][0], f[now][1]);
}
```

- Paint house II
  - 一排N栋房子，每栋房子漆成K种颜色中的一种，任何两栋相邻的房子不能漆成同样的颜色。房子i染成第j种颜色的花费是cost[i, j]。问最少需要多少钱油漆这些房子

  - 记录油漆前i栋房子并且房子并且房子i-1是颜色1，颜色2，...颜色k的最小花费:f[i, 1], f[i, 2], ..., f[i, k]
  - f(i, j) = min_k!=j{f(i-1, k), cost[i-1, j]}

  - Time:
    - i: 0 - N
    - j: 1 - K
    - k: 1 - K
    - O(NK^2)
  - optimization
    - 如果最小值是第i，次小值是第j
    1. 只要除掉的不是i，最小值就是i
    2. 如果除掉的是i，则是j
    - 记录最小值f(i-1, a)，次小值f(i-1, b)
    - 对于j = 1, 2, 3, ..., a-1, a+1, ..., K f(i, j) = f(i-1, a) + cost[i-1, j]
    - f(i, a) = f(i-1, b) + cost[i-1, a]
    - O(NK) time

```Java
public int minCostII(int[][] C) {
  int n = C.length;
  if (n == 0) {
    return 0;
  }

  int K = C[0].length;
  if (K == 0) {
    return 0;
  }

  int[][] f = new int[n + 1][K];
  int i, j, k, a, b;
  // init. first 0 houses
  for (i = 0; i < K; i++) {
    f[0][i] = 0;
  }

  for (i = 1; i <= n; i++) {
    // find minimum f[i-1][a] and 2nd minimum f[i-1][b] among
    // f[i-1][0], f[i-1][1], ..., f[i-1][k-1]
    a = b = -1;
    for (k = 0; k < K; k++) {
      if (a == -1 || f[i - 1][k] < f[i - 1][a]) {
        b = a; // old min becomes 2nd min
        a = k;
      } else {
        if (b == -1 || f[i - 1][k] < f[i - 1][b]) {
          b = k;
        }
      }
    }

    for (j = 0; j < K; j++) {
      if (j != a) {
        f[i][j] = f[i - 1][a] + C[i - 1][j];
      } else {
        f[i][j] = f[i - 1][b] + C[i - 1][j];
      }
    }
  }
  int res = Integer.MAX_VALUE;
  for (j = 0; j < K; j++) {
    res = Math.min(res, f[n][j]);
  }

  return res;
}
```

  - 打印路径 - 倒着找

  ```Java
  public int minCostII(int[][] C) {
    int n = C.length;
    if (n == 0) {
      return 0;
    }

    int K = C[0].length;
    if (K == 0) {
      return 0;
    }

    int[][] f = new int[n + 1][K];
    int[][] pi = new int[n + 1][K]; // pi[i][j] tells you which f[i-1][?] contributes to f[i][j]
    int i, j, k, a, b;
    // init. first 0 houses
    for (i = 0; i < K; i++) {
      f[0][i] = 0;
    }

    for (i = 1; i <= n; i++) {
      // find minimum f[i-1][a] and 2nd minimum f[i-1][b] among
      // f[i-1][0], f[i-1][1], ..., f[i-1][k-1]
      a = b = -1;
      for (k = 0; k < K; k++) {
        if (a == -1 || f[i - 1][k] < f[i - 1][a]) {
          b = a; // old min becomes 2nd min
          a = k;
        } else {
          if (b == -1 || f[i - 1][k] < f[i - 1][b]) {
            b = k;
          }
        }
      }

      for (j = 0; j < K; j++) {
        if (j != a) {
          f[i][j] = f[i - 1][a] + C[i - 1][j];
          pi[i][j] = a;
        } else {
          f[i][j] = f[i - 1][b] + C[i - 1][j];
          pi[i][j] = b;
        }
      }
    }
    int res = Integer.MAX_VALUE;
    k = 0;
    for (j = 0; j < K; j++) {
      res = Math.min(res, f[n][j]);
      if (f[n][j] == res) {
        k = j;
      }
    }

    // best ans is f[n][k]
    // let's traverse back from f[n][k]
    int[] color = new int[n];
    for (i = n; i >= 1; i--) {
      color[i - 1] = k;
      // jump from f[i][k] to f[i-1][???], ??? = pi[i][k]
      k = pi[i][k];
    }

    for (i = 0; i < n; i++) {
      System.out.println("House" + i + " is painted with color " + color[i]);
    }

    return res;
  }
  ```
