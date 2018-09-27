# 122. Best Time to Buy and Sell Stock II
*Easy*
09/26/18

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:
```
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
```
Example 2:
```
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
```
Example 3:
```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

## Attempts
* 规律：每次在局部最低值买入，局部最高值卖出。edge case:最后一个值如果是最高，则sell等于这个值，算出profit之后iteration结束。最后一个值如果是较低值，则新的buy和sell都等于这个值，加给profit的值是0.
```
public int maxProfit(int[] prices) {
    int buy = 0;
    int sell = 0;
    int profit = 0;
    for (int i = 0; i < prices.length; i++) {
        while (i < prices.length - 1 && prices[i] > prices[i + 1]) {
            i++;
        }
        buy = prices[i];
        while (i < prices.length - 1 && prices[i] < prices[i + 1]) {
            i++;
        }
        sell = prices[i];
        profit += (sell - buy);
    }
    return profit;
}
```

## Solutions
* Same as my approach - peak & valley approach
  - O(n) time, O(1) space
* Simple one pass
  - follows the same logic, but simplifies it.
```
public int maxProfit(int[] prices) {
    int maxprofit = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1])
            maxprofit += prices[i] - prices[i - 1];
    }
    return maxprofit;
}
```
