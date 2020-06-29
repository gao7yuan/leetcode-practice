# 36. Valid Sudoku
11/29/18
*Medium*

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

## Attempts
* O(n^2) time, O(n) space in each outer for loop iteration
```Java
public boolean isValidSudoku(char[][] board) {
    // rows
    for (int i = 0; i < 9; i++) {
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (board[i][j] != '.') {
                int num = Character.valueOf(board[i][j]);
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
        }
    }
    // cols
    for (int i = 0; i < 9; i++) {
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (board[j][i] != '.') {
                int num = Character.valueOf(board[j][i]);
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
        }
    }

    // matrix
    for (int i = 0; i <= 6; i +=3) {
        for (int j = 0; j <= 6; j +=3) {
            Set<Integer> set = new HashSet<>();
            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++) {
                    char c = board[i + m][j + n];
                    if (c != '.') {
                        int num = Character.valueOf(c);
                        if (set.contains(num)) {
                            return false;
                        }
                        set.add(num);
                    }
                }
            }
        }
    }

    return true;
}
```

## Solutions
* very hacking method:
```Java
public boolean isValidSudoku(char[][] board) {
    Set seen = new HashSet();
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;
}
```
* regular method???
```Java
public boolean isValidSudoku(char[][] board) {
    for(int i = 0; i<9; i++){
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Character> columns = new HashSet<Character>();
        HashSet<Character> cube = new HashSet<Character>();
        for (int j = 0; j < 9; j++){
            if(board[i][j]!='.' && !rows.add(board[i][j]))
                return false;
            if(board[j][i]!='.' && !columns.add(board[j][i]))
                return false;
            int RowIndex = 3*(i/3);
            int ColIndex = 3*(i%3);
            if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                return false;
        }
    }
    return true;
}
```
