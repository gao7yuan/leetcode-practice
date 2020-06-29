# 126. Word Ladder II
5/3/19
*Hard*

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:
```
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
```
Example 2:
```
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
```

## Solutions
- BFS to find shortest distance between start and end, distance of each node from start, and each node's next neighbor.
- DFS to output all paths with same distance as the shortest distance

```Java
public List<List<String>> findLadders(String start, String end, List<String> wordList) {
  // dictionary
  HashSet<String> dict = new HashSet<String>(wordList);
  // result
  List<List<String>> res = new ArrayList<List<String>>();
  // key: word. value: neighbors (with one letter change)
  HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();
  // key: word. value: distance from start node
  HashMap<String, Integer> distance = new HashMap<String, Integer>();

  ArrayList<String> solution = new ArrayList<String>();

  dict.add(start);
  bfs(start, end, dict, nodeNeighbors, distance);
  dfs(start, end, dict, nodeNeighbors, distance, solution, res);
  return res;
}

// BFS: Trace every node's distance from the start node (level by level).
private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
  for (String str : dict)
      nodeNeighbors.put(str, new ArrayList<String>());

  Queue<String> queue = new LinkedList<String>();
  queue.offer(start);
  distance.put(start, 0);

  while (!queue.isEmpty()) {
      // to visit each level
      int count = queue.size();
      boolean foundEnd = false;
      for (int i = 0; i < count; i++) {
          String cur = queue.poll();
          int curDistance = distance.get(cur);
          ArrayList<String> neighbors = getNeighbors(cur, dict);

          for (String neighbor : neighbors) {
              nodeNeighbors.get(cur).add(neighbor);
              if (!distance.containsKey(neighbor)) {// Check if visited
                  distance.put(neighbor, curDistance + 1);
                  if (end.equals(neighbor))// Found the shortest path
                      foundEnd = true;
                  else
                      queue.offer(neighbor);
                  }
              }
          }

          if (foundEnd)
              break;
      }
  }

// Find all next level nodes.    
private ArrayList<String> getNeighbors(String node, Set<String> dict) {
  ArrayList<String> res = new ArrayList<String>();
  char chs[] = node.toCharArray();

  for (char ch ='a'; ch <= 'z'; ch++) {
      for (int i = 0; i < chs.length; i++) {
          if (chs[i] == ch) continue;
          char old_ch = chs[i];
          chs[i] = ch;
          if (dict.contains(String.valueOf(chs))) {
              res.add(String.valueOf(chs));
          }
          chs[i] = old_ch;
      }

  }
  return res;
}

// DFS: output all paths with the shortest distance.
private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
    solution.add(cur);
    if (end.equals(cur)) {
       res.add(new ArrayList<String>(solution));
    } else {
       for (String next : nodeNeighbors.get(cur)) {
            if (distance.get(next) == distance.get(cur) + 1) {
                 dfs(next, end, dict, nodeNeighbors, distance, solution, res);
            }
        }
    }
   solution.remove(solution.size() - 1);
}
```

- my code
```Java
public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    // dictionary set
    Set<String> dictionary = new HashSet<>(wordList);
    dictionary.add(beginWord);
    // node and next neighbors
    Map<String, List<String>> neighbors = new HashMap<>();
    // each nodes distance from source
    Map<String, Integer> distance = new HashMap<>();
    // result list
    List<List<String>> res = new ArrayList<>();
    // temp list to store one res
    List<String> temp = new ArrayList<>();

    // BFS to fill neighbors, distance
    bfs(beginWord, endWord, dictionary, neighbors, distance);
    // DFS to fill res
    dfs(beginWord, endWord, neighbors, distance, temp, res);

    return res;
}

// cur: the node we have just done processing
void dfs(String cur, String end, Map<String, List<String>> neighbors, Map<String, Integer> distance, List<String> temp, List<List<String>> res) {
    temp.add(cur);
    if (cur.equals(end)) {
        res.add(new ArrayList<>(temp));
    } else {
        if (neighbors.containsKey(cur)) {
            List<String> neis = neighbors.get(cur);
            for (String nei : neis) {
                if (distance.get(nei) == distance.get(cur) + 1) {
                    dfs(nei, end, neighbors, distance, temp, res);
                }
            }
        }
    }

    temp.remove(temp.size() - 1);
}

void bfs(String start, String end, Set<String> dictionary, Map<String, List<String>> neighbors, Map<String, Integer> distance) {
    Queue<String> queue = new LinkedList<>();
    // init neighbors with empty lists
    for (String word : dictionary) {
        neighbors.put(word, new ArrayList<>());
    }
    queue.offer(start);
    distance.put(start, 0);
    while (!queue.isEmpty()) {
        // do it level by level
        int cnt = queue.size();
        boolean found = false;
        for (int i = 0; i < cnt; i++) {
            String word = queue.poll();
            // find all its neighbors and add to list
            List<String> neis = findNeighbors(word, dictionary);
            int dist = distance.get(word);

            for (String nei : neis) {
                neighbors.get(word).add(nei);
                // if distance does not have it, it is the shortest distance
                if (!distance.containsKey(nei)) {
                    distance.put(nei, dist + 1);
                    if (nei.equals(end)) {
                        found = true;
                    } else {
                        queue.add(nei);
                    }
                }

            }
            if (found) {
                break;
            }
        }

    }
}

// given a word and a dictionary, find all its next neighbors
List<String> findNeighbors(String word, Set<String> dict) {
    List<String> res = new ArrayList<>();
    int len = word.length();
    for (int i = 0; i < len; i++) {
        for (char j = 'a'; j <= 'z'; j++) {
            char[] ch = word.toCharArray();
            if (ch[i] == j) {
                continue;
            }
            ch[i] = j;
            String newWord = new String(ch);
            if (dict.contains(newWord)) {
                res.add(newWord);
            }
        }
    }
    return res;
}
```

- 5/21/19
```Java
Map<String, List<String>> graph;
public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    int n = beginWord.length();
    makeGraph(n, wordList);
    Map<String, Integer> distance = new HashMap<>();
    int ladderlen = ladderLength(beginWord, endWord, graph, distance);
    List<List<String>> res = new ArrayList<>();
    if (ladderlen == 0) {
        return res;
    }
    List<String> temp = new ArrayList<>();
    dfs(beginWord, endWord, graph, temp, res, distance);
    return res;
}

void dfs(String cur, String endWord, Map<String, List<String>> graph, List<String> temp, List<List<String>> res, Map<String, Integer> distance) {
    temp.add(cur);
    if (cur.equals(endWord)) {
        res.add(new ArrayList<>(temp));
    } else {
        for (int i = 0; i < endWord.length(); i++) {
            String generic = cur.substring(0, i) + "*" + cur.substring(i + 1, endWord.length());
            if (graph.containsKey(generic)) {
                List<String> neis = graph.get(generic);
                for (String nei : neis) {
                    if (distance.containsKey(nei) && distance.get(nei) == distance.get(cur) + 1) {
                        // shortest distance
                        dfs(nei, endWord, graph, temp, res, distance);
                    }
                }
            }
        }
    }
    temp.remove(temp.size() - 1);
}

void makeGraph(int n, List<String> wordList) {
    graph = new HashMap<>();
    for (String word : wordList) {
        for (int i = 0; i < n; i++) {
            String generic = word.substring(0, i) + "*" + word.substring(i + 1, n);
            if (!graph.containsKey(generic)) {
                graph.put(generic, new ArrayList<>());
            }
            graph.get(generic).add(word);
        }
    }
}

int ladderLength(String beginWord, String endWord, Map<String, List<String>> graph, Map<String, Integer> distance) {
    int n = beginWord.length();
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.offer(beginWord);
    visited.add(beginWord);
    distance.put(beginWord, 1);
    while (!queue.isEmpty()) {
        String word = queue.poll();
        int dist = distance.get(word);
        for (int i = 0; i < n; i++) {
            String generic = word.substring(0, i) + "*" + word.substring(i + 1, n);
            if (graph.containsKey(generic)) {
                List<String> neis = graph.get(generic);
                for (String nei : neis) {
                    if (!visited.contains(nei)) {
                        queue.offer(nei);
                        visited.add(nei);
                        distance.put(nei, dist + 1);
                    }
                    if (endWord.equals(nei)) {
                        return dist + 1;
                    }
                }
            }
        }
    }
    return 0;
}
```
