# 695. Max Area of Island
12/22/18
*Medium*

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
```
Example 2:
```
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
```
Note: The length of each dimension in the given grid does not exceed 50.

## Attempts
* DFS (or BFS)
  - O(r*c) time, O(r*c) space (call stack)
```Java
public int maxAreaOfIsland(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int max = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                max = Math.max(max, dfs(grid, i, j, 0));
            }
        }
    }
    return max;
}

// dfs starting from a source, change all 1's to 0's, return number of 1's
private int dfs(int[][] grid, int row, int column, int area) {
    grid[row][column] = 0;
    area++;
    if (row - 1 >= 0 && grid[row - 1][column] == 1) {
        area = dfs(grid, row - 1, column, area);
    }
    if (row + 1 < grid.length && grid[row + 1][column] == 1) {
        area = dfs(grid, row + 1, column, area);
    }
    if (column - 1 >= 0 && grid[row][column - 1] == 1) {
        area = dfs(grid, row, column - 1, area);
    }
    if (column + 1 < grid[0].length && grid[row][column + 1] == 1) {
        area = dfs(grid, row, column + 1, area);
    }
    return area;
}
```

* DFS Iterative
```Java
private int[][] grid;

public int maxAreaOfIsland(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int max = 0;
    this.grid = grid;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (this.grid[i][j] == 1) {
                max = Math.max(max, dfs(i, j));
            }
        }
    }
    return max;
}

// given the source to start dfs, calculate the area of island
private int dfs(int row, int column) {
    int area = 0;
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[]{row, column});
    this.grid[row][column] = 0;
    while (!stack.isEmpty()) {
        int[] index = stack.pop();
        int r = index[0];
        int c = index[1];
        area++;
        if (r - 1 >= 0 && this.grid[r - 1][c] == 1) {
            stack.push(new int[]{r - 1, c});
            this.grid[r - 1][c] = 0;
        }
        if (r + 1 < this.grid.length && this.grid[r + 1][c] == 1) {
            stack.push(new int[]{r + 1, c});
            this.grid[r + 1][c] = 0;
        }
        if (c - 1 >= 0 && this.grid[r][c - 1] == 1) {
            stack.push(new int[]{r, c - 1});
            this.grid[r][c - 1] = 0;
        }
        if (c + 1 < this.grid[0].length && this.grid[r][c + 1] == 1) {
            stack.push(new int[]{r, c + 1});
            this.grid[r][c + 1] = 0;
        }
    }
    return area;
}
```

## Solutions
* DFS (recursion with better code)
```Java
private int[][] grid;

public int maxAreaOfIsland(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int max = 0;
    this.grid = grid;
    for (int i = 0; i < this.grid.length; i++) {
        for (int j = 0; j < this.grid[0].length; j++) {
            if (grid[i][j] == 1) {
                max = Math.max(max, dfs(i, j));
            }
        }
    }
    return max;
}

// dfs starting from a source, change all 1's to 0's, return number of 1's
private int dfs(int row, int column) {
    if (row < 0 || row >= this.grid.length || column < 0 || column >= this.grid[0].length
        || this.grid[row][column] == 0) {
        return 0;
    }
    this.grid[row][column] = 0;
    return (1 + dfs(row - 1, column) + dfs(row + 1, column)
           + dfs(row, column - 1) + dfs(row, column + 1));
}
```
