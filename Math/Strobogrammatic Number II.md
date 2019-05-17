# 247. Strobogrammatic Number II
5/16/19
*Medium*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:
```
Input:  n = 2
Output: ["11","69","88","96"]
```

## Solutions
- recursion
  - base case:
    - n == even, 0: ""
    - n == odd, 1: "0" or "1" or "8"
  - recursion:
    - add digits before and after previous result
- Time complexity O(5 ^ (n / 2)), space complexity O(5 ^ (n / 2)).
  - n / 2 indices for choice, each index has 5 choices.
  - Not counting in the time cost for string connecting. Using char[] to record num during dfs saves this cost.
  - Space complexity means the number of resulting num.
```Java
public List<String> findStrobogrammatic(int n) {
    return find(n, n);
}

// build string from scratch, till cur == n. cur means number of digits in the string
List<String> find(int cur, int n) {
    if (cur == 0) {
        return new ArrayList<>(Arrays.asList(""));
    }
    if (cur == 1) {
        return new ArrayList<>(Arrays.asList("0", "1", "8"));
    }
    List<String> prev = find(cur - 2, n);
    List<String> res = new ArrayList<>();
    for (int i = 0; i < prev.size(); i++) {
        String s = prev.get(i);
        if (cur != n) {
            res.add("0" + s + "0");
        }
        res.add("1" + s + "1");
        res.add("6" + s + "9");
        res.add("8" + s + "8");
        res.add("9" + s + "6");
    }
    return res;
}
```

- iterative
```Java
public List<String> findStrobogrammatic(int n) {
    List<String> zero = new ArrayList<>(Arrays.asList(""));
    List<String> one = new ArrayList<>(Arrays.asList("0", "1", "8"));
    List<String> base;
    if (n % 2 == 0) {
        base = zero;
    } else {
        base = one;
    }
    for (int i = 0; i < n / 2; i++) {
        // list of strings after adding one digit to start and end
        List<String> r = new ArrayList<>();
        for (String s : base) {
            if (i != n / 2 - 1) {
                r.add("0" + s + "0");
            }
            r.add("1" + s + "1");
            r.add("6" + s + "9");
            r.add("8" + s + "8");
            r.add("9" + s + "6");
        }
        base = r;
    }
    return base;
}
```
