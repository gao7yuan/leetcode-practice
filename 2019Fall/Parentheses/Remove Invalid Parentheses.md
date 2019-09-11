# 301. Remove Invalid Parentheses
9/6/19 *hard*

1. Valid: each `)` has a matching `(`, and each `(` has a matching `)`.
2. remove K parentheses -> may get different valid parentheses.
3. each valid parentheses -> may have different ways to remove

- for each bracket, two choices
  1. keep it for final expression
  2. delete
- try out all options: Recursion

- Algorithm
1. array to store all valid expressions
2. start from left and proceed to right
3. STATE of recursion: index in original string `S`: `i`. `left_count`, `right_count`
4. if `S[i]` is not a bracket, add it to solution string
5. if `S[i]` is a bracket, two options: keep or discard. For a closing bracket, we check `left_count` and `right_count` to decide whether we can take it or not.
6. when all brackets has been processed, check if it is valid or not.
How to check:
`left_count == right_count`
  - also keep track of `rm_count` and check value with global minimum
- Time: O(2^n), all left brackets. Space: O(n) recursion stack

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

- Limited backtracking
  - find out number of misplaced brackets
  - How?
  1. process left to right
  2. left bracket: `left_count++``
  3. right bracket: two cases
    - `left_count == 0` -> `right_count++`` (misplaced right brackets)
    - `left_count > 0` -> `left_count--``
  4. finally `left_count` and `right_count` represent unmatched left and right brackets
- Algorithm
  - variables
    1. index
    2. left_count, right_count -> already added to `exp`
    3. left_rm, right_rm -> final condition: left_rm == 0, right_rm == 0
