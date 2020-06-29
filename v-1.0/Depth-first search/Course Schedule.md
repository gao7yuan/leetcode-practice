# 207. Course Schedule
12/27/18
*Medium*

## Notes
* Topological sort
* indegree: 3->2, talking about 2. outdegree: 3->2, talking about 3
* A node with 0 indegree is a *source*

* Arrays of ArrayList
  - Cannot create arrays of parameterized types
  1. `ArrayList[] graph = new ArrayList[numCourses];`, then `for (Integer nei : (ArrayList<Integer>)graph[course])`
  2. or create ArrayList of ArrayList

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
```
Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
```
Example 2:
```
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
```
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

## Attempts
* Equivalent to checking cycles in a digraph

## Solutions
* Detecting cycles in a digraph
* BFS and DFS solve it using topological sort
### 1. BFS
* Use indegree of a node.
  - Find the node with 0 indegree.
  - If fail to do so, there must be a cycle.
  - Set the indegree of the source to -1 to avoid visiting again.
  - Reduce the indegree of its neighbors by 1 and repeat this process.

```Java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    // make graph as adjacency lists
    ArrayList[] graph = new ArrayList[numCourses];
    int[] degrees = new int[numCourses];
    // initialize graph as adjacency lists
    for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList();
    }
    // traverse the whole prerequisites array, fill graph with values
    // degree: number of courses that requires this course
    for (int i = 0; i < prerequisites.length; i++) {
        graph[prerequisites[i][0]].add(prerequisites[i][1]);
        degrees[prerequisites[i][1]]++; // 不理解 把0 1 反过来也行
    }
    Queue queue = new LinkedList();
    int count = 0;
    for (int i = 0; i < numCourses; i++) {
        if (degrees[i] == 0) {
            queue.add(i);
            count++;
        }
    }
    while (!queue.isEmpty()) {
        int course = (int)queue.remove();
        for (Integer nei : (ArrayList<Integer>)graph[course]) {
            degrees[nei]--;
            if (degrees[nei] == 0) {
                queue.add(nei);
                count++;
            }
        }
    }
    return count == numCourses;
}
```
### 2. DFS
* In the DFS process, if it meets a node that is in the current DFS process, a cycle exists.
* Make two records:
  1. all the visited nodes
  2. visited nodes in the current DFS process
* >= O(E+V) time, DFS for each vertex, O(E+V) space to store neighbors
```Java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    // make graph as adjacency lists
    ArrayList[] graph = new ArrayList[numCourses];
    // initialize graph as adjacency lists
    for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList<Integer>();
        // this <Integer> is no use because compile time type is still ArrayList of Object
    }
    // traverse the whole prerequisites array, fill graph with values
    // define edge of digraph u->v as u is a prereq of v, therefore [v, u] in prereq
    for (int i = 0; i < prerequisites.length; i++) {
        graph[prerequisites[i][1]].add(prerequisites[i][0]);
    }
    boolean[] visited = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
        if (!dfs(graph, visited, i)) {
            return false;
        }
    }
    return true;
}

private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
    if (visited[course]) {
        return false;
    } else {
        visited[course] = true;
    }
    for (Integer nei : (ArrayList<Integer>)graph[course]) {
        if (!dfs(graph, visited, nei)) {
            return false;
        }
    }
    visited[course] = false;
    return true;
}
```
* Optimized code, avoid repeated DFS on a node that has been visited using `boolean[] allVisited`
```Java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    // make graph as adjacency lists
    ArrayList[] graph = new ArrayList[numCourses];
    // initialize graph as adjacency lists
    for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList<Integer>();
    }
    // traverse the whole prerequisites array, fill graph with values
    // define edge of digraph u->v as u is a prereq of v, therefore [v, u] in prereq
    for (int i = 0; i < prerequisites.length; i++) {
        graph[prerequisites[i][1]].add(prerequisites[i][0]);
    }
    boolean[] visited = new boolean[numCourses];
    boolean[] allVisited = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
        if (!dfs(graph, allVisited, visited, i)) {
            return false;
        }
    }
    return true;
}

private boolean dfs(ArrayList[] graph, boolean[] allVisited, boolean[] visited, int course) {
    if (visited[course]) {
        return false;
    } else {
        visited[course] = true;
    }
    if (allVisited[course]) {
        visited[course] = false;
        return true;
    } else {
        allVisited[course] = true;
    }
    for (Integer nei : (ArrayList<Integer>)graph[course]) {
        if (!dfs(graph, allVisited, visited, nei)) {
            return false;
        }
    }
    visited[course] = false;
    return true;
}
```
