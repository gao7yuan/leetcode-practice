# 500.Keyboard Row
12/4/18
*Medium*

## 注意点
* `new HashSet<>(Arrays.asList(TOPARR))`
* `word.toUpperCase().charAt(0)`
* `return res.toArray(new String[0]);`

Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

Example:
```
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
```

Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

## Attempts
* use sets to store three rows of Keyboard
* check first char of a word to determine the row
* check others to see if they are in the same row
```Java
private final static Character[] TOPARR = new Character[]{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
private final static Character[] MIDARR = new Character[]{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
private final static Character[] BOTARR = new Character[]{'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
private final static Set<Character> TOP = new HashSet<>(Arrays.asList(TOPARR));
private final static Set<Character> MID = new HashSet<>(Arrays.asList(MIDARR));
private final static Set<Character> BOT = new HashSet<>(Arrays.asList(BOTARR));
public String[] findWords(String[] words) {
    List<String> res = new ArrayList<>();
    for (String word : words) {
        Set<Character> row = null;
        char first = word.charAt(0);
        if (TOP.contains(first)) {
            row = TOP;
        } else if (MID.contains(first)) {
            row = MID;
        } else {
            row = BOT;
        }
        if (sameRow(row, word)) {
            res.add(word);
        }
    }
    String[] ans = new String[res.size()];
    for (int i = 0; i < res.size(); i++) {
        ans[i] = res.get(i);
    }
    return ans;
}

private boolean sameRow(Set<Character> row, String word) {
    for (int i = 1; i < word.length(); i++) {
        if (!row.contains(word.charAt(i))) {
            return false;
        }
    }
    return true;
}
```

## Solutions
* use a map to record char-row number pairs
* use index flag to determine whether the chars are in a row or not
```Java
public String[] findWords(String[] words) {
    String[] rows = new String[]{"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < rows.length; i++) {
        for (char c : rows[i].toCharArray()) {
            map.put(c, i); // put character - row into map
        }
    }

    List<String> res = new ArrayList<>();
    for (String word : words) {
        if (word.equals("")) {
            continue;
        }
        int index = map.get(word.toUpperCase().charAt(0));
        for (int i = 1; i < word.length(); i++) {
            if (map.get(word.toUpperCase().charAt(i)) != index) {
                index = -1;
                break;
            }
        }
        if (index != -1) {
            res.add(word);
        }
    }
    return res.toArray(new String[0]);
}
```
