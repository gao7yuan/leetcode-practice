# 202. Happy Number
08/24/18

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

**Example:**
```
Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

## Attempts
* Use a set to keep track of numbers that have already reached.
```
public boolean isHappy(int n) {
    int cur = n;
    int digit = 0;
    int sum = 0;
    Set<Integer> set = new HashSet<>();
    set.add(n);

    while (!set.contains(sum)) {
        set.add(sum);
        sum = 0;
        while (cur != 0) {
            digit = cur % 10;
            cur = cur / 10;
            sum += (digit * digit);
        }
        if (sum == 1) {
            return true;
        }
        cur = sum;
    }
    return false;
}
```
