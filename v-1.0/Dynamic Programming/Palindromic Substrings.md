# 647. Palindromic Substrings
3/6/19
*Medium*

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
```
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
```

Example 2:
```
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
```

## Attempts
- dp[][] = substring(j, i) is palindrome
  - O(n^2) time, O(n^2) space
```Java
public int countSubstrings(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || dp[j + 1][i - 1])) {
                dp[j][i] = true;
                count++;
            }
        }
    }
    return count;    
}
```

## Solutions
### 1. expand around center
- n: length of string. middle can be at 2n-1 positions
- for each middle, expand. condition: left >= 0 && right < n && s.charAt(left) == s.charAt(right)
- O(n^2) time, O(1) space
```Java
public int countSubstrings(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int count = 0;
    int n = s.length();
    for (int center = 0; center < 2 * n; center++) {
        int left = center / 2;
        int right = left + center % 2;
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
    return count;
}
```
### 2. Manacher's Algorithm
- loop invariant: `center`, `right` - palindrome with largest right-most boundary with center < i, centered at `center` and right boundary as `right`. i > `center`, already computed Z[j]'s for j < i
- when i < right...
- 看不下去了
