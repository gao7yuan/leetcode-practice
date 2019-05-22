# 386. Lexicographical Numbers
5/21/19
*Medium*

Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

## Solutions
- DFS
  - 1->0, 1, 2, 3...9
  - 2->0, 1, 2, 3...9
  - pre-order

  - O(n) time, O(n) space?

```Java
public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
        dfs(i, n, res);
    }
    return res;
}

void dfs(int cur, int n, List<Integer> res) {
    if (cur > n) {
        return;
    }
    res.add(cur);
    for (int i = 0; i < 10; i++) {
        dfs(10 * cur + i, n, res);
    }
}
```
