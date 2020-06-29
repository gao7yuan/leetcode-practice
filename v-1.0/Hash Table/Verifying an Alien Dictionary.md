# 953. Verifying an Alien Dictionary
6/15/19
*Easy*

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.



Example 1:
```
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
```
Example 2:
```
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
```
Example 3:
```
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
```

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

## Attempts
- 建一个hashtable用来记录letter - order
- 依次两两比较word，依次比较char的order。如果index超出长度，order用-1代替
- O(26 + nm) = O(nm) time. n = word数量, m = 一个word的长度
- O(26) = O(1) space for hash map
```Java
public boolean isAlienSorted(String[] words, String order) {
    int[] dict = new int[26];
    for (int i = 0; i < 26; i++) {
        dict[order.charAt(i) - 'a'] = i;
    }
    for (int i = 0; i < words.length - 1; i++) {
        String prev = words[i];
        String next = words[i + 1];
        for (int j = 0; j < Math.max(prev.length(), next.length()); j++) {
            int p = -1, n = -1;
            if (j < prev.length()) {
                p = prev.charAt(j) - 'a';
            }
            if (j < next.length()) {
                n = next.charAt(j) - 'a';
            }
            int p_order = p == -1 ? -1 : dict[p];
            int n_order = n == -1 ? -1 : dict[n];
            if (p_order < n_order) {
                break;
            }
            if (p_order > n_order) {
                return false;
            }
        }
    }
    return true;
}
```
