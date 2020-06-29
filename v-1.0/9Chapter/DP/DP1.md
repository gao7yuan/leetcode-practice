# DP1

## 常见动态规划类型
- 坐标型动态规划（本节课） 20% *
- 序列型动态规划（前i个） 20% *
- 划分型 20% *
- 区间型 15% *
- 背包型 10%
- 最长序列型 5%
- 博弈型 5%
- 综合型 5%

- 时间空间优化 follow up
  - 滚动数组 二维->一维
- 打印路径

- 什么是动态规划

- 矩阵网络，机器人从左上角出发，每次可以向下或者向右走一步
A. 多少种方式走到右下角 - 动态规划
B. 输出所有走到右下角的路径 - 递归

- 动态规划题目特点
1. 计数
  - 多少种方式走到右下角
  - 多少种方法选出k个数使得和是sum
2. 求最大最小值 （少数用贪心算法）
  - 从左上角走到右下角路径的最大数字和
  - 最长上升子序列长度
3. 求存在性
  - 取石子游戏，先手是否必胜
  - 能不能选出k个数使得和是sum

# Problems

- Coin change
  - 三种硬币，2元，5元，7元，每种足够多
  - 买一本书需要27元
  - 如何用最少硬币组合正好付清，不需要对方找钱
  - 求最大最小值

1. 确定状态
- 开一个数组，数组的每个元素f[i]或者f[i, j]代表什么
- 确定状态需要两个意识
  - 最后一步
    - 最优策略肯定是k枚硬币a1, a2, ..., ak加起来是27
    - 所以一定有最后一枚硬币ak
    - 除掉这枚硬币前面硬币面值加起来是27-ak
      - 确定前面的硬币拼出了27-ak
      - 拼出27-ak的硬币数一定最少
  - 子问题
    - 转化成规模更小的一个子问题：27-ak
    - 设状态f(x)=最少用多少枚硬币拼出x
    - f(27) = min{f(27-2)+1, f(27-5)+1, f(27-7)+1}
- Recursion
```Java
int f(int x) { // f(x) = 最少用多少枚硬币拼出x
  if (x == 0) return 0; // 0元钱只要0枚硬币
  int res = Integer.MAX_VALUE; // 初始化用无穷大
  if (x >= 2) { // f(1) = MAX_VALUE
    res = Math.min(f(x - 2) + 1, res);
  }
  if (x >= 5) {
    res = Math.min(f(x - 5) + 1, res);
  }
  if (x >= 7) {
    res = Math.min(f(x - 7) + 1, res);
  }
  return res;
}
```
- Recursion的问题
  - 重复计算效率低下
  - 保存计算结果，并改变顺序

2. 转移方程
- f[x]=最少用多少枚硬币拼出x
- 对于任何x
  - f[x] = min{f[x-2]+1, f[x-5]+1, f[x-7]+1}

3. 初始条件和边界清开
- x-2, x-5, x-7小于0怎么办？什么时候停下来？
- 边界条件：如果不能拼出y，就定义f[y] = MAX_VALUE
- 初始条件f[0] = 0 (不能直接用转移方程算出来，但可以定义)

4. 计算顺序
- 初始条件：f[0] = 0
- 然后计算f[1], f[2], ...
- 当我们计算到f[x]时，f[x-2], f[x-5], f[x-7]都已经得到计算结果了

```Java
public int coinChange(int[] coins, int amount) {
  int[] dp = new int[amount + 1];
  int n = coins.length;

  // init
  dp[0] = 0;
  int i, j;

  for (i = 1; i <= amount; i++) {
    dp[i] = Integer.MAX_VALUE;
    for (j = 0; j < n; j++) {
      if (i - coin[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
      }
    }
  }
  if (dp[amount] == Integer.MAX_VALUE) {
    return -1;
  }
  return dp[amount];
}
```


- Unique paths
  - m x n grid, robot starts from (0, 0) -> 右下角。每次向下或者向右走一步。多少种走法
  - 计数型

1. 确定状态
  - 最后一步
    - (m-1, n-1) -> (m-1, n-2) or (m-2, n-1)
  - 子问题
    - x ways to reach (m-2, n-1), y ways to reach (m-1, n-2), then x+y ways to reach (m-1, n-1)
    - 加法原理：无重复，无遗漏
    - 状态：f[i, j]=机器人多少方式从左上角走到(i, j)
2. 转移方程
  - f[i, j] = f[i-1, j] + f[i, j-1]
3. 初始条件和边界情况
  - 初始条件：f[0, 0] = 1
  - 边界情况：i = 0 or j = 0，前一步只能有一个方向过来 -> f[i, j] = 1
4. 计算顺序
  - 按行从左到右

```Java
public int uniquePaths(int m, int n) {
  int[][] f = new int[m][n];
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i == 0 || j == 0) {
        f[i][j] = 1;
        continue;
      }
      f[i][j] = f[i - 1][j] + f[i][j - 1];
    }
  }
  return f[m - 1][n - 1];
}
```


- Jump game
  - n块石头在x轴0, 1, ..., n-1位置
  - 一只青蛙在石头0，想跳到石头n-1
  - 如果青蛙在第i块石头上，最多可以右跳距离ai
  - 青蛙是否能跳到石头n-1

1. 确定状态
  - 最后一步
    - i < n-1 ->
    - 青蛙可以跳到i
    - n-1-i <= ai
  - 子问题
    - f[j]=青蛙能不能跳到石头j
2. 转移方程
  - f[j] = OR_0<=i<j(f[i] AND i+a[i]>=j)
  - 枚举上一格跳到的石头i
3. 初始条件和边界情况
  - 初始条件 f[0] = true
  - 边界情况 没有
4. 计算顺序 从小到大

```Java
public boolean canJump(int[] A) {
  int n = A.length;
  boolean[] f = new boolean[n];

  f[0] = true;
  for (int j = 1; j < n; j++) {
    f[j] = false;

    for (int i = 0; i < j; i++) {
      if (f[i] && i + A[i] >= j) {
        f[j] = true;
        break;
      }
    }
  }
  return f[n - 1];
}
```

## HW
- maximum product subarray
  - find the contiguous subarray within an array (containing at least one number) which has the parges product
  - [2, 3, -2, 4] 连续乘积 [2, 3] => 6
