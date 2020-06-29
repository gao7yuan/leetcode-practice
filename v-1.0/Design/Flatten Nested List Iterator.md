# 341. Flatten Nested List Iterator
6/15/19
*Medium*

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
```
Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,1,2,1,1].
```
Example 2:
```
Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,4,6].
```

## Attempts
- use recursion to convert `List<NestedInteger>` to List<Integer>
```Java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    ArrayList<Integer> flat;
    int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.flat = new ArrayList<>();
        flatten(nestedList);
        this.index = 0;
    }

    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger ele : nestedList) {
            if (ele.isInteger()) {
                this.flat.add(ele.getInteger());
            } else {
                flatten(ele.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return this.flat.get(index++);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return index < this.flat.size();
    }
}
```
