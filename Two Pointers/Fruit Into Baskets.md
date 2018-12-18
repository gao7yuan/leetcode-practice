# 904. Fruit Into Baskets
12/17/18
*Medium*

In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

## Attempts
* 本质上是找一个array里面最长的只含有两种数的subarray
* 三个runner
  - i: first index of first kind of fruit
  - j: second index of second kind of fruit
  - k: visit each number till the end of array or till the third fruit
* 注意外面while loop的条件，以及k
* Time complexity: O(nk) ??
* There is duplication

```Java
public int totalFruit(int[] tree) {
    if (tree == null || tree.length == 0) {
        return 0;
    }
    int max = 0;
    // i: first index of first kind of fruit
    // j: first index of second kind of fruit
    // k: runner
    int i = 0, j = Integer.MIN_VALUE, k = 0;

    while (i < tree.length) {
        // when k is in range and tree[k] is one of the two kinds of fruits
        while (k < tree.length &&
               (tree[k] == tree[i] || j == Integer.MIN_VALUE
                || j != Integer.MIN_VALUE && tree[k] == tree[j])) {
            // when to set first index of second kind of fruit
            if (j == Integer.MIN_VALUE && tree[k] != tree[i]) {
                j = k;
            }
            k++;
        }
        int num = k - i;
        max = max > num ? max : num;
        if (k >= tree.length) {
            break;
        }
        i = j;
        j = Integer.MIN_VALUE;
        k = i;
    }
    return max;
}
```

## Solutions
### 1. Scan through blocks
* Use `List<Integer> blockLefts` to record first index of each block
* For each block, calculate weight in a cumulative way. Use a HashSet to record types. Once number of types exceed 2, break that iteration.
* O(n) time, O(n) space
```Java
public int totalFruit(int[] tree) {
    if (tree == null || tree.length == 0) {
        return 0;
    }
    // record first index of each block
    List<Integer> blockLefts = new ArrayList<>();
    for (int i = 0; i < tree.length; i++) {
        if (i == 0 || tree[i - 1] != tree[i]) {
            blockLefts.add(i);
        }
    }
    blockLefts.add(tree.length);

    int max = 0, i = 0;
    while (true) {
        Set<Integer> types = new HashSet<>();
        int weight = 0;
        int j = i;
        for (; j < blockLefts.size() - 1; j++) {
            types.add(tree[blockLefts.get(j)]);
            // add length of this block to weight
            weight += blockLefts.get(j + 1) - blockLefts.get(j);
            if (types.size() >= 3) {
                i = j - 1;
                break;
            }
            max = Math.max(max, weight);
        }
        if (j >= blockLefts.size() - 1) {
            break;
        }
    }
    return max;
}
```

## 2. Sliding window
* [i, j] largest valid subarray window
* i: left most index of the valid subarray
* j: subarray ends at j
* Use a Counter (extends HashMap) to record types and their occurrences.
* O(n) time, O(n) space
```Java
public int totalFruit(int[] tree) {
    if (tree == null || tree.length == 0) {
        return 0;
    }
    int max = 0, i = 0;
    Map<Integer, Integer> typeMap = new HashMap<>();
    for (int j = 0; j < tree.length; j++) {
        if (typeMap.containsKey(tree[j])) {
            typeMap.put(tree[j], typeMap.get(tree[j]) + 1);
        } else {
            typeMap.put(tree[j], 1);
        }
        while (typeMap.size() >= 3) {
            typeMap.put(tree[i], typeMap.get(tree[i]) - 1);
            if (typeMap.get(tree[i]).equals(0)) {
                typeMap.remove(tree[i]);
            }
            i++;
        }
        max = Math.max(max, j - i + 1);
    }
    return max;
}
```
