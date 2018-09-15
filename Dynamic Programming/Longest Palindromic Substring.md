# 5. Longest Palindromic Substring
*Medium*
09/13/18

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```
Example 2:
```
Input: "cbbd"
Output: "bb"
```

## Solutions
* dp
  - 如果一个substring starting from i+1 ending at j-1 of s是palindrome，那么substring starting from i ending at j的条件是在i和j位置上的char相同
  - base case: starting from i, ending at i是palindrome，或者starting from i, ending at i + 1...

  - start记录回文的开头index，maxlen记录总长度
```
private int start, maxlen;
```
```
public String longestPalindrome(String s) {
    if (s.length() < 2) return s;
    for (int i = 0; i < s.length(); i++) {
        expandPalindrome(s, i, i);
        expandPalindrome(s, i, i + 1);
    }
    return s.substring(start, start + maxlen);
}
```
  - 找到最长回文并且更新start和maxlen
```
private void expandPalindrome(String s, int j, int k) {
    while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
        j--;
        k++;
    }
    if (maxlen < k - j - 1) {
        start = j + 1;
        maxlen = k - j - 1;
    }
}
```
