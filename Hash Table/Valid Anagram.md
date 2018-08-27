# 242. Valid Anagram
08/26/18

Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
```
Input: s = "anagram", t = "nagaram"
Output: true
```
Example 2:
```
Input: s = "rat", t = "car"
Output: false
```
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

## Attempts
* Use two arrays to keep track of number of occurrences of each character.

```
public boolean isAnagram(String s, String t) {

        int[] sArray = new int[256];
        int[] tArray = new int [256];

        if (s.length() != t.length()) {
            return false;
        }

        for (char c : s.toCharArray()) {
            sArray[c]++;
        }

        for (char c : t.toCharArray()) {
            tArray[c]++;
        }

        for (int i = 0; i < 256; i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }
        return true;
    }
```

## Solutions
* Similar thoughts but used one array.
* O(n) time, O(1) space.

```
public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
```
