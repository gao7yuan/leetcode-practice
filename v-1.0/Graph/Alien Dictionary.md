# 269. Alien Dictionary
5/30/19
*Hard* *Topological Sort*

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:
```
Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
```
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

## Attempts (Wrong Answer)
- Used topological sort, but wrong graph
```Java
public String alienOrder(String[] words) {
    if (words == null || words.length == 0) {
        return "";
    }
    List<Character> list = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    for (int i = 0; i < words.length; i++) {
        for (int j = 0; j < words[i].length(); j++) {
            dfs(words, i, j, visited, list);
        }
    }
    StringBuilder str = new StringBuilder();
    for (Character c : list) {
        str.append(c);
    }
    return str.toString();
}

void dfs(String[] words, int i, int j, Set<String> visited, List<Character> list) {
    if (i >= words.length || j >= words[i].length() || visited.contains(i + "," + j)) {
        return;
    }
    char c = words[i].charAt(j);
    visited.add(i + "," + j);
    dfs(words, i + 1, j, visited, list);
    dfs(words, i, j + 1, visited, list);
    list.add(0, c);
}
```

## Solutions
- Topological sort
  - A topological ordering is possible if and only if the graph has no directed cycles.
  - build a graph and DFS
    - visited[i] = -1 -> not even exist
    - visited[i] = 0 -> not visited
    - visited[i] = 1 -> visiting
    - visited[i] = 2 -> visited
  - indeed very tricky. see comments for details
- my code
```Java
public String alienOrder(String[] words) {
    // init visited, all not exist
    int[] visited = new int[26];
    for (int i = 0; i < 26; i++) {
        visited[i] = -1; // -1 = not even exist
    }
    // init graph, all false
    boolean[][] graph = new boolean[26][26];
    buildGraph(words, graph, visited);
    StringBuilder str = new StringBuilder();
    // dfs_all
    for (int i = 0; i < 26; i++) {
        if (visited[i] == 0) {
            if (!dfs(graph, visited, str, i)) {
                return "";
            }
        }
    }
    return str.reverse().toString();
}

// find neis, if visiting we have a cycle. otherwise dfs. in the end append to str.

boolean dfs(boolean[][] graph, int[] visited, StringBuilder str, int i) {
    visited[i] = 1; // 1 = visiting
    // find neighbors
    for (int j = 0; j < 26; j++) {
        if (graph[i][j]) {
            if (visited[j] == 1) {
                return false; // cycle
            }
            if (visited[j] == 0) {
                if (!dfs(graph, visited, str, j)) {
                    return false;
                }
            }
        }
    }
    str.append((char)(i + 'a'));
    visited[i] = 2;
    return true;
}

// build a directed graph which represents relationships between characters.
// c1 -> c2: graph[c1-'a'][c2-'a'] = true. means c1 is before c2.

void buildGraph(String[] words, boolean[][] graph, int[] visited) {
    for (int i = 0; i < words.length; i++) {
        // mark each character as exist but not visited
        for (char c : words[i].toCharArray()) {
            visited[c - 'a'] = 0; // 0 = not visited
        }
        if (i == 0) {
            continue;
        }
        // build graph based on relationship with previous word
        String pre = words[i - 1];
        String cur = words[i];
        int len = Math.min(pre.length(), cur.length());
        for (int j = 0; j < len; j++) {
            char prec = pre.charAt(j);
            char curc = cur.charAt(j);
            // important! only need to find the FIRST character that is different to build the graph!
            if (prec != curc) {
                graph[prec - 'a'][curc - 'a'] = true;
                break;
            }
        }
    }
}
```
