# 131. Palindrome Partitioning
4/17/19
*Medium*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:
```
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
```

## Solutions
- helper method `isPalindrome`
- backtrack: start from a certain index, i from start to end, test if is a palindrome, if yes, then add to temp list, continue with start = i + 1
```Java
public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    backtrack(res, new ArrayList<>(), s, 0);
    return res;
}

void backtrack(List<List<String>> list, List<String> temp, String s, int start) {
    if (start == s.length()) {
        list.add(new ArrayList<>(temp));
    }
    for (int i = start; i < s.length(); i++) {
        if (isPalindrome(s, start, i)) {
            temp.add(s.substring(start, i + 1));
            backtrack(list, temp, s, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

boolean isPalindrome(String s, int low, int high) {
    while (low < high) {
        if (s.charAt(low++) != s.charAt(high--)) {
            return false;
        }
    }
    return true;
}
```
