# 212. Word Search II
5/13/19
*Hard*

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:
```
Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
```

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.

## Attempts
- DFS, Backtracking
  - Time: can be exponential?
```Java
public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    if (board == null || board.length == 0 || board[0].length == 0) {
        return res;
    }
    Set<String> resSet = new HashSet<>();
    Set<String> wordSet = new HashSet<>();
    int row = board.length;
    int col = board[0].length;
    boolean[][] visited = new boolean[row][col]; // initial: false
    for (String word : words) {
        wordSet.add(word);
    }
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            dfs(board, resSet, wordSet, "", i, j, visited);
        }
    }
    for (String resWord : resSet) {
        res.add(resWord);
    }
    return res;
}

// soFar: String built before (i, j)
void dfs(char[][] board, Set<String> res, Set<String> words, String soFar, int i, int j, boolean[][] visited) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
        return;
    }
    // add (i, j) to string and mark visited
    visited[i][j] = true;
    soFar = soFar + board[i][j];
    if (words.contains(soFar)) {
        res.add(new String(soFar));
    }
    dfs(board, res, words, soFar, i - 1, j, visited);
    dfs(board, res, words, soFar, i + 1, j, visited);
    dfs(board, res, words, soFar, i, j - 1, visited);
    dfs(board, res, words, soFar, i, j + 1, visited);
    visited[i][j] = false;
    soFar = soFar.substring(0, soFar.length() - 1);
}
```

- how to optimize run time? if the prefix doesn't exist in the dictionary, we shouldn't go to the next char any more. use a trie

## Solutions

- Time
  - construct k trie: k*O(l)
  - traverse every char on board: O(m*n), DFS(avg depth: l), search Trie O(l)
  - total: O(kl) + O(nml)

```Java
public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    TrieNode root = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            dfs (board, i, j, root, res);
        }
    }
    return res;
}

public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
    char c = board[i][j];
    if (c == '#' || p.next[c - 'a'] == null) return;
    p = p.next[c - 'a'];
    if (p.word != null) {   // found one
        res.add(p.word);
        p.word = null;     // de-duplicate
    }

    board[i][j] = '#';
    if (i > 0) dfs(board, i - 1, j ,p, res);
    if (j > 0) dfs(board, i, j - 1, p, res);
    if (i < board.length - 1) dfs(board, i + 1, j, p, res);
    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
    board[i][j] = c;
}

public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String w : words) {
        TrieNode p = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (p.next[i] == null) p.next[i] = new TrieNode();
            p = p.next[i];
       }
       p.word = w;
    }
    return root;
}

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
}
```
- 我又写了一遍
```Java
public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    if (board == null || board.length == 0 || board[0].length == 0) {
        return res;
    }
    TrieNode dict = buildTrie(words);
    int row = board.length;
    int col = board[0].length;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            dfs(board, dict, i, j, res);
        }
    }
    return res;
}

void dfs(char[][] board, TrieNode cur, int i, int j, List<String> res) {
    // if out of boundary or visited
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
        return;
    }
    char c = board[i][j];
    int index = c - 'a';
    // if not in dict
    if (cur.next[index] == null) {
        return;
    }
    // mark as visited
    board[i][j] = '#';
    // if so far exists in dict, add to res
    if (cur.next[index].word != null) {
        res.add(cur.next[index].word);
        cur.next[index].word = null;
    }
    dfs(board, cur.next[index], i - 1, j, res);
    dfs(board, cur.next[index], i + 1, j, res);
    dfs(board, cur.next[index], i, j - 1, res);
    dfs(board, cur.next[index], i, j + 1, res);
    board[i][j] = c;
}

TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String word : words) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (p.next[i] == null) {
                p.next[i] = new TrieNode();
            }
            p = p.next[i];
        }
        p.word = word;
    }
    return root;
}

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
}
```
