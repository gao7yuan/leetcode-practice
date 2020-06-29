# 125. Valid Palindrome
*Easy*
08/29/18

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
```
Input: "A man, a plan, a canal: Panama"
Output: true
```
Example 2:
```
Input: "race a car"
Output: false
```

## Attempts
* One pointer at head and one at tail.
* Note that the character is alphanumeric.
* O(n) time.
```
public boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
        while (!(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
               && !(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
               && !(s.charAt(i) >= '0' && s.charAt(i) <= '9')
               && i < j) {
            i++;
        }
        while (!(s.charAt(j) >= 'A' && s.charAt(j) <= 'Z')
               && !(s.charAt(j) >= 'a' && s.charAt(j) <= 'z')
               && !(s.charAt(j) >= '0' && s.charAt(j) <= '9')
               && i < j) {
            j--;
        }
        if (s.charAt(i) != s.charAt(j)  // can be simplified as: Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))
            && Character.toUpperCase(s.charAt(i)) != s.charAt(j)
            && Character.toLowerCase(s.charAt(i)) != s.charAt(j)) {
            return false;
        }
        i++;
        j--;
    }
    return true;
}
```

## Solutions
* Similar approach.
  - **Java notes**: ```java.lang.Character.isLetterOrDigit()``` method
* Use regex.
  - **Java notes**: ```reverse()``` of StringBuffer
```
public boolean isPalindrome(String s) {
    String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    String rev = new StringBuffer(actual).reverse().toString();
    return actual.equals(rev);
}
```
