# 54. Spiral Matrix
3/4/19
*Medium*

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:
```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
```
Example 2:
```
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```

## Solutions
### 1. Simulation
- simulate the path. if in matrix range or if unvisited, add to list. otherwise turn clockwise.
- `seen[r][c]` to record visited elements. `(r, c)` is current element. `di` direction. next to visit `(cr, cc)`. if in range add, otherwise add the one that turns clockwise. finish when all visited.

- O(nm) time. O(nm) space

```Java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) {
        return res;
    }
    int n = matrix.length;
    int m = matrix[0].length;

    int[][] seen = new int[n][m];
    int di = 0; // 0: right; 1: down; 2: left; 3: up
    int r = 0, c = 0;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    while (res.size() < n * m) {
        res.add(matrix[r][c]);
        seen[r][c] = 1;
        // next to visit
        int cr = r + dr[di];
        int cc = c + dc[di];
        if (cr >= 0 && cc >= 0 && cr < n && cc < m && seen[cr][cc] == 0) {
            r = cr;
            c = cc;
        } else {
            di++;
            di %= 4;
            r += dr[di];
            c += dc[di];
        }
    }
    return res;
}
```

- solution code
```Java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}
```

### 2. Layer by Layer
- top-left corner: (r1, c1). bottom-right corner: (r2, c2)
- for each layer
  - top: (r1, c) c = c1 ... c2
  - right: (r, c2) r = r1 + 1 ... r2
  - bottom: (r2, c) c = c2 - 1 ... c1 + 1
  - left: (r, c1) r = r2 ... r1 + 1
  - for bottom and left have to make sure r1 < r2 && c1 < c2 otherwise not 4 edges.
- O(nm) time O(nm) space to store
```Java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) {
        return res;
    }

    int n = matrix.length;
    int m = matrix[0].length;

    int r1 = 0, c1 = 0, r2 = n - 1, c2 = m - 1;

    while (r1 >= 0 && r1 < n && r2 >= 0 && r2 < n
           && c1 >= 0 && c1 < m && c2 >= 0 && c2 < m
           && r1 <= r2 && c1 <= c2) {
        // top
        for (int c = c1; c <= c2; c++) {
            res.add(matrix[r1][c]);
        }
        for (int r = r1 + 1; r <= r2; r++) {
            res.add(matrix[r][c2]);
        }
        if (r1 < r2 && c1 < c2) {
            for (int c = c2 - 1; c >= c1 + 1; c--) {
              res.add(matrix[r2][c]);
            }
            for (int r = r2; r >= r1 + 1; r--) {
                res.add(matrix[r][c1]);
            }
        }

        r1++;
        c1++;
        r2--;
        c2--;
    }
    return res;
}
```

- solution code
```Java
class Solution {
    public List < Integer > spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}
```
