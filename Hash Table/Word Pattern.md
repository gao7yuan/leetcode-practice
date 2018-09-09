# 290. Word Pattern
*Easy*

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

**Example 1:**
```
Input: pattern = "abba", str = "dog cat cat dog"
Output: true
```
**Example 2:**
```
Input:pattern = "abba", str = "dog cat cat fish"
Output: false
```
**Example 3:**
```
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
```
**Example 4:**
```
Input: pattern = "abba", str = "dog dog dog dog"
Output: false
```
**Notes:**
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

## Attempts
* similar as ```Isomorphic Strings```
* Note: ```str.split(" ")```
```
public boolean wordPattern(String pattern, String str) {
    Map<Character, Integer> patternMap = new HashMap<>();
    Map<String, Integer> strMap = new HashMap<>();

    String[] strArr = str.split(" ");
    int patternLen = pattern.length();
    int strLen = strArr.length;

    if (patternLen != strLen) {
        return false;
    }

    for (int i = 0; i < patternLen; i++) {
        if (patternMap.containsKey(pattern.charAt(i)) ^ strMap.containsKey(strArr[i])) {
            return false;
        }
        if (patternMap.containsKey(pattern.charAt(i))) {
            if (!patternMap.get(pattern.charAt(i)).equals(strMap.get(strArr[i]))) {
                return false;
            }
        } else {
            patternMap.put(pattern.charAt(i), i);
            strMap.put(strArr[i], i);
        }
    }
    return true;
}
```

## Solutions
* Similar thoughts but neater using just one map.
* Note: in Java, put(key, val) for HashMap returns val.
???
