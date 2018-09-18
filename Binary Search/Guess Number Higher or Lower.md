# 374. Guess Number Higher or Lower
*Easy* *已整理*
08/28/18

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
```
-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 ```
Example:
```
n = 10, I pick 6.

Return 6.
```

## Attempts
* Binary search
  - checking base case with 2 inputs really helps.
```
public int guessNumber(int n) {
    int i = 1;
    int j = n;
    int m = i + (j - i) / 2;
    while (i <= j) {
        m = i + (j - i) / 2;
        if (guess(m) == -1) {
            j = m - 1;
        } else if (guess(m) == 1) {
            i = m + 1;
        } else {
            return m;
        }
    }
    return -1;
}
```

## Solutions
* Brute force
  - O(n) time, O(1) space.
* Binary search
  - O(logn) time, O(1) space.
* Ternary search
  - choose 2 pivots: m1 and m2. If num < m1 then apply ternary search on the left segment of m1.
  - O(logn/log3) time. O(1) space.
```
public int guessNumber(int n) {
    int low = 1;
    int high = n;
    while (low <= high) {
        int mid1 = low + (high - low) / 3;
        int mid2 = high - (high - low) / 3;
        int res1 = guess(mid1);
        int res2 = guess(mid2);
        if (res1 == 0)
            return mid1;
        if (res2 == 0)
            return mid2;
        else if (res1 < 0)
            high = mid1 - 1;
        else if (res2 > 0)
            low = mid2 + 1;
        else {
            low = mid1 + 1;
            high = mid2 - 1;
        }
    }
    return -1;
}
```
