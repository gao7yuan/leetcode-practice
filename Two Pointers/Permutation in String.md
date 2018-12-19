# 567. Permutation in String
12/19/18
*Medium*

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
```
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
```
Example 2:
```
Input:s1= "ab" s2 = "eidboaoo"
Output: False
```
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

## Solutions
### 1. Brute force (time limit exceeded)
* find all permutations and use `s1.indexOf(s2) >= 0` to check inclusion
* O(n^3) time
### 2. Sorting (time limit exceeded)
* sort s1, sort all possible substrings of s2
* O(l1 logl1 + (l2 - l1)l1 logl1) time. O(l1) space (for sorting)
### 3. Hashmap (time limit exceeded)
* use a hashmap to record char-freq of s1
* use a hashmap to record char-freq of substrings of s2
* O(l1 + 26 * l1 * (l2 - l1)) time, O(1) space
### 4. Using array (time limit exceeded)
* similar as 3
* O(l1 + 26 * l1 * (l2 - l1)) time, O(1) space
### 5. Sliding window
* use two arrays of size 26 to record frequency of each character
* only record length = s1 for s2, starting with first s1.length() letters, then slide the window, update the array each time
* O(l1 + 26 * (l2 - l1)) time, O(1) space
```Java
public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) {
        return false;
    }
    int[] freq1 = new int[26];
    int[] freq2 = new int[26];
    for (int i = 0; i < s1.length(); i++) {
        freq1[s1.charAt(i) - 'a']++;
        freq2[s2.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s2.length() - s1.length(); i++) {
        if (matches(freq1, freq2)) {
            return true;
        }
        freq2[s2.charAt(i) - 'a']--;
        freq2[s2.charAt(i + s1.length()) - 'a']++;
    }
    return matches(freq1, freq2);
}

private boolean matches(int[] a1, int[] a2) {
    for (int i = 0; i < a1.length; i++) {
        if (a1[i] != a2[i]) {
            return false;
        }
    }
    return true;
}
```
### 6. Optimized sliding window
* instead of checking arrays of 26 length each time, we can keep track of number of letters of which their frequencies match.
* O(l1 + l2 - l1) = O(l2) time
```Java
public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) {
        return false;
    }
    int[] freq1 = new int[26];
    int[] freq2 = new int[26];
    for (int i = 0; i < s1.length(); i++) {
        freq1[s1.charAt(i) - 'a']++;
        freq2[s2.charAt(i) - 'a']++;
    }
    int count = 0;
    for (int i = 0; i < 26; i++) {
        if (freq1[i] == freq2[i]) {
            count++;
        }
    }
    for (int i = 0; i < s2.length() - s1.length(); i++) {
        if (count == 26) {
            return true;
        }
        int prev = s2.charAt(i) - 'a';
        int next = s2.charAt(i + s1.length()) - 'a';
        freq2[prev]--;
        if (freq2[prev] == freq1[prev]) {
            count++;
        } else if (freq2[prev] == freq1[prev] - 1) {
            count--;
        }

        freq2[next]++;
        if (freq2[next] == freq1[next]) {
            count++;
        } else if (freq2[next] == freq1[next] + 1) {
            count--;
        }      
    }
    return count == 26;
}
```
