# 332. Reconstruct Itinerary
12/27/18
*Medium*

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
```
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
```
Example 2:
```
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
```

## Attempts
* DFS
  - Use a Map of String - List<String> pairs to record departure - destinations.
  - Need lexical order and need to record visited destinations starting from one departure - use PriorityQueue

## Solutions
### 1. Brute force
1. convert to graph, HashMap<String, List<String>>
2. DFS from "JFK", remove visited edges, add back when backtracking
- O(n!) time, O(n) space
```Java
public List<String> findItinerary(List<List<String>> tickets) {
    List<String> res = new LinkedList<>();
    if (tickets == null || tickets.size() == 0) {
        return res;
    }
    int len = tickets.size() + 1;
    // construct the graph as adjacency list
    // origin: String. dest: list of Strings
    Map<String, List<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
        if (!graph.containsKey(ticket.get(0))) {
            graph.put(ticket.get(0), new ArrayList<>());
        }
        graph.get(ticket.get(0)).add(ticket.get(1));
    }

    for (List<String> edges : graph.values()) {
        Collections.sort(edges);
    }

    // dfs from JFK
    String start = "JFK";
    res.add(start);
    if (dfs(start, res, graph, len)) {
        return res;
    }
    return new LinkedList<String>();
}

boolean dfs(String start, List<String> path, Map<String, List<String>> graph, int len) {
    if (path.size() == len) {
        return true;
    }
    if (!graph.containsKey(start)) {
        return false;
    }
    List<String> destinations = graph.get(start);
    for (int i = 0; i < destinations.size(); i++) {
        String dest = destinations.get(i);
        destinations.remove(i);
        path.add(dest);
        if (dfs(dest, path, graph, len)) {
            return true;
        }
        destinations.add(i, dest);
        path.remove(path.size() - 1);
    }
    return false;
}
```

### 2. Eulerian path
- for a finite graph, each node visited exactly once
- For a directed graph, there exists an Eulerian path *iff*
  1. connected
  2. one of below
    - in degree == out degree
    - exactly one node in degree == out degree - 1 (start), and exactly one node in degree == out degree + 1 (end), others in degree == out degree
- algorithms
  1. Fleury
  2. Hierholzer
    - pseudocode
    ```go
    path = []
    DFS(G, u)
      for (v in G.neighbors(u))
        visited[v] = T
        DFS(G, v)
      path.pushLeft(u)  
    ```
- first trial
```Java
public List<String> findItinerary(List<List<String>> tickets) {
    LinkedList<String> res = new LinkedList<>();
    if (tickets == null || tickets.size() == 0) {
        return res;
    }
    Map<String, List<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
        if (!graph.containsKey(ticket.get(0))) {
            graph.put(ticket.get(0), new ArrayList<>());
        }
        graph.get(ticket.get(0)).add(ticket.get(1));
    }
    for (List<String> edge : graph.values()) {
        Collections.sort(edge, (s1, s2) -> s1.compareTo(s2));
    }
    //Map<String, Integer> unvisited = new HashMap<>();

    dfs(graph, "JFK", res);
    return res;
}

void dfs(Map<String, List<String>> graph, String start, LinkedList<String> list) {
    if (!graph.containsKey(start) || graph.get(start).size() == 0) {
        list.addFirst(start);
        return;
    }
    List<String> dests = graph.get(start);
    for (int i = 0; i < dests.size(); i++) {
        String dest = dests.get(i);
        dests.remove(0);
        dfs(graph, dest, list);
    }
    list.addFirst(start);
}
```

- there's problem in traversing the list. use a priority queue
```Java
public List<String> findItinerary(List<List<String>> tickets) {
    LinkedList<String> res = new LinkedList<>();
    if (tickets == null || tickets.size() == 0) {
        return res;
    }
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
        if (!graph.containsKey(ticket.get(0))) {
            graph.put(ticket.get(0), new PriorityQueue<>());
        }
        graph.get(ticket.get(0)).offer(ticket.get(1));
    }

    dfs(graph, "JFK", res);
    return res;
}

void dfs(Map<String, PriorityQueue<String>> graph, String start, LinkedList<String> list) {
    PriorityQueue<String> dests = graph.get(start);
    while (dests != null && !dests.isEmpty()) {
        String dest = dests.poll();
        dfs(graph, dest, list);
    }
    list.addFirst(start);
}
```
