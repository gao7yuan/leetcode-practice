# 463. Island Perimeter
*Easy*
10/29/18

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:
```
Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16
```

## Attempts
* 总1数 * 4 - 共用边数 * 2
  - 实际上为了避免重复，可以只检查右边和下边是否也是1
```
public int islandPerimeter(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    // find number of 1's
    int total = 0;
    int edge = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                total++;
                if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                    edge++;
                }
                if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                    edge++;
                }
            }
        }
    }
    return total * 4 - edge * 2;
}
```
