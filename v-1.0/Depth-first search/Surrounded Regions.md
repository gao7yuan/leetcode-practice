# 130. Surrounded Regions
12/27/18
*Medium*

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:
```
X X X X
X O O X
X X O X
X O X X
```
After running your function, the board should be:
```
X X X X
X X X X
X X X X
X O X X
```
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

## Solutions
* DFS
  - find O's on boarder and DFS to set all connected to 1
  - O(r*c) time, O(r*c) space from recursion stack
```Java
public void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) {
        return;
    }
    int row = board.length;
    int col = board[0].length;
    for (int i = 0; i < col; i++) {
        if (board[0][i] == 'O') {
            dfs(board, 0, i);
        }
        if (board[row - 1][i] == 'O') {
            dfs(board, row - 1, i);
        }
    }
    for (int i = 0; i < row; i++) {
        if (board[i][0] == 'O') {
            dfs(board, i, 0);
        }
        if (board[i][col - 1] == 'O') {
            dfs(board, i, col - 1);
        }
    }

    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (board[i][j] == 'O') {
                board[i][j] = 'X';
            }
        }
    }
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (board[i][j] == '1') {
                board[i][j] = 'O';
            }
        }
    }
}

private void dfs(char[][] board, int r, int c) {
    board[r][c] = '1';
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int i = 0; i < 4; i++) {
        int newR = r + dir[i][0];
        int newC = c + dir[i][1];
        if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length
            && board[newR][newC] == 'O') {
            dfs(board, newR, newC);
        }
    }
}
```
