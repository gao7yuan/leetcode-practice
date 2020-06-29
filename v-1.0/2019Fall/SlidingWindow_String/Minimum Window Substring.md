# 76. Minimum Window Substring
9/10/19
*Hard*

- my solution using sliding window template
```Java
public String minWindow(String s, String t) {
    if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
        return "";
    }
    int lenS = s.length();
    int lenT = t.length();
    Map<Character, Integer> map = new HashMap<>();
    for (char c : t.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }
    int need = lenT; // need to match this number of characters
    int start = 0, end = 0;
    int min = Integer.MAX_VALUE;
    int minStart = Integer.MAX_VALUE, minEnd = Integer.MAX_VALUE;

    char c;
    while (end < lenS) {
        while (end < lenS && need > 0) {
            c = s.charAt(end); // to add
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1); // update needed c in map
                if (map.get(c) >= 0) {
                    // c was needed
                    need--;
                }
            }
            end++;
        }
        while (need == 0) {
            if (min > end - start) {
                min = end - start;
                minEnd = end;
                minStart = start;
            }
            c = s.charAt(start++); // to remove
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
                if (map.get(c) >= 1) {
                    need++;
                }
            }
        }
    }

    return min == Integer.MAX_VALUE ? "" : s.substring(minStart, minEnd);
}
```
