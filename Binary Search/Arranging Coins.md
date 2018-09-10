# 441. Arranging Coins
*Easy*
08/28/18

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:
```
n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
```
Example 2:
```
n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
```

## Attempts
* Binary search.
  - Math is simple: 1+2+3+...+k = k*(k+1)/2.
  - Be careful with edge case and overflow.
```
public int arrangeCoins(int n) {
    long i = 0;
    long j = n;
    long nLong = (long) n;
    while (i <= j) {
        long k = i + (j - i) / 2;
        if (k * (k + 1) <= 2 * nLong) {
            i = k + 1;
        } else {
            j = k - 1;
        }
    }
    return (int) i - 1;
}
```
