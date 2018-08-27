# 387. First Unique Character in a String
08/27/18

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:
```
s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
```
Note: You may assume the string contain only lowercase letters.

## Attempts
* Use a map to keep track of the occurrence of each char in the string, and iterate the string in the order of index to find the first one with one occurrence.
  - O(n) time. O(n) space.
```
public int firstUniqChar(String s) {
    Map<Character, Integer> charMap = new HashMap<>();
    for (char c : s.toCharArray()) {
        if (charMap.containsKey(c)) {
            charMap.put(c, charMap.get(c) + 1);
        } else {
            charMap.put(c, 1);
        }
    }
    for (int i = 0; i < s.length(); i++) {
        if (charMap.get(s.charAt(i)).equals(1)) {
            return i;
        }
    }
    return -1;
}
```

## Solutions
* When dealing with strings / characters, can use arrays with indices as unicode...
  - Better to use an array of size 256 to store other characters.
```
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
```
