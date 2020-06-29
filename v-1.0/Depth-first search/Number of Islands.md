# 200. Number of Islands
*Medium*
10/16/18

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
```
Input:
11110
11010
11000
00000

Output: 1
```
Example 2:
```
Input:
11000
11000
00100
00011

Output: 3
```

## Attempts after reading solutions
Idea: BFS or DFS, linear scan the grid, when meet a '1' that triggers a BFS or DFS. Do BFS or DFS with it and remember to turn each node into '0'.
* DFS
  - O(mn) time because need to visit each node
  - O(mn) space if all are filled with 1
```
public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int num = 0;

    for (int r = 0; r < nr; r++) {
        for (int c = 0; c < nc; c++) {
            if (grid[r][c] == '1') {
                num++;
                dfs(grid, r, c);
            }
        }
    }

    return num;
}

void dfs(char[][] grid, int r, int c) {
    if (grid.length == 0 || grid[0].length == 0) {
        return;
    }

    int nr = grid.length;
    int nc = grid[0].length;

    if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
        return;
    }

    grid[r][c] = '0';

    dfs(grid, r - 1, c);
    dfs(grid, r + 1, c);
    dfs(grid, r, c - 1);
    dfs(grid, r, c + 1);
}
```

* BFS
  - O(mn) time - visit each node
  - O(min(m,n)) space - if all filled with 1
```
public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int nr = grid.length;
    int nc = grid[0].length;
    int num = 0;
    for (int r = 0; r < nr; r++) {
        for (int c = 0; c < nc; c++) {
            if (grid[r][c] == '1') {
                num++;
                grid[r][c] = '0';
                Queue<Integer> neighbours = new LinkedList<>();
                neighbours.add(r * nc + c);
                while (!neighbours.isEmpty()) {
                    int index = neighbours.remove();
                    int row = index / nc;
                    int col = index % nc;
                    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                        neighbours.add((row - 1) * nc + col);
                        grid[row - 1][col] = '0';
                    }
                    if (row + 1 < nr && grid[row + 1][col] == '1') {
                        neighbours.add((row + 1) * nc + col);
                        grid[row + 1][col] = '0';
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                        neighbours.add(row * nc + col - 1);
                        grid[row][col - 1] = '0';
                    }
                    if (col + 1 < nc && grid[row][col + 1] == '1') {
                        neighbours.add(row * nc + col + 1);
                        grid[row][col + 1] = '0';
                    }
                }
            }
        }
    }
    return num;
}
```
