# 289. Game of Life
3/4/19
*Medium*

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:
```
Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
```
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

## Attempts
- use extra space
  - O(mn) time, O(mn) space

```Java
public void gameOfLife(int[][] board) {
    if (board.length == 0 || board[0].length == 0) {
        return;
    }  
    int m = board.length;
    int n = board[0].length;
    int[][] update = new int[m][n];

    int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
    int[] dc = {0, -1, 0, 1, -1, 1, -1, 1};

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int neighbours = 0;
            for (int k = 0; k < 8; k++) {
                int r = i + dr[k];
                int c = j + dc[k];
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    if (board[r][c] == 1) {
                        neighbours++;
                    }
                }
                if (neighbours > 3) {
                    break;
                }
            }
            if (board[i][j] == 1) {
                if (neighbours < 2 || neighbours > 3) {
                    update[i][j] = 0;
                } else {
                    update[i][j] = 1;
                }
            } else {
                if (neighbours == 3) {
                    update[i][j] = 1;
                } else {
                    update[i][j] = 0;
                }
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            board[i][j] = update[i][j];
        }
    }
}
```

- in place: use 2 to mark 1 turning to 0, use -1 to mark 0 turning to 1
  - O(mn) time, two pass, O(1) space

```Java
public void gameOfLife(int[][] board) {
    if (board.length == 0 || board[0].length == 0) {
        return;
    }  
    int m = board.length;
    int n = board[0].length;

    int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
    int[] dc = {0, -1, 0, 1, -1, 1, -1, 1};

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int neighbours = 0;
            for (int k = 0; k < 8; k++) {
                int r = i + dr[k];
                int c = j + dc[k];
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    if (board[r][c] == 1 || board[r][c] == 2) {
                        neighbours++;
                    }
                }
                if (neighbours > 3) {
                    break;
                }
            }
            if (board[i][j] == 1 && (neighbours < 2 || neighbours > 3)) {
                board[i][j] = 2; // originally 1, updated to 0, marked as 2
            }
            if (board[i][j] == 0 && neighbours == 3){
                board[i][j] = -1;
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 2) {
                board[i][j] = 0;
            } else if (board[i][j] == -1) {
                board[i][j] = 1;
            }          
        }
    }
}
```
