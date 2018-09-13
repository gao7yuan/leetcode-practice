# 121. Best Time to Buy and Sell Stock
*Easy*
09/11/18

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:
```
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
```
Example 2:
```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

## Attempts
* Brute force(没有implement)
* 根据答案提示想了一个自己的方法但是是错的
  - 先找min，再找这个min之后存在的max
  - 错误的原因是，买入值不一定需要是min

## Solutions
### Approach 1: Brute Force
* O(n^2) time O(1) space
### Approach 2: One Pass
* 记录minprice和maxprofit，寻找到当前最小值之后找最大profit，如果遇到的值比当前最小值更小则更新最小值，最大profit暂时不变，直到遇到更大的profit
  - O(n) time O(1) space
```
public int maxProfit(int prices[]) {
    int minprice = Integer.MAX_VALUE; // 也可以用prices[0]，但是这样就要考虑array是空的情况
    int maxprofit = 0;
    for (int i = 0; i < prices.length; i++) {
        if (prices[i] < minprice)
            minprice = prices[i];
        else if (prices[i] - minprice > maxprofit)
            maxprofit = prices[i] - minprice;
    }
    return maxprofit;
}
```
