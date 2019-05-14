# 947. Most Stones Removed with Same Row or Column
5/13/19
*Medium*

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:
```
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
```
Example 2:
```
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
```
Example 3:
```
Input: stones = [[0,0]]
Output: 0
```


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000

## Solutions
### 1. DFS, Connected components
- for each connected components, we have component.size - 1 moves (think of a spanning tree of the component, we can always remove others and leave one)
- 1 stone belongs to only one component, removing 1 stone doesn't affect other components
- DFS to find all connected components
- Time: O(n^2)
- Space: O(n^2)

```Java
class Solution {
    public int removeStones(int[][] stones) {
        int N = stones.length;

        // graph[i][0] = the length of the 'list' graph[i][1:]
        // in each row stores all stones that are connected
        // adjacency matrix
        int[][] graph = new int[N][N];
        // for each stone
        for (int i = 0; i < N; ++i)
            // for each other stone
            for (int j = i+1; j < N; ++j)
                // for stone pair (i, j), if their row or col are the same, they belong to one component
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    graph[i][++graph[i][0]] = j;
                    graph[j][++graph[j][0]] = i;
                }

        int ans = 0;
        boolean[] seen = new boolean[N];
        // for each unvisited stone, do DFS
        for (int i = 0; i < N; ++i) if (!seen[i]) {
            Stack<Integer> stack = new Stack();
            stack.push(i);
            seen[i] = true;
            ans--;
            while (!stack.isEmpty()) {
                int node = stack.pop();
                ans++;
                // add all unvisited neighbors
                for (int k = 1; k <= graph[node][0]; ++k) {
                    int nei = graph[node][k];
                    if (!seen[nei]) {
                        stack.push(nei);
                        seen[nei] = true;
                    }
                }
            }
        }

        return ans;
    }
}
```

### 2. Union Find
```Java
class Solution {
    public int removeStones(int[][] stones) {
        int N = stones.length;
        // range of stone position [1, 10000]
        DSU dsu = new DSU(20000);

        // for each stone, union its row with column
        for (int[] stone: stones)
            dsu.union(stone[0], stone[1] + 10000);

        // for each connected component, only one left
        Set<Integer> seen = new HashSet();
        for (int[] stone: stones)
            seen.add(dsu.find(stone[0]));

        return N - seen.size();
    }
}

class DSU {
    int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    // did not use rank union
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```
