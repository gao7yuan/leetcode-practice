# 127. Word Ladder
11/28/18
*Medium*

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
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

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
```
Example 2:
```
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
```

## Solutions
* 思路：
  - shortest path问题，BFS。
  - neighbour是只变动了一个字母的存在于dictionary中的单词。
  - 已经visit过的不会再次visit
* BFS方法
  - 一般用queue，这里为了计算层数，可以用一个set (toAdd) 存放每一层的元素，每次结束enqueue完一层的元素之后将层数+1
  - 因为无所谓顺序只需要分层所以可以不用queue，用一个set (visited)即可
  - 用另一个set （unvisited）记录没有被处理的元素
  - 一旦visited里面包括endWord说明已经结束
  - 每层如果toAdd是空说明达不到endWord
* 寻找neighbour的方法
  - 对于一个单词，对于它的每一个字母，将其替换成a-z的字母，如果存在于dictionary则说明是neighbour （注意题目里面说没有重复，所以可以排除替换成原本单词的情况）
  - **`char[] chars = each.toCharArray();` 的位置非常重要，如果在`for (int i = 0; i < strLen; i++) `之前，则每次替换字母之后，没有reset回原来的单词！！**

```Java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // parents of each level of BFS
    Set<String> visited = new HashSet<>();
    // unvisited
    Set<String> unvisited = new HashSet<>(wordList);
    visited.add(beginWord);
    int strLen = beginWord.length();
    int len = 1;
    while (!visited.contains(endWord)) {
        Set<String> toAdd = new HashSet<>();
        // explore each parent in visited
        for (String each : visited) {
            for (int i = 0; i < strLen; i++) {
                char[] chars = each.toCharArray(); // note the position of this line!!!
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String word = new String(chars);
                    if (unvisited.contains(word)) {
                        toAdd.add(word);
                        unvisited.remove(word);
                    }
                }
            }
        }
        len++;
        if (toAdd.size() == 0) {
            return 0;
        }
        visited = toAdd;
    }
    return len;
}
```

```Java
import javafx.util.Pair;

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    // Since all words are of same length.
    int L = beginWord.length();

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();

    wordList.forEach(
        word -> {
          for (int i = 0; i < L; i++) {
            // Key is the generic word
            // Value is a list of words which have the same intermediate generic word.
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            ArrayList<String> transformations =
                allComboDict.getOrDefault(newWord, new ArrayList<String>());
            transformations.add(word);
            allComboDict.put(newWord, transformations);
          }
        });

    // Queue for BFS
    // pair: word, level
    Queue<Pair<String, Integer>> Q = new LinkedList<Pair<String, Integer>>();
    Q.add(new Pair(beginWord, 1));

    // Visited to make sure we don't repeat processing same word.
    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    visited.put(beginWord, true);

    while (!Q.isEmpty()) {
      Pair<String, Integer> node = Q.remove();
      String word = node.getKey();
      int level = node.getValue();
      for (int i = 0; i < L; i++) {

        // Intermediate words for current word
        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

        // Next states are all the words which share the same intermediate state.
        for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
          // If at any point if we find what we are looking for
          // i.e. the end word - we can return with the answer.
          if (adjacentWord.equals(endWord)) {
            return level + 1;
          }
          // Otherwise, add it to the BFS Queue. Also mark it visited
          if (!visited.containsKey(adjacentWord)) {
            visited.put(adjacentWord, true);
            Q.add(new Pair(adjacentWord, level + 1));
          }
        }
      }
    }

    return 0;
  }
}
```

- my code with comment
```Java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (wordList == null || wordList.size() == 0) {
        return 0;
    }
    int len = beginWord.length();
    Map<String, List<String>> dict = new HashMap<>();
    // key: generic word. value: all words that meets generic.
    for (String word : wordList) {
        for (int i = 0; i < len; i++) {
            // take index at i as *
            String generic = word.substring(0, i) + "*" + word.substring(i + 1, len);
            if (!dict.containsKey(generic)) {
                dict.put(generic, new ArrayList<>());
            }
            dict.get(generic).add(word);
        }
    }

    // queue of (word, level)
    Queue<Pair<String, Integer>> queue = new LinkedList<>();
    queue.offer(new Pair<>(beginWord, 1));
    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    while (!queue.isEmpty()) {
        Pair<String, Integer> pair = queue.poll();
        String word = pair.getKey();
        int level = pair.getValue();

        for (int i = 0; i < len; i++) {
            String cand = word.substring(0, i) + "*" + word.substring(i + 1, len);
            if (dict.containsKey(cand)) {
                List<String> adjWords = dict.get(cand);
                for (String adjWord : adjWords) {
                    if (adjWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.contains(adjWord)) {
                        visited.add(adjWord);
                        queue.offer(new Pair<>(adjWord, level + 1));
                    }
                }
            }
        }
    }
    return 0;
}
```
