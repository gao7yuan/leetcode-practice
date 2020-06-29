# 763. Partition Labels
12/18/18
*Medium*

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
```
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
```
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

## Solutions
* Greedy and Two pointers
* anchor: start of each partition
* i: runner than iterates the whole string
* j: end of each partition.
* last[]: an array of ints to represent the last occurrence of each letter in the string. j will be updated based on this array
* when i == j, start a new partition
* O(n) time, O(1) space
```Java
public List<Integer> partitionLabels(String S) {
    List<Integer> res = new ArrayList<>();
    if (S == null || S.length() == 0) {
        return res;
    }
    int[] last = new int[26];
    for (int i = 0; i < S.length(); i++) {
        last[S.charAt(i) - 'a'] = i;
    }
    int anchor = 0, j = 0;
    for (int i = 0; i < S.length(); i++) {
        j = Math.max(j, last[S.charAt(i) - 'a']);
        if (i == j) {
            res.add(i - anchor + 1);
            anchor = i + 1;
        }
    }
    return res;
}
```
