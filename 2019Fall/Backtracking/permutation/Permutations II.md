# 47. Permutations II
9/6/19 *Medium*

- might contain duplicates

- use a Set -> in this case Time = O(n!), Space = O(n). We still need to generate duplicated permutations and this is a waste of time
- sort array first, then for each backtrack -> will save a lot of time if there are many duplicates

- use `Set<Integer> used` or use `boolean[] used` to record used indices
