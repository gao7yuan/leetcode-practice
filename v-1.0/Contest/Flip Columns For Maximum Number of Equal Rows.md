# 1072. Flip Columns For Maximum Number of Equal Rows
6/1/19
*Medium*

Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.



Example 1:
```
Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
```
Example 2:
```
Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
```
Example 3:
```
Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
```

Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1

## Solutions
### 1. Brute force
- 至少是1
- 一行行比较，要么相等，要么flip之后相等
- O(n*n*m) time, O(1) space

```Java
public int maxEqualRowsAfterFlips(int[][] matrix) {
    int res = 1;
    int n = matrix.length;
    int m = matrix[0].length;
    for (int i = 0; i < n; i++) {
        int ct = 1;
        inner:
        for (int j = i + 1; j < n; j++) {
            if (Arrays.equals(matrix[i], matrix[j])) {
                ct++;
            } else {
                for (int k = 0; k < m; k++) {
                    if (matrix[i][k] + matrix[j][k] != 1) {
                        continue inner;
                    }
                }
                ct++;
            }
        }
        res = Math.max(res, ct);
    }
    return res;
}
```

### 2. HashMap
- 用map优化，将pattern和数量存进map
- 原pattern和flip之后的只存一个
- O(nm) time, O(n) space

```Java
public int maxEqualRowsAfterFlips(int[][] matrix) {
    Map<String, Integer> map = new HashMap<>();
    int res = 1;
    int n = matrix.length;
    int m = matrix[0].length;
    for (int i = 0; i < n; i++) {
        StringBuilder pattern1 = new StringBuilder();
        StringBuilder pattern2 = new StringBuilder();
        for (int j = 0; j < m; j++) {
            pattern1.append(matrix[i][j]);
            pattern2.append(1 - matrix[i][j]);
        }
        String p1 = pattern1.toString();
        String p2 = pattern2.toString();
        if (map.containsKey(p2)) {
            map.put(p2, map.get(p2) + 1);
            res = Math.max(res, map.get(p2));
        } else if (map.containsKey(p1)) {
            map.put(p1, map.get(p1) + 1);
            res = Math.max(res, map.get(p1));
        } else {
            map.put(p1, 1);
            res = Math.max(res, map.get(p1));
        }
    }
    return res;
}
```
