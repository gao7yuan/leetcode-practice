# 567. Permutation in String
9/10/19
*Medium*

- my solution sliding window
```Java
public boolean checkInclusion(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();
    if (len1 > len2) {
        return false;
    }
    int[] map = new int[26];
    int need = len1;
    for (char c : s1.toCharArray()) {
        map[c - 'a']++;
    }
    int start = 0, end = 0;
    char c;
    // the first len1 chars in s2
    for (; end < len1; end++) {
        c = s2.charAt(end);
        map[c - 'a']--;
        if (map[c - 'a'] >= 0) {
            // c was needed
            need--;
        }
    }
    if (need == 0) {
        return true;
    }
    while (end < len2) {
        c = s2.charAt(start++); // to remove
        map[c - 'a']++;
        if (map[c - 'a'] >= 1) {
            // c is needed
            need++;
        }
        c = s2.charAt(end++);
        map[c - 'a']--;
        if (map[c - 'a'] >= 0) {
            // c was needed
            need--;
        }
        if (need == 0) {
            return true;
        }
    }
    return false;
}
```
