# 547. Friend Circles
12/22/18
*Medium* *Union Find没有看*

There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
```
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
```
Example 2:
```
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

## Attempts
* 开始用了同659一样的DFS，发现不可行。这道题不是找direct neighbor，而是connectivity问题

## Solutions
* DFS
  - O(n^2) time, O(n) space
```Java
public int findCircleNum(int[][] M) {
    int count = 0;
    int[] visited = new int[M.length];
    for (int i = 0; i < M.length; i++) {
        if (visited[i] == 0) {
            count++;
            dfs(M, visited, i);
        }
    }
    return count;
}

// visited length = M.length, record whether each node is visited
// i is the index of node we are looking at
private void dfs(int[][] M, int[] visited, int i) {
    // iterate M[i] and find all nodes that are connected to ith node, dfs them
    for (int j = 0; j < M.length; j++) {
        if (M[i][j] == 1 && visited[j] == 0) {
            visited[j] = 1;
            dfs(M, visited, j);
        }
    }
}
```

* BFS
```Java
public int findCircleNum(int[][] M) {
    int count = 0;
    int[] visited = new int[M.length];
    for (int i = 0; i < M.length; i++) {
        if (visited[i] == 0) {
            count++;
            bfs(M, visited, i);
        }
    }
    return count;
}

private void bfs(int[][] M, int[] visited, int i) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(i);
    visited[i] = 1;
    while (!queue.isEmpty()) {
        int current = queue.remove();
        for (int j = 0; j < M.length; j++) {
            if (visited[j] == 0 && M[current][j] == 1) {
                queue.add(j);
                visited[j] = 1;
            }
        }
    }
}
```
