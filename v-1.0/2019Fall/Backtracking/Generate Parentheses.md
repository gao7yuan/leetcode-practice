# 22. Generate Parentheses
9/6/19 *Medium*

- my solution:
  - backtrack
  - each time check unused left and unused right to determine whether to add or not
  - when used up all brackets add to result

- my code
```Java
private List<String> res = new ArrayList<>();

public List<String> generateParenthesis(int n) {
    backtrack(n, n, n, new StringBuilder());
    return res;
}

private void backtrack(int n, int leftRm, int rightRm, StringBuilder exp) {
    if (leftRm == 0 && rightRm == 0) {
        // all brackets used
        res.add(exp.toString());
    }
    int len = exp.length();
    if (leftRm > 0) {
        // there's unsued left
        exp.append('(');
        backtrack(n, leftRm - 1, rightRm, exp);
        exp.deleteCharAt(len);
    }
    if (rightRm > 0 && leftRm < rightRm) {
        // there's unused right, and used left is more than used right
        exp.append(')');
        backtrack(n, leftRm, rightRm - 1, exp);
        exp.deleteCharAt(len);
    }
}
```
