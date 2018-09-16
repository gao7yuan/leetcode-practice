# 91. Decode Ways
*Medium*
09/14/18

A message containing letters from A-Z is being encoded to numbers using the following mapping:
```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
```
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
```
Example 2:
```
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

## Attempts
* dp思路
  - int[] dp记录到第k个char decode的方法数。dp[]的长度设置为s.length() + 1，dp[0]为没有decode时的方法数，为1.
  - 到第k+1个char的decode方法数有几种情况：
    - 第k+1个char是0的时候，只能跟第k个char组合成一个字母。这时候如果第k+1个char和第k个char同为0，或者第k+1个char是0，而第k个char大于2，则无法解码。如果没有这个问题，则和第k个char组合解码，方法数为解码到第k-1个char时候的方法数。这是k和k+1必须组合的情况。
    - k+1必须单独解码的情况：如果k和k+1组合起来大于26，或者第k个char是0，方法数为解码到第k个char的方法数
    - 以上情况之外，既可以单独解码又可以和前一个char一起解码，方法数为解码到k-1和解码到k的数目总和。
    - 初始条件：如果第一个char是0则无法解码，否则dp[1]=1
```
public int numDecodings(String s) {
    if (s.charAt(0) == '0') return 0; // 更加严谨的话加上s为空的情况
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i < s.length() + 1; i++) {
        if (s.charAt(i - 1) == '0'
            && (s.charAt(i - 2) == '0' || s.charAt(i - 2) > '2'))
            return 0;
        if (s.charAt(i - 1) == '0') dp[i] = dp[i - 2];
        else if (s.charAt(i - 2) > '2'
                 || s.charAt(i - 2) == '2' && s.charAt(i - 1) > '6'
                 || s.charAt(i - 2) == '0')
            dp[i] = dp[i - 1];
        else dp[i] = dp[i - 2] + dp[i - 1];
    }
    return dp[s.length()];
}
```

## Solutions
* similar but different implementation
```
public int numDecodings(String s) {
    if(s == null || s.length() == 0) {
        return 0;
    }
    int n = s.length();
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = s.charAt(0) != '0' ? 1 : 0;
    for(int i = 2; i <= n; i++) {
        int first = Integer.valueOf(s.substring(i-1, i));
        int second = Integer.valueOf(s.substring(i-2, i));
        if(first >= 1 && first <= 9) {
           dp[i] += dp[i-1];  
        }
        if(second >= 10 && second <= 26) {
            dp[i] += dp[i-2];
        }
    }
    return dp[n];
}
```
