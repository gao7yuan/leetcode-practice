# 187. Repeated DNA Sequences
12/6/18
*Medium*

## Note
* `new ArrayList<>(repeated)`
* `if (!seen.add(str))`

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:
```
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
```

## Attempts
* 用一个set记录查看过的所有10-letter-long substrings，如果已存在就加入res set。O(n) time, n是string的长度

```Java
public List<String> findRepeatedDnaSequences(String s) {
    List<String> res = new ArrayList<>();
    if (s == null || s.length() == 0) {
        return res;
    }
    Set<String> sequenceSet = new HashSet<>();
    Set<String> resSet = new HashSet<>();
    for (int i = 0; i <= s.length() - 10; i++) {
        String str = s.substring(i, i + 10);
        if (sequenceSet.contains(str)) {
            if (!resSet.contains(str)) {
                resSet.add(str);
            }
        } else {
            sequenceSet.add(str);
        }
    }
    for (String str : resSet) {
        res.add(str);
    }
    return res;
}
```

## Solutions
```Java
public List<String> findRepeatedDnaSequences(String s) {
    if (s == null || s.length() == 0) {
        return new ArrayList<>();
    }
    Set<String> seen = new HashSet<>();
    Set<String> repeated = new HashSet<>();
    for (int i = 0; i <= s.length() - 10; i++) {
        String str = s.substring(i, i + 10);
        if (!seen.add(str)) {
            repeated.add(str);
        }
    }
    return new ArrayList<>(repeated);
}
```
