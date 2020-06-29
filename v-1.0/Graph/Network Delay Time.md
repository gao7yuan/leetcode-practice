# 743. Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

## Solutions
### 1. Dijkstra with array
```Java
class Solution {
    Map<Integer, Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dist.put(K, 0);
        boolean[] seen = new boolean[N+1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            seen[candNode] = true;
            if (graph.containsKey(candNode))
                for (int[] info: graph.get(candNode))
                    dist.put(info[0],
                             Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
        }

        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }
}
```
- my code with comments
```Java
int[] dist;
public int networkDelayTime(int[][] times, int N, int K) {
    // times[i] = (u, v, w) -> times[i][0] = source, times[i][1] = dest, times[i][2] = cost
    // use adjacency list to represent graph
    // key: source, value: list of dests, with dest nodes and costs
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge : times) {
        if (!graph.containsKey(edge[0])) {
            graph.put(edge[0], new ArrayList<>());
        }
        graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
    }

    dist = new int[N + 1];
    for (int i = 1; i <= N; i++) {
        dist[i] = Integer.MAX_VALUE;
    }
    dist[K] = 0;
    boolean[] visited = new boolean[N + 1];

    while(true) {
        int candNode = -1; // node with minimum distance
        int candDist = Integer.MAX_VALUE; // minimum dist of unvisited nodes
        // find node with minumum distance
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && dist[i] < candDist) {
                candNode = i;
                candDist = dist[i];
            }
        }
        // all nodes have been visited
        if (candNode < 0) {
            break;
        }
        // else
        visited[candNode] = true;
        // relax all its neighbors
        if (graph.containsKey(candNode)) {
            // for each (v, w) of u's neighbors
            for (int[] out : graph.get(candNode)) {
                // dist.put(out[0],
                //          Math.min(dist.get(out[0]), dist.get(candNode) + out[1]));
                // relax
                if (dist[out[0]] > dist[candNode] + out[1]) {
                    dist[out[0]] = dist[candNode] + out[1];
                }
            }
        }
    }

    int res = 0;
    for (int i = 1; i <= N; i++) {
        if (dist[i] == Integer.MAX_VALUE) {
            return -1;
        }
        if (dist[i] > res) {
            res = dist[i];
        }
    }
    return res;
}
```

### 2. Dijkstra with heap
```Java
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
}
```
- my code
```Java
public int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge : times) {
        if (!graph.containsKey(edge[0])) {
            graph.put(edge[0], new ArrayList<>());
        }
        graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
    }

    // queue: (dist, node)
    PriorityQueue<int[]> queue = new PriorityQueue<>(
    (out1, out2) -> out1[0] - out2[0]);
    queue.offer(new int[]{0, K});

    Map<Integer, Integer> distance = new HashMap<>();

    while (!queue.isEmpty()) {
        int[] distNode = queue.poll();
        int dist = distNode[0];
        int node = distNode[1];
        if (distance.containsKey(node)) {
            continue;
        }
        distance.put(node, dist);
        if (graph.containsKey(node)) {
            for (int[] out : graph.get(node)) {
                int nei = out[0];
                int cost = out[1];
                // relax
                if (!distance.containsKey(nei)) {
                    queue.offer(new int[]{dist + cost, nei});
                }
            }
        }
    }

    if (distance.size() != N) {
        return -1;
    }
    int res = 0;
    for (int dist : distance.values()) {
        if (res < dist) {
            res = dist;
        }
    }
    return res;
}
```
### 3. DFS
```Java
class Solution {
    Map<Integer, Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
      // construct the graph as an adjacency list
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
        }
        // sort the out vertex of a node with increasing cost
        for (int node: graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
        }
        // init dist with max distance
        dist = new HashMap();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dfs(graph, K, 0);
        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        if (elapsed >= dist.get(node)) return;
        dist.put(node, elapsed);
        if (graph.containsKey(node))
            for (int[] info: graph.get(node))
                dfs(graph, info[1], elapsed + info[0]);
    }
}
```
