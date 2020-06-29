# 322. Coin Change
1/9/19
*Medium*

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
```
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
```
Example 2:
```
Input: coins = [2], amount = 3
Output: -1
```
Note:
You may assume that you have an infinite number of each kind of coin.

## Solutions
### 1. Brute force
* sum(xi*ci) = s
* 0 <= xi <= s/ci
* Backtracking - generate all combinations of coin frequencies
* Algorithm:
  1. `idxCoin`: index of coin, which coin in the array to pick. It goes from 0 to `coins.length - 1`
  2. for each `idxCoin`, `maxVal = amount/coins[idxCoin]` is the max number of coins we can pick of this value
  3. for each `idxCoin`, iterate through all possible number of coins from 0 to `maxVal`, recursively call `coinChange` on the next denomination, `idxCoin + 1`, and updated amount `amount - x * coins[idxCoin]`. If the result isn't -1, update `minCost`
* Time: Every coin denomination could have s/ci values, so the number of possible combinations is (s/c1) * (s/c2) * ... * (s/cn) = O(s^n)
* Space: O(n) from recursion
```Java
public int coinChange(int[] coins, int amount) {
    return coinChange(0, coins, amount);
}

private int coinChange(int idxCoin, int[] coins, int amount) {
    if (amount == 0)
        return 0;
    // idxCoin: which coin to pick
    if (idxCoin < coins.length && amount > 0) {
        int maxVal = amount/coins[idxCoin]; // max number of coins of this value
        int minCost = Integer.MAX_VALUE;
        for (int x = 0; x <= maxVal; x++) { // iterate through all possible number of coins of this value
            if (amount >= x * coins[idxCoin]) {
                int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                if (res != -1)
                    minCost = Math.min(minCost, res + x);
            }                    
        }           
        return (minCost == Integer.MAX_VALUE)? -1: minCost;
    }        
    return -1;
}  
```

### 2. DP (top down)
* f(s) is an optimal solution to total amount of s with coin denomination from c0 to cn-1
* Suppose f(s) is an optimal solution with val1, val2 ... for all the other coins and the last coin denomination is C.
* f(s) = f(s-C) + 1. But we don't know about C.
* f(s) = min f(s-ci) + 1, s-ci >= 0
  - f(s) = 0 when s = 0
  - f(s) = -1 when n = 0
* Use a table to store values that have been calculated
* O(S*n) time - in the worst case height of recursion tree is S, and for each problem have to iterate n times
* O(S) space - the table has size of S

```Java
public int coinChange(int[] coins, int amount) {        
    if (amount < 1) return 0;
    return coinChange(coins, amount, new int[amount]);
}

// rem: remaining amount
// count[]: the optimal number of coins to get certain amount of money. count[0] -> amount = 1
private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) return -1;
    if (rem == 0) return 0;
    if (count[rem - 1] != 0) return count[rem - 1];
    int min = Integer.MAX_VALUE;
    for (int coin : coins) { // iterate all denominations, all possible last coins
        int res = coinChange(coins, rem - coin, count);
        if (res >= 0 && res < min)
            min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
}
```

### 3.  DP(iterative, bottom up)
* f(i) = min f(i - cj) + 1
* Note: cannot use `Integer.MAX_VALUE` because that plus one results in -xxxxxx. Use `amount + 1` instead because count can never go beyond that
* O(S*n) time, O(S) space
```Java
public int coinChange(int[] coins, int amount) {
    int[] count = new int[amount + 1];
    Arrays.fill(count, amount + 1); // !
    count[0] = 0;
    // fill count from bottom up
    for (int i = 1; i <= amount; i++) {
        // iterate all coins
        for (int j = 0; j < coins.length; j++) {
            if (coins[j] <= i) {
                count[i] = Math.min(count[i], count[i - coins[j]] + 1);
            }
        }
    }
    return count[amount] > amount ? -1 : count[amount];
}
```
