# 17. Letter Combinations of a Phone Number
*Medium*
10/16/18

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:
```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

## Solutions
* BFS using a queue
1. using a String[] to store the mapping of number - letters
2. use a queue to store all the possible combinations of letters up to a certain number in the input digits
3. each time dequeue and get the previous combination, add the result of appending each letter to the pre comb, and enqueue
4. when peek gives a combination with length longer than current index in the input digits, that means all the combinations are visited
  - note: ```LinkedList``` is a class that implements ```Queue```, but ```List``` is not necessarily ```Queue```. If want to use a queue, use LinkedList
  - also note edge cases where digits is ""
```
public List<String> letterCombinations(String digits) {
    LinkedList<String> res = new LinkedList<>();
    if (digits == null || digits.length() == 0) {
        return res;
    }
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    res.add("");
    for (int i = 0; i < digits.length(); i++) {
        String letters = mapping[digits.charAt(i) - '0'];
        while (res.peek().length() == i) {
            String prev = res.remove();
            for (int j = 0; j < letters.length(); j++) {
                res.add(prev + letters.charAt(j));
            }
        }
    }
    return res;
}
```

* Recursion
  - String prefix: an accumulator that records a letter combination of previous digits. When offset reaches digits.length that means all digits are visited, now we add the prefix to resulting list.
  - int offset: from 0 to digits.length - 1, visits each char in digits. In each recursion we add one possible letter from the mapping to the accumulator.
```
private static final String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits.length() == 0) {
        return res;
    }
    combinations("", digits, 0, res);
    return res;
}

void combinations(String prev, String digits, int offset, List<String> res) {
    if (offset >= digits.length()) {
        res.add(prev);
        return;
    }
    String letters = mapping[digits.charAt(offset) - '0'];
    for (char letter : letters.toCharArray()) {
        combinations(prev + letter, digits, offset + 1, res);
    }
}
```
