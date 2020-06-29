# 399. Evaluate Division
5/30/19
*Medium* *Graph* *Union Find*

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
```
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
```

According to the example above:
```
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
```

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

## Attempts
- DFS, Backtracking.
  - graph: dominator and divider as one vertex, the other one as the other vertex, division result as cost.
  - result: (dom, div) -> res
  - dfs with all dom and div
  - note: if x not in input, then x / x = -1.0
  - there is redundancy:
    - a->b->c->d
    - b->c->d
```Java
public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    double[] res = new double[queries.size()];
    // from, (to, cost)
    // 其实用Map<String, Map<String, Double>> 就行了嘤嘤嘤
    Map<String, Set<Pair>> graph = new HashMap<>();
    // from, to -> res
    Map<String, Double> visited = new HashMap<>(); // stores pairs like "a,b", 2.0
    // build graph
    for (int i = 0; i < equations.size(); i++) {
        List<String> eq = equations.get(i);
        String from = eq.get(0);
        String to = eq.get(1);
        // from / to = values[i]
        if (!graph.containsKey(from)) {
            graph.put(from, new HashSet<>());
        }
        graph.get(from).add(new Pair(to, values[i]));
        //visited.put(from + "," + from, 1.0);
        // to / from = 1.0 / values[i]
        if (!graph.containsKey(to)) {
            graph.put(to, new HashSet<>());
        }
        graph.get(to).add(new Pair(from, 1.0 / values[i]));
        //visited.put(to + "," + to, 1.0);
    }

    for (List<String> eq : equations) {
        String from = eq.get(0);
        String to = eq.get(1);
        dfs(graph, visited, from, from, 1.0);
        dfs(graph, visited, to, to, 1.0);
    }

    int i = 0;

    for (List<String> query : queries) {
        String from = query.get(0);
        String to = query.get(1);
        if (visited.containsKey(from + "," + to)) {
            res[i] = visited.get(from + "," + to);
        } else if (graph.containsKey(from) && from.equals(to)) {
            res[i] = 1.0;
        } else {
            res[i] = -1.0;
        }
        i++;
    }
    return res;


}

// from is source, mid is what we are looking at

void dfs(Map<String, Set<Pair>> graph, Map<String, Double> visited, String from, String mid, double sofar) {
    if (!graph.containsKey(mid)) {
        return;
    }
    // find all neis and backtrack
    for (Pair pair : graph.get(mid)) {
        String to = pair.var;
        if (visited.containsKey(from + "," + to)) {
            continue;
        }
        double val = pair.val;
        double tmp = sofar;
        sofar *= val; // update sofar
        visited.put(from + "," + to, sofar);
        dfs(graph, visited, from, to, sofar);
        sofar = tmp;
    }
}

// 不需要

class Pair {
    public String var;
    public double val;

    public Pair(String var, double val) {
        this.var = var;
        this.val = val;
    }
}
```


## Solutions
### 1. DFS
```Java
public double[] calcEquation(String[][] eq, double[] vals, String[][] q) {
        // graph: num1 -> num2, cost
        Map<String, Map<String, Double>> m = new HashMap<>();
        for (int i = 0; i < vals.length; i++) {
            m.putIfAbsent(eq[i][0], new HashMap<>());
            m.putIfAbsent(eq[i][1], new HashMap<>());
            m.get(eq[i][0]).put(eq[i][1], vals[i]);
            m.get(eq[i][1]).put(eq[i][0], 1 / vals[i]);
        }
        double[] r = new double[q.length];
        for (int i = 0; i < q.length; i++)
            r[i] = dfs(q[i][0], q[i][1], 1, m, new HashSet<>());
        return r;
    }

    // s: start. t: end. r: sofar.

    double dfs(String s, String t, double r, Map<String, Map<String, Double>> m, Set<String> seen) {
        if (!m.containsKey(s) || !seen.add(s)) return -1;
        if (s.equals(t)) return r;
        Map<String, Double> next = m.get(s);
        for (String c : next.keySet()) {
            double result = dfs(c, t, r * next.get(c), m, seen);
            if (result != -1) return result;
        }
        return -1;
    }
```

### 2. Union Find
```Java
/**
    1. Thoughts
        - check if we have enough info to get the result
        - if yes, calculate; if not, return -1.0
        - Method: union find
            - a/b = 2.0 --> b is the root of a; the distance from a to b is 1/2.0
            - if two nums have the same root, we can get the result; a/b=2.0, b/c=3.0
            index   a   b   c
            root    b   c   c
            dist    2   3   1
            - if we want to know a/c = ?: a = 2 * b = 2 * 3 * c => a/c = 6.0
    2. Corner case
        - if any input is null, return null
        - no enough info, return -1.0
    3. Steps
        - go through equations to union elements with the same root and update root map and distance map
        - go through each query: check if has the same root; find relative dist
*/
class Solution {
    public double[] calcEquation(String[][] e, double[] values, String[][] q) {
        double[] res = new double[q.length];
        Map<String, String> root = new HashMap<>();
        Map<String, Double> dist = new HashMap<>();
        for (int i = 0; i < e.length; i++) {
            String r1 = find(root, dist, e[i][0]);
            String r2 = find(root, dist, e[i][1]);
            root.put(r1, r2);
            dist.put(r1, dist.get(e[i][1]) * values[i] / dist.get(e[i][0]));
        }
        for (int i = 0; i < q.length; i++) {
            if (!root.containsKey(q[i][0]) || !root.containsKey(q[i][1])) {
                res[i] = -1.0;
                continue;
            }
            String r1 = find(root, dist, q[i][0]);
            String r2 = find(root, dist, q[i][1]);
            if (!r1.equals(r2)) {
                res[i] = -1.0;
                continue;
            }
            res[i] = (double) dist.get(q[i][0]) / dist.get(q[i][1]);
        }
        return res;
    }

    private String find(Map<String, String> root, Map<String, Double> dist, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            dist.put(s, 1.0);
            return s;
        }
        if (root.get(s).equals(s)) return s;
        String lastP = root.get(s);
        String p = find(root, dist, lastP);
        root.put(s, p);
        dist.put(s, dist.get(s) * dist.get(lastP));
        return p;
    }
}
```
```Java
public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    Map<String, String> parent = new HashMap<>();  //<node, parent of the node>
    Map<String, Double> ratio = new HashMap<>();   //<node, node / parent>
    for (int i = 0; i < equations.length; i++) {
        union(parent, ratio, equations[i][0], equations[i][1], values[i]);
    }

    double[] res = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
        String s1 = queries[i][0], s2 = queries[i][1];
        if (!parent.containsKey(s1) || !parent.containsKey(s2)
            || !find(parent, ratio, s1).equals(find(parent, ratio, s2))) {
            res[i] = -1.0;
        } else {
            res[i] = ratio.get(s1) / ratio.get(s2);
        }
    }
    return res;
}

private void union(Map<String, String> parent, Map<String, Double> ratio, String s1, String s2, double val) {
        if (!parent.containsKey(s1)) {
            parent.put(s1, s1);
            ratio.put(s1, 1.0);
        }
        if (!parent.containsKey(s2)) {
            parent.put(s2, s2);
            ratio.put(s2, 1.0);
        }
        String p1 = find(parent, ratio, s1);
        String p2 = find(parent, ratio, s2);
        parent.put(p1, p2);
        ratio.put(p1, val * ratio.get(s2) / ratio.get(s1));
}

private String find(Map<String, String> parent, Map<String, Double> ratio, String s) {
    if (s.equals(parent.get(s))) {
        return s;
    }
    String father = parent.get(s);
    String grandpa = find(parent, ratio, father);
    parent.put(s, grandpa);
    ratio.put(s, ratio.get(s) * ratio.get(father));
    return grandpa;
}
```
