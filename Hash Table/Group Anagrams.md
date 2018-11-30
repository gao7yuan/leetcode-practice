# 49. Group Anagrams
11/29/18
*Medium*

Given an array of strings, group anagrams together.

Example:
```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
Note:

All inputs will be in lowercase.
The order of your output does not matter.

## Attempts
* 用map存储每个anagram的char, int pairs, value存放为list
  - 无法将key设置成一个map
* id-list,id-map，但是这样需要iterate整个id-map来寻找有没有对应的anagram，时间复杂度太高

## Solutions
* 毋庸置疑，map存的value是list
* 问题在于key如何存。有两种存法：
### 1. 存成排序后的word (String)
* sort: klogk (k: max length of a string). for loop: n (n: length of string array)
* O(nklogk) time, O(nk) space
```Java
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
        return new ArrayList();
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
        char[] word = str.toCharArray();
        Arrays.sort(word);
        String key = String.valueOf(word);
        if (!map.containsKey(key)) {
            List<String> list = new ArrayList<>();
            list.add(str);
            map.put(key, list);
        } else {
            map.get(key).add(str);
        }
    }
    return new ArrayList(map.values());
}
```
### 2. 存成每个字母出现的频率。hashtable（此处可以用array因为只有26个字母）-> string
* O(nk) time, O(nk) space
```Java
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
        return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();
    int[] dict = new int[26];
    for (String str : strs) {
        Arrays.fill(dict, 0);
        for (int i = 0; i < str.length(); i++) {
            dict[str.charAt(i) - 'a']++;
        }
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            key.append("#");
            key.append(dict[i]);
        }
        String keyStr = key.toString();
        if (map.containsKey(keyStr)) {
            map.get(keyStr).add(str);
        } else {
            List<String> list = new ArrayList<>();
            list.add(str);
            map.put(keyStr, list);
        }
    }
    return new ArrayList<>(map.values());
}
```
