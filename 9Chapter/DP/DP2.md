# DP2

## HW
- maximum product subarray
  - 因为负数乘法的原因，需要记录到每个位置为止最大最小的乘积
  - 状态：f[j]=以a[j]结尾的连续子序列最大乘积，g[j]=以a[j]结尾的连续子序列的最小乘积
  - f[j]=max{a[j], max{a[j] * f[j-1], a[j] * g[j-1]}|j>0}
  - g[j]=min{a[j], min{a[j] * f[j-1], a[j] * g[j-1]}|j>0}

## Problems
- Unique paths II
  - m * n grid, start from (0, 0), move right or down, to bottom right corner. with obstacles
  - how many ways?

  - 最后一步: (i, i-1) or (i-1, j) -> (i, j)
    - f[i, j]=从左上角多少方法走到(i, j)
  - 坐标型动态规划： 数组下标即坐标
  - f[i, j] = f[i-1, j] + f[i, j-1]
  - 初始条件和边界情况
    - 如果从左上角或者右下角有障碍，直接输出0
    - 如果(i, j)有障碍，f[i, j]=0，表示机器人不能到达此格
    - 初始条件：f[0, 0] = 1
  - f[i, j] =
    - 0 如果(i, j)有障碍
    - 1， i=0 && j=0
    - f[i-1, j] 如果j=0 第一列
    - f[i, j-1] 如果i=0 第一行
    - f[i-1, j] + f[i, j-1] 其他

    - my code - lc63 二刷
```Java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
        return 0;
    }
    int n = obstacleGrid.length;
    int m = obstacleGrid[0].length;
    if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
        return 0;
    }
    int[][] dp = new int[n][m];
    dp[0][0] = 1;
    for (int i = 1; i < n; i++) {
        dp[i][0] = obstacleGrid[i][0] == 1 || dp[i - 1][0] == 0 ? 0 : 1;
    }
    for (int j = 1; j < m; j++) {
        dp[0][j] = obstacleGrid[0][j] == 1 || dp[0][j - 1] == 0 ? 0 : 1;
    }
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if (obstacleGrid[i][j] == 1) {
                dp[i][j] = 0;
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }
    return dp[n - 1][m - 1];
}
```
  - code
```Java
public int uniquePath(int[][] A) {
  int m = A.length;
  if (m == 0) {
    return 0;
  }
  n = A[0].length;
  if (n == 0) {
    return 0;
  }
  if (A[0][0] == 1 || A[m - 1][n - 1] == 1) {
    return 0;
  }
  int[][] f = new int[m][n];
  int i, j;
  for (i = 0; i < m; i++) {
    for (j = 0; j < n; j++) {
      if (A[i][j] == 1) { // obstacle
        f[i][j] = 0;
        continue;
      }
      if (i == 0 && j == 0) { // init
        f[i][j] = 1;
        continue;
      }
      f[i][j] = 0;
      if (i > 0) {
        f[i][j] += f[i - 1][j];
      }
      if (j > 0) {
        f[i][j] += f[i][j - 1];
      }
    }
  }
  return f[m - 1][n - 1];
}
```

- Paint house
  - n houses, paint as one of red, blue, green
  - 任何相邻房子不能paint成相同颜色
  - 第i个house染成红色蓝色绿色的cost分别是cost[i, 0], cost[i, 1], cost[i, 2]
  - 最少需要花多少钱油漆这些房子

  - 序列型：前n个
    - 前0个
    - 前1个 0
    - 前2个 0 to 1
    - 前i个 0 to i-1

1. 确定状态
  - 最优策略是花费最小的策略
  - 最后一步：最优策略中房子n-1一定染成了红蓝绿中的一种
    - n-2只能是其他两种
    - 直接套用以前的思路，记前n个房子的最小花费。也需要记录前n-1的最小花费
    - 但是前n-1最优策略中不知道n-2是什么颜色
    - 不知道n-2的颜色，就把它记录下来，加入状态里
    - 分别记录油漆前n-1房子并且房子n-2是红色、蓝色、绿色的最小花费
  - 子问题
  - 状态
    - 油漆前i个房子并且房子i-1是红色蓝色绿色的最小花费分别为f[i, 0], f[i, 1], f[i, 2]
2. 转移方程
  - f[i, 0] = min { f[i-1, 1] + cost[i-1, 0], f[i-1, 2] + cost[i-1, 0]}
    - 油漆前i个房子并且房子index=i-1是红色的最小花费
  - f[i, 1] = ...
  - f[i, 2] = ...
3. 初始条件和边界情况
  - 初始条件 f[0, 0] = f[0, 1] = f[0, 2] = 0
  - 无边界情况
4. 计算顺序
  - 初始化
  - f[1, 0] f[1, 1] f[1, 2]...
  - f[n, 0] f[n, 1] f[n, 2]...
  - min {f[n, 0], f[n, 1], f[n, 2]}
  - O(n) time O(n) space

```Java
public int minCost(int[][] C) {
  int n = C.length;
  if (n == 0) {
    return 0;
  }
  int[][] f = new int[n + 1][3];
  f[0][0] = f[0][1] = f[0][2] = 0;
  int i, j, k;
  // first i houses
  for (i = 1; i <= n; i++) {
    // house i - 1's color is j
    for (j = 0; j < 3; j++) {
      f[i][j] = Integer.MAX_VALUE;

      // house i - 2's color is k
      for (k = 0; k < 3; k++) {
        if (j == k) {
          continue;
        }
        f[i][j] = Math.min(f[i][j], f[i - 1][k] + C[i - 1][j]);
      }
    }
  }
  return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
}
```

- summary
  - 序列型：前i个。。。最小/方式数/可行性
  - 序列+状态

- decode ways
  - A-Z组成的字母串被加密成数字串
  - A->1 B->2...
  - 给定加密后的数字串S[0...N-1] 多少种方式解密成字母串

- 划分型

1. 确定状态
  - 划分
  - 最后一步（最后一段）：对应一个字母
    - 加法原理
  - 子问题
    - 前n个 -> 前n-1或者n-2
  - 状态：S前i个数字解密成字母串有f[i]种方式
2. 转移方程
  - f[i] = f[i-1]|S[i-1]对应一个字母 + f[i-2]|S[i-2]S[i-1]对应一个字母
3. 初始条件和边界情况
  - 初始条件
    - f[0] = 1 空串
  - 边界情况
    - i=1 只看最后一个数字
4. 计算顺序
  - f[0] -> f[n]
  - f[n]
  - O(n) time O(n) space
```Java
public int numDecodings(String s) {
  int n = s.length();
  if (n == 0) {
    return 0;
  }
  int[] f = new int[n + 1];
  int i, t;
  f[0] = 1; // init
  for (i = 1; i <= n; i++) {
    f[i] = 0;
    if (s.charAt(i - 1) != '0') {
      f[i] += f[i - 1];
    }
    if (i >= 2 && ((s.charAt(i - 2) == '1') || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
      f[i] += f[i - 2];
    }
  }
  return f[n];
}
```

- 坐标型动态规划
  - 最简单dp
  - 给定序列或者网格
  - 初始条件f[0]是以a0为结尾的子序列的性质

- minimum path sum
  - m * n grid, (i, j)有非负数a[i, j]
  - (0, 0) -> 右下角 使和最小

```Java
public int minPathSum(int[][] A) {
  int m = A.length;
  if (m == 0) {
    return 0;
  }
  int n = A[0].length;
  if (n == 0) {
    return 0;
  }
  int[][] f = new int[m][n];
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i == 0 && j == 0) {
        f[i][j] = A[i][j];
        continue;
      }
      f[i][j] = Integer.MAX_VALUE;
      if (i > 0) {
        f[i][j] = Math.min(f[i][j], f[i - 1][j] + A[i][j]);
      }
      if (j > 0) {
        f[i][j] = Math.min(f[i][j], f[i][j - 1] + A[i][j]);
      }
    }
  }
  return f[m - 1][n - 1];
}
```

- 空间优化
  - 滚动数组 rolling array O(min(m, n)) space

```Java
  public int minPathSum(int[][] A) {
    int m = A.length;
    if (m == 0) {
      return 0;
    }
    int n = A[0].length;
    if (n == 0) {
      return 0;
    }
    int[][] f = new int[2][n];

    int now = 0, old;
    for (int i = 0; i < m; i++) {
      old = now;
      now = 1 - now;
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          f[now][j] = A[i][j];
          continue;
        }

        f[now][j] = Integer.MAX_VALUE;
        if (i > 0) {
          f[now][j] = Math.min(f[now][j], f[old][j] + A[i][j]);
        }
        if (j > 0) {
          f[now][j] = Math.min(f[now][j], f[now][j - 1] + A[i][j]);
        }
      }
    }
    return f[now][n - 1];
  }
```
- bomb enemy
  - m * n grid，每个格子可能是空的，可能有一个敌人，可能有一堵墙
  - 只能在某个空格子放一个炸弹，炸弹会炸死所有同行同列的敌人，但是不能穿墙
  - 最多能炸死几个敌人

  - 枚举
    - O(mn*n) time
  - dp
    - (i, j)放一个炸弹，向上能炸死的敌人数是
      - (i, j)为空地：(i-1, j)格向上能炸死的敌人数
      - (i, j)为敌人：(i-1, j) + 1
      - (i, j)为wall: 0
    - up[i, j]=(i, j)放一个炸弹向上能炸死的敌人数
      - up[i-1, j] 0
      - up[i-1, j] + 1 E
      - 0 wall
    - 初始条件
      - up[0, j] = 0 if (0, j) is not E
      - up[0, j] = 1 if (0, j) is E
    - O(nm) time

```Java
public int maxKilledEnemies(char[][] A) {
  if (A == null || A.length == 0 || A[0].length == 0) {
    return 0;
  }
  int m = A.length;
  int n = A[0].length;
  int[][] up = new int[m][n];
  int[][] down = new int[m][n];
  int[][] left = new int[m][n];
  int[][] right = new int[m][n];
  // up
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      up[i][j] = 0;
      if (A[i][j] != 'W') {
        if (A[i][j] == 'E') {
          up[i][j] = 1;
        }
        if (i > 0) {
          up[i][j] += up[i - 1][j];
        }
      }
    }
  }

  // down
  for (int i = m - 1; i >= 0; i--) {
    for (int j = 0; j < n; j++) {
      down[i][j] = 0;
      if (A[i][j] != 'W') {
        if (A[i][j] == 'E') {
          down[i][j] = 1;
        }
        if (i < m - 1) {
          down[i][j] += down[i + 1][j];
        }
      }
    }
  }

  // left
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      left[i][j] = 0;
      if (A[i][j] != 'W') {
        if (A[i][j] == 'E') {
          left[i][j] = 1;
        }
        if (j > 0) {
          left[i][j] += left[i][j - 1];
        }
      }
    }
  }

  // right
  for (int i = 0; i < m; i++) {
    for (int j = n - 1; j >= 0; j--) {
      right[i][j] = 0;
      if (A[i][j] != 'W') {
        if (A[i][j] == 'E') {
          right[i][j] = 1;
        }

        if (j < n - 1) {
          right[i][j] += right[i][j + 1];
        }
      }
    }
  }

  int res = 0;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (A[i][j] == '0') {
        res = Math.max(res, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
      }
    }
  }
  return res;
}
```

- 位操作型动态规划
  - 位操作（二进制）
  - & | ^ !

- counting bits
  - 给定n，要求输出0, 1, ..., N的每个数的二进制表示里的1的个数

  - 子问题
    - 要求n的二进制表示中有多少1
    - 在n的二进制去掉最后一位n mod 2，设新的数是y=(n>>1)
    - 要知道y的二进制表示中有多少1
  - f[i] - i的二进制表示中有多少个1
  - f[i] = f[i>>1] + (i mod 2)

```Java
public int[] countBits(int n) {
  int[] f = new int[n + 1];
  f[0] = 0;
  for (int i = 0; i <= n; i++) {
    f[i] = f[i >> 1] + i % 2;
  }
  return f;
}
```

## HW
- longest increasing continuous subsequence
