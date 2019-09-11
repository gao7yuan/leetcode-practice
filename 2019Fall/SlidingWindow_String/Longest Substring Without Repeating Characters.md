# 3. Longest Substring Without Repeating Characters
*Medium* *Sliding Window*
10/21/18

Given a string, find the length of the longest substring without repeating characters.

Example 1:
```
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```
Example 2:
```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```
Example 3:
```
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```
## Attempts
* 类似下面#2的思路
  - O(n^2) time
```Java
public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int max = 0;
    int len = 0;
    Set<Character> letters;
    for (int i = 0; i < s.length(); i++) {
        letters = new HashSet<>();
        len = 0;
        for (int j = i; j < s.length(); j++) {
            char current = s.charAt(j);
            if (!letters.contains(current)) {
                len++;
                letters.add(current);
            } else {
                max = Math.max(max, len);
                break;
            }
        }
        max = Math.max(max, len);
    }
    max = Math.max(max, len);
    return max;
}
```
* 在看了下面#2的code之后略微改变了`max = Math.max(max, len);`的位置
```Java
public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int max = 0;
    int len = 0;
    Set<Character> letters;
    for (int i = 0; i < s.length(); i++) {
        letters = new HashSet<>();
        len = 0;
        for (int j = i; j < s.length(); j++) {
            char current = s.charAt(j);
            if (!letters.contains(current)) {
                len++;
                letters.add(current);
                max = Math.max(max, len);
            } else {
                max = Math.max(max, len);
                break;
            }
        }
    }
    return max;
}
```

## Solutions
### 1. Brute force 超时
* 检查每一个substring内是否有重复
1. 产生所有的substring，(i, j], 0 <= i < j <= n, n是string长度
  - 需要i = 0 to n - 1 iterate
  - j = i + 1 to n iterate
  - O(n^2)
2. 检查每一个substring内部是否有重复，用hashset存放元素，遍历所有char
  - O(n)
* 总共O(n^3) time,O(min(m,n)) space:当所有char都不一样的时候，stack的大小取决于string长度或者字符类型总数的最小值
```Java
public int lengthOfLongestSubstring(String s) {
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
        for (int j = i + 1; j <= s.length(); j++) {
            if (allUnique(s, i, j)) {
                max = Math.max(max, j - i);
            }
        }
    }
    return max;
}

boolean allUnique(String s, int start, int end) {
    Set<Character> letters = new HashSet<>();
    for (int i = start; i < end; i++) {
        char letter = s.charAt(i);
        if (letters.contains(letter)) {
            return false;
        }
        letters.add(letter);
    }
    return true;
}
```
### 2. sliding window
* 不需要重复检查substring是否有重复。如果想检查Sij有没有重复，并且知道Sij-1没有重复，那么只要检查s[j]是否存在于Sij-1中就行了。
  - O(2n) = O(n) time for checking if a char is in a substring
* A sliding window is an abstract concept commonly used in array/string problems.
* By using hashset as a sliding window, reduce to O(1) to check one char
```Java
public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
        // try to extend the range [i, j]
        if (!set.contains(s.charAt(j))){
            set.add(s.charAt(j++));
            ans = Math.max(ans, j - i);
        }
        else {
            set.remove(s.charAt(i++));
        }
    }
    return ans;
}
```
### 3. sliding window optimized
* 用map记录每个char出现的位置，如果已出现则更新i的index。注意i的index是i和上一个j出现index+1之间的较大值。
我的code：
```Java
public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;
    int i = 0, j = 0;
    while (j < s.length()) {
        if (map.containsKey(s.charAt(j))) {
            i = Math.max(map.get(s.charAt(j)) + 1, i);
        }
        map.put(s.charAt(j), j);
        max = Math.max(max, j - i + 1);
        j++;
    }
    return max;
}
```
* 如果知道characters的范围可以用array来取代map
* Commonly used tables are:
  - int[26] for Letters 'a' - 'z' or 'A' - 'Z'
  - int[128] for ASCII
  - int[256] for Extended ASCII
