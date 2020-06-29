# 161. One Edit Distance
7/7/19
*Medium*

Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:
```
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
```
Example 2:
```
Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
```
Example 3:
```
Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
```

## Solutions
1. one pass
- 长度不能相差1以上
- 逐次比较，若遇到不同char，如果长度相等则i+1之后的char都必须相同。若长度相差1则i和i+1之后的char必须相同。
- my code
```Java
public boolean isOneEditDistance(String s, String t) {
    int lens = s.length();
    int lent = t.length();
    if (lens < lent) {
        return isOneEditDistance(t, s);
    }
    if (lens - lent > 1) {
        return false;
    }
    int i = 0, j = 0;
    while (i < lens && j < lent) {
        if (s.charAt(i) != t.charAt(j)) {
            if (lens == lent) {
                i++;
                j++;
            } else {
                // lens > lent
                i++;
            }
            break;
        }
        i++;
        j++;
    }
    if (i == lens && j == lent) {
        if (i == 0 && j == 0 || s.charAt(i - 1) == t.charAt(j - 1)) {
            return false;
        }  
    }
    while (i < lens && j < lent) {
        if (s.charAt(i++) != t.charAt(j++)) {
            return false;
        }
    }
    return true;
}
```
- answer
```Java
class Solution {
  public boolean isOneEditDistance(String s, String t) {
    int ns = s.length();
    int nt = t.length();

    // Ensure that s is shorter than t.
    if (ns > nt)
      return isOneEditDistance(t, s);

    // The strings are NOT one edit away distance  
    // if the length diff is more than 1.
    if (nt - ns > 1)
      return false;

    for (int i = 0; i < ns; i++)
      if (s.charAt(i) != t.charAt(i))
        // if strings have the same length
        if (ns == nt)
          return s.substring(i + 1).equals(t.substring(i + 1));
        // if strings have different lengths
        else
          return s.substring(i).equals(t.substring(i + 1));

    // If there is no diffs on ns distance
    // the strings are one edit away only if
    // t has one more character.
    return (ns + 1 == nt);
  }
}
```
