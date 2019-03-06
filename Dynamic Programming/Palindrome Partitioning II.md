# 132. Palindrome Partitioning II
3/6/19
*Hard*

## NOTE
- 不知道如何优化空间

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:
```
Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
```

## Solution
- understand palindrome
  - `boolean[][] isPalindrome`
  - isPalindrome[j,i] = true if s.charAt(j) == s.charAt(i) && isPalindrome[j+1,i-1] (note boundary condition when j+1>i-1)
- cut[i]=min(cut[j-1]+1 (j<=i)) if [j, i] is palindrome (note boundary condition when j == 0)
- O(n^2) time, O(n^2) space

```Java
public int minCut(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int n = s.length();
    int[] cuts = new int[n]; // number of cuts from 0..i-1
    boolean[][] isPal = new boolean[n][n]; // (j, i) is palindrome j <= i
    for (int i = 0; i < n; i++) {
        int min = i; // init min, cut between every char
        for (int j = 0; j <= i; j++) {
            if (s.charAt(j) == s.charAt(i) && (j + 1 > i - 1 || isPal[j + 1][i - 1])) {
                isPal[j][i] = true;
                min = j == 0 ? 0 : Math.min(min, cuts[j - 1] + 1);
            }
        }
        cuts[i] = min;
    }
    return cuts[n - 1];
}
```
