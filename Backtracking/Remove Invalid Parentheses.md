# 301. Remove Invalid Parentheses
5/29/19
*Hard*

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:
```
Input: "()())()"
Output: ["()()()", "(())()"]
```
Example 2:
```
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
```
Example 3:
```
Input: ")("
Output: [""]
```

## Solutions
- backtracking
  - O(2^n) time, O(n) space
```Java
class Solution {

  private Set<String> validExpressions = new HashSet<String>();
  private int minimumRemoved;

  private void reset() {
    this.validExpressions.clear();
    this.minimumRemoved = Integer.MAX_VALUE;
  }

  private void recurse(
      String s,
      int index,
      int leftCount,
      int rightCount,
      StringBuilder expression,
      int removedCount) {

    // If we have reached the end of string.
    if (index == s.length()) {

      // If the current expression is valid.
      if (leftCount == rightCount) {

        // If the current count of removed parentheses is <= the current minimum count
        if (removedCount <= this.minimumRemoved) {

          // Convert StringBuilder to a String. This is an expensive operation.
          // So we only perform this when needed.
          String possibleAnswer = expression.toString();

          // If the current count beats the overall minimum we have till now
          if (removedCount < this.minimumRemoved) {
            this.validExpressions.clear();
            this.minimumRemoved = removedCount;
          }
          this.validExpressions.add(possibleAnswer);
        }
      }
    } else {

      char currentCharacter = s.charAt(index);
      int length = expression.length();

      // If the current character is neither an opening bracket nor a closing one,
      // simply recurse further by adding it to the expression StringBuilder
      if (currentCharacter != '(' && currentCharacter != ')') {
        expression.append(currentCharacter);
        this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
        expression.deleteCharAt(length);
      } else {

        // Recursion where we delete the current character and move forward
        this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
        expression.append(currentCharacter);

        // If it's an opening parenthesis, consider it and recurse
        if (currentCharacter == '(') {
          this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
        } else if (rightCount < leftCount) {
          // For a closing parenthesis, only recurse if right < left
          this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
        }

        // Undoing the append operation for other recursions.
        expression.deleteCharAt(length);
      }
    }
  }

  public List<String> removeInvalidParentheses(String s) {

    this.reset();
    this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
    return new ArrayList(this.validExpressions);
  }
}
```

- my code
```Java
class Solution {

    int min_rmv = Integer.MAX_VALUE;
    Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        reset();
        backtrack(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<String>(res);
    }

    void reset() {
        this.min_rmv = Integer.MAX_VALUE;
        this.res.clear();
    }

    void backtrack(String s, int left, int right, int index, StringBuilder sofar, int rmv) {
        // if reached the end of string
        if (index == s.length()) {
            // if valid
            if (left == right) {
                // check remove count
                if (rmv <= min_rmv) {
                    String toadd = sofar.toString();
                    if (rmv < min_rmv) {
                        this.res.clear();
                        min_rmv = rmv;
                    }
                    res.add(toadd);
                }
            }
        } else {
            char c = s.charAt(index);
            int len = sofar.length();

            if (c != '(' && c != ')') {
                // add it
                sofar.append(c);
                // recursion
                backtrack(s, left, right, index + 1, sofar, rmv);
                // backtrack
                sofar.deleteCharAt(len);
            } else {
                // choose to take or not to take
                // not to take
                backtrack(s, left, right, index + 1, sofar, rmv + 1);

                // to take
                if (c == '(') {
                    sofar.append(c);
                    backtrack(s, left + 1, right, index + 1, sofar, rmv);
                    sofar.deleteCharAt(len);
                } else {
                    if (left > right) {
                        sofar.append(c);
                        backtrack(s, left, right + 1, index + 1, sofar, rmv);
                        sofar.deleteCharAt(len);
                    }
                }
            }
        }
    }
}
```

- limited backtracking
```Java
class Solution {

  private Set<String> validExpressions = new HashSet<String>();

  private void recurse(
      String s,
      int index,
      int leftCount,
      int rightCount,
      int leftRem,
      int rightRem,
      StringBuilder expression) {

    // If we reached the end of the string, just check if the resulting expression is
    // valid or not and also if we have removed the total number of left and right
    // parentheses that we should have removed.
    if (index == s.length()) {
      if (leftRem == 0 && rightRem == 0) {
        this.validExpressions.add(expression.toString());
      }

    } else {
      char character = s.charAt(index);
      int length = expression.length();

      // The discard case. Note that here we have our pruning condition.
      // We don't recurse if the remaining count for that parenthesis is == 0.
      if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
        this.recurse(
            s,
            index + 1,
            leftCount,
            rightCount,
            leftRem - (character == '(' ? 1 : 0),
            rightRem - (character == ')' ? 1 : 0),
            expression);
      }

      expression.append(character);

      // Simply recurse one step further if the current character is not a parenthesis.
      if (character != '(' && character != ')') {

        this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

      } else if (character == '(') {

        // Consider an opening bracket.
        this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

      } else if (rightCount < leftCount) {

        // Consider a closing bracket.
        this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
      }

      // Delete for backtracking.
      expression.deleteCharAt(length);
    }
  }

  public List<String> removeInvalidParentheses(String s) {

    int left = 0, right = 0;

    // First, we find out the number of misplaced left and right parentheses.
    for (int i = 0; i < s.length(); i++) {

      // Simply record the left one.
      if (s.charAt(i) == '(') {
        left++;
      } else if (s.charAt(i) == ')') {
        // If we don't have a matching left, then this is a misplaced right, record it.
        right = left == 0 ? right + 1 : right;

        // Decrement count of left parentheses because we have found a right
        // which CAN be a matching one for a left.
        left = left > 0 ? left - 1 : left;
      }
    }

    this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
    return new ArrayList<String>(this.validExpressions);
  }
}
```

- my code
```Java
class Solution {

    Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left == 0) {
                    right++;
                }
                if (left > 0) {
                    left--;
                }
            }
        }

        backtrack(s, 0, 0, left, right, 0, new StringBuilder());
        return new ArrayList<String>(res);

    }

    void backtrack(String s, int leftcnt, int rightcnt, int leftrm, int rightrm, int index, StringBuilder sofar) {
        if (s.length() == index) {
            if (leftrm == 0 && rightrm == 0) {
                res.add(sofar.toString());
            }
        } else {
            char c = s.charAt(index);
            int len = sofar.length();

            if (c != '(' && c != ')') {
                sofar.append(c);
                backtrack(s, leftcnt, rightcnt, leftrm, rightrm, index + 1, sofar);
                sofar.deleteCharAt(len);
            } else {
                if (c == '(') {
                    // not add
                    if (leftrm > 0) {
                        backtrack(s, leftcnt, rightcnt, leftrm - 1, rightrm, index + 1, sofar);
                    }
                    // add
                    sofar.append(c);
                    backtrack(s, leftcnt + 1, rightcnt, leftrm, rightrm, index + 1, sofar);
                    sofar.deleteCharAt(len);
                } else {
                    // not add
                    if (rightrm > 0) {
                        backtrack(s, leftcnt, rightcnt, leftrm, rightrm - 1, index + 1, sofar);
                    }
                    // add
                    if (leftcnt > rightcnt) {
                        sofar.append(c);
                        backtrack(s, leftcnt, rightcnt + 1, leftrm, rightrm, index + 1, sofar);
                        sofar.deleteCharAt(len);
                    }
                }
            }
        }
    }
}
```
