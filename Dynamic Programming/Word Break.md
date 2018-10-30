# 139. Word Break
*Medium* *没有看BFS方法*
10/16/18

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:
```
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```
Example 2:
```
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
```
Example 3:
```
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
```

## Solutions
### 1. Brute force
Naive approach using recursion and backtracking
* Check every possible prefix in dictionary, if it exists, recursively call the function on the rest of the string
  - 想不通为什么O(n^n) time - 想通了。recursion的时候，每次都会展开成n个case。n是每个subproblem的大小
  - O(n) space - call stack due to recursion
```
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreak(s, new HashSet<String>(wordDict), 0);
}

private boolean wordBreak(String s, Set<String> wordDict, int start) {
    // null
    if (start == s.length()) {
        return true;
    }
    // end is not included
    for (int end = start + 1; end <= s.length(); end++) {
        if (wordDict.contains(s.substring(start, end)) && wordBreak(s, wordDict, end)) {
            return true;
        }
    }
    return false;
}
```

### 2. Recursion with memoization
* O(n^2) time, O(n) space due to memo[]/recursion stack
```
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreak(s, new HashSet<String>(wordDict), 0, new Boolean[s.length()]);
}

// memo records if substring(start, s.length()) is wordbreakable
private boolean wordBreak(String s, Set<String> wordDict, int start, Boolean[] memo) {
    // null
    if (start == s.length()) {
        return true;
    }
    if (memo[start] != null) {
        return memo[start];
    }
    // end is not included
    for (int end = start + 1; end <= s.length(); end++) {
        if (wordDict.contains(s.substring(start, end)) && wordBreak(s, wordDict, end, memo)) {
            memo[start] = true;
            return true;
        }
    }
    memo[start] = false;
    return false;
}
```

### 3. DP
* 从头开始依次推进节点，节点前dp[j]表示substring(0, j)是否符合word break,节点后为后面的substring是否存在于dict里面
  - O(n^2) time: 两个for loop（如果不用set的话，```contains``` takes O(wordDict.size()) time）
  - O(n) space
```
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> set = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && set.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}
```
