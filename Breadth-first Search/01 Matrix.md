# 542. 01 Matrix
12/24/18
*Medium*

## Notes
```Java
int[][] arr = new int[][]{{1, 0}, {0, 1}};
int[][] arr = {{1, 0}, {0, 1}};
```

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1:
```
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
```
Example 2:
```
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
```
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

## Attempts
* for each cell, do BFS
* in the queue, use an array to store `row` `col` and `distance`
* Time complexity: O(r*c) for nested for loops for the matrix, then for each cell do a BFS, worst case O(r*c), so in total could be O((r*c)^2). Space O(r*c) for queue
```Java
public int[][] updateMatrix(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return null;
    }

    int[][] res = new int[matrix.length][matrix[0].length];

    // BFS for each cell
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            res[i][j] = bfs(matrix, i, j);
        }
    }
    return res;
}

private int bfs(int[][] matrix, int r, int c) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{r, c, 0}); // row, column, distance
    while (!queue.isEmpty()) {
        int[] pos = queue.remove();
        int row = pos[0];
        int col = pos[1];
        int distance = pos[2];
        if (matrix[row][col] == 0) {
            return distance;
        }
        if (row - 1 >= 0) {
            queue.add(new int[]{row - 1, col, distance + 1});
        }
        if (row + 1 < matrix.length) {
            queue.add(new int[]{row + 1, col, distance + 1});
        }
        if (col - 1 >= 0) {
            queue.add(new int[]{row, col - 1, distance + 1});
        }
        if (col + 1 < matrix[0].length) {
            queue.add(new int[]{row, col + 1, distance + 1});
        }
    }
    return - 1;
}
```

## Solutions
### 1. BFS
* Above method has redundancy by repeatedly doing BFS to calculate distance
* Better solution:
  - Use a dist[][] to record distance. For matrix[i][j] == 0, dist[i][j] = 0.
  - Set dist[i][j] for matrix[i][j] == 1 INT_MAX
  - Enqueue all the cells with 0 value into queue. Do BFS for each one. Distance of the neighbor of one cell is distance of this cell + 1. If the calculated distance is smaller than original, push the cell into queue and update the distance
  - O(r*c) time, O(r*c) space
```Java
public int[][] updateMatrix(int[][] matrix) {
    int row = matrix.length;
    if (row == 0) {
        return matrix;
    }
    int col = matrix[0].length;
    int[][] dist = new int[row][col];
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (matrix[i][j] == 0) {
                dist[i][j] = 0;
                queue.add(new int[]{i, j});
            } else {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while (!queue.isEmpty()) {
        int[] position = queue.remove();
        int r = position[0];
        int c = position[1];
        for (int i = 0; i < 4; i++) {
            int newR = r + dir[i][0];
            int newC = c + dir[i][1];
            if (newR >= 0 && newC >= 0 && newR < row && newC < col) {
                int newDist = dist[r][c] + 1;
                if (newDist < dist[newR][newC]) {
                    dist[newR][newC] = newDist;
                    queue.add(new int[]{newR, newC});
                }
            }
        }
    }
    return dist;
}
```

### 2. DP
* 从上往下iterate, 确保横向最小
* 从左往右iterate, 确保纵向最小
* O(r*c) time, O(r*c) space
```Java
public int[][] updateMatrix(int[][] matrix) {
    int row = matrix.length;
    if (row == 0) {
        return matrix;
    }
    int col = matrix[0].length;
    int[][] dist = new int[row][col];

    // top left to bottom right
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (matrix[i][j] == 0) {
                dist[i][j] = 0;
            } else {
                dist[i][j] = Integer.MAX_VALUE - 10000; // avoid overflow of int max
                // top
                if (i > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                // left
                if (j > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }
    }
    // bottom right to top left
    for (int i = row - 1; i >= 0; i--) {
        for (int j = col - 1; j >= 0; j--) {
            if (i < row - 1) {
                dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
            }
            if (j < col - 1) {
                dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }
    }
    return dist;
}
```
