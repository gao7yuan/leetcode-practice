# 14. Longest Common Prefix
12/27/18
*Easy*

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
```
Input: ["flower","flow","flight"]
Output: "fl"
```
Example 2:
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```
Note:

All given inputs are in lowercase letters a-z.

## Attempts
* O(S) time, S is sum of all strings in the array. O(1) extra space except for the result.
```Java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
        return "";
    }
    StringBuilder onemore = new StringBuilder();
    StringBuilder res = new StringBuilder();
    int pos = 0;
    while (true) {
        for (int i = 0; i < strs.length; i++) {
            if (pos >= strs[i].length()) {
                return res.toString();
            }
            if (i == 0) {
                onemore.append(strs[i].charAt(pos));
            } else if (strs[i].charAt(pos) != onemore.charAt(pos)) {
                return res.toString();
            }
        }
        res = new StringBuilder(onemore); // deep copy!!
        pos++;
    }
    //return res.toString(); // never reachable!!
}
```

## Solutions
* Another way to do it is to do reduction
```Java
public String longestCommonPrefix(String[] strs) {
   if (strs.length == 0) return "";
   String prefix = strs[0];
   for (int i = 1; i < strs.length; i++)
       while (strs[i].indexOf(prefix) != 0) {
           prefix = prefix.substring(0, prefix.length() - 1);
           if (prefix.isEmpty()) return "";
       }        
   return prefix;
}
```
