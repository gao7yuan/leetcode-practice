# 79. Word Search
3/26/19
*Medium*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

## Attempts
```Java
public boolean exist(char[][] board, String word) {
    if (board.length == 0 || board[0].length == 0) {
        return false;
    }
    int n = board.length;
    int m = board[0].length;
    // each cell as the start of a word
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (search(board, i, j, word, 0)) {
                return true;
            }
        }
    }
    return false;
}

private boolean search(char[][] board, int i, int j, String word, int index) {
    int n = board.length;
    int m = board[0].length;
    // out of boundary
    if (i < 0 || i >= n || j < 0 || j >= m) {
        return false;
    }
    // char is different
    if (board[i][j] != word.charAt(index)) {
        return false;
    }
    // if last char
    if (index == word.length() - 1) {
        return true;
    }
    // mark as visited
    char c = board[i][j];
    board[i][j] = 0;
    boolean found = search(board, i - 1, j, word, index + 1)
        || search(board, i + 1, j, word, index + 1)
        || search(board, i, j - 1, word, index + 1)
        || search(board, i, j + 1, word, index + 1);
    board[i][j] = c;
    return found;
}
```
or
```Java
boolean[][] visited;

public boolean exist(char[][] board, String word) {
    if (board.length == 0 || board[0].length == 0) {
        return false;
    }
    int n = board.length;
    int m = board[0].length;
    visited = new boolean[n][m];

    // each cell as the start of a word
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (search(board, i, j, word, 0)) {
                return true;
            }
        }
    }
    return false;
}

private boolean search(char[][] board, int i, int j, String word, int index) {
    int n = board.length;
    int m = board[0].length;
    // out of boundary
    if (i < 0 || i >= n || j < 0 || j >= m) {
        return false;
    }
    // visited or char is different
    if (visited[i][j] || board[i][j] != word.charAt(index)) {
        return false;
    }
    // if last char
    if (index == word.length() - 1) {
        return true;
    }
    visited[i][j] = true;
    boolean found = search(board, i - 1, j, word, index + 1)
        || search(board, i + 1, j, word, index + 1)
        || search(board, i, j - 1, word, index + 1)
        || search(board, i, j + 1, word, index + 1);
    visited[i][j] = false;
    return found;
}
```
  - Time complexity
    - search: O(4^l), l = length of word
    - total: O(n*m*4^l)
  - Space complexity
    - DFS = recursion, space complexity = depth of recursion = l
    - n * m from graph
    - O(n*m + l)  
