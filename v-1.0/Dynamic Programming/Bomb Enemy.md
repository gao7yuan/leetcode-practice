# 361. Bomb Enemy
3/7/19
*Medium*

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:
```
Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3
Explanation: For the given grid,

0 E 0 0
E 0 W E
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
```

## Solutions
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
