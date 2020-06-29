# 524. Longest Word in Dictionary through Deleting
2/15/19
*Medium*

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
```
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
```
Example 2:
```
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
```
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

## Attempts
- sort list first, according to length and lexicographical order.
- two pointers, notice boundary condition
- Time complexity:
  - sort: O(nlgn)
  - two pointers: each string O(n) (n is string length). worst case visit all strings O(n*m), m is number of strings in d
- Space complexity:
  - O(1)

```Java
public String findLongestWord(String s, List<String> d) {

    Collections.sort(d, new Comparator<String>() {
        public int compare(String a, String b) {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        }
    });

    for (String str : d) {
        int i = 0, j = 0;
        while (i < str.length() && j < s.length()) {
            if (str.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i == str.length()) {
            return str;
        }
    }
    return "";
}
```

- without sorting
  - O(n*m) time, n = number of strings, m = string length, O(m) space
```Java
public String findLongestWord(String s, List<String> d) {

    String res = "";

    for (String str : d) {
        int i = 0, j = 0;
        while (i < str.length() && j < s.length()) {
            if (str.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i == str.length()
            && (str.length() > res.length()
                || str.length() == res.length()
                && str.compareTo(res) < 0)) {
            res = str;
        }
    }
    return res;
}
```

## Solutions
### 1. sort and check sequence
- same as my solution but code style FYI.
```Java
public class Solution {
    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
    public String findLongestWord(String s, List < String > d) {
        Collections.sort(d, new Comparator < String > () {
            public int compare(String s1, String s2) {
                return s2.length() != s1.length() ? s2.length() - s1.length() : s1.compareTo(s2);
            }
        });
        for (String str: d) {
            if (isSubsequence(str, s))
                return str;
        }
        return "";
    }
}
```

### 2. without sorting
- directly find sequences, and update if the new one is longer or smaller in lexicographical order
  - O(n * m) time, O(m) space. m is string size
