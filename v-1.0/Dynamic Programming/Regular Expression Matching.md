# 10. Regular Expression Matching
*Hard* *背答案吧呵呵*
11/6/18

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and `'*'`.

'.' Matches any single character.
`'*'` Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or `*`.
Example 1:
```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```
Example 2:
```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```
Example 3:
```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```
Example 4:
```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```
Example 5:
```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```

## Solutions
### 1. DP
* boolean dp[i][j] -> s[0-j]和p[0-j]是否匹配。长度分别为s.length + 1和p.length + 1
* dp[0][0] = true
1. p.charAt(j) == s.charAt(i) --> 这个位置匹配 --> dp[i][j] = dp[i - 1][j - 1]
2. p.charAt(j) == '.' --> 可以匹配任意在s内相对位置的字符 --> dp[i][j] = dp[i - 1][j - 1]
3. If p.charAt(j) == `*`
  又分为两种情况:
  1. p.charAt(j - 1) != s.charAt(i) --> `*`前面的字符不和当前s中字符相符，将`x*` 当做empty看待 --> dp[i][j] = dp[i][j - 2] // ???
  2. p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.' --> 前面的字符匹配
    1. dp[i][j] = dp[i][j - 1] <-- a* counts as a single a
    2. dp[i][j] = dp[i - 1][j] <-- a* counts as multiple a
    3. dp[i][j] = dp[i][j - 1] <-- a* counts as empty
```
public boolean isMatch(String s, String p) {
    if (s == null && p == null) {
        return false;
    }
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    dp[0][0] = true;
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*') {
            dp[0][i + 1] = dp[0][i - 1];
        }
    }
    for (int i = 0; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == s.charAt(i)) {
                dp[i + 1][j + 1] = dp[i][j];
            }
            if (p.charAt(j) == '.') {
                dp[i + 1][j + 1] = dp[i][j];
            }
            if (p.charAt(j) == '*') {
                if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                    dp[i + 1][j + 1] = dp[i + 1][j - 1];
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
                }
            }
        }
    }
    return dp[s.length()][p.length()];
}
```
### 2. DP
动态规划重点：
* state - 用什么变量存储过程的state
* init
* 转移方程
* return

* dp[][] s.length + 1, p.length + 1
* dp[i][j]: 从s中取前i个字符，从p中取前j个字符，是否匹配
