# 205. Isomorphic Strings
*Easy* *已整理*
08/24/18

Given two strings **s** and **t**, determine if they are isomorphic.

Two strings are isomorphic if the characters in **s** can be replaced to get **t**.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

**Example 1:**
```
Input: s = "egg", t = "add"
Output: true
```
**Example 2:**
```
Input: s = "foo", t = "bar"
Output: false
```
**Example 3:**
```
Input: s = "paper", t = "title"
Output: true
```
**Note:**
You may assume both **s** and **t** have the same length.

## Attempts
* 用两个map存放character和对应的位置，如果character已存在，比较integer大小，如果不存在则加入map。
```
public boolean isIsomorphic(String s, String t) {
    Map<Character, Integer> mapS = new HashMap<>();
    Map<Character, Integer> mapT = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        if (mapS.containsKey(s.charAt(i)) ^ mapT.containsKey(t.charAt(i))) {
            return false;
        }
        if (mapS.containsKey(s.charAt(i))) {
            if (!mapS.get(s.charAt(i)).equals(mapT.get(t.charAt(i)))) {
                return false;
            }
        } else {
            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }
    }
    return true;
}
```

## Solutions
* Use arrays as hashtables
```
public boolean isIsomorphic(String s, String t) {
    int[] a = new int[256];
    int[] b = new int[256];
    for (int i = 0; i < 256; i++) {
        a[i] = b[i] = -1;
    }
    int len = s.length();
    for (int i = 0; i < len; i++) {
        if (a[s.charAt(i)] != b[t.charAt(i)]) {
            return false;
        }
        a[s.charAt(i)] = b[t.charAt(i)] = i;
    }

    return true;


}
```
