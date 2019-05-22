# 417. Pacific Atlantic Water Flow
5/21/19
*Medium*

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

## Attempts
- DFS
```Java
public List<int[]> pacificAtlantic(int[][] matrix) {
    List<int[]> res = new ArrayList<>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return res;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    boolean[][] pacific = new boolean[row][col];
    boolean[][] atlantic = new boolean[row][col];
    for (int i = 0; i < row; i++) {
        dfs(pacific, -1, i, 0, matrix);
        dfs(atlantic, -1, i, col - 1, matrix);
    }
    for (int i = 0; i < col; i++) {
        dfs(pacific, -1, 0, i, matrix);
        dfs(atlantic, -1, row - 1, i, matrix);
    }
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (pacific[i][j] && atlantic[i][j]) {
                res.add(new int[]{i, j});
            }
        }
    }

    return res;
}

int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

void dfs(boolean[][] visited, int height, int i, int j, int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    // out of bound or visited or height too small
    if (i < 0 || j < 0 || i >= row || j >= col || visited[i][j] || matrix[i][j] < height) {
        return;
    }
    visited[i][j] = true;
    for (int k = 0; k < 4; k++) {
        dfs(visited, matrix[i][j], i + dir[k][0], j + dir[k][1], matrix);
    }
}
```
