# 56. Merge Intervals
*Medium*
09/25/18

Given a collection of intervals, merge all overlapping intervals.

Example 1:
```
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
```
Example 2:
```
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

## Attempts
* 开始的思路：按照start来sort所有的数对，如果遇到前一个end比后一个start大，则merge。但是不确定是否应该implement ```Comparator```
* 看了答案果然要implement ```Comparator```...
  - O(nlgn) time, O(n) space (or O(1) if we can sort in place)

  - Comparator:
```
private class intervalComparator implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        if (a.start > b.start) return 1;
        else if (a.start < b.start) return -1;
        else return 0;
    }
}
```
  - 先sort intervals
  - 先将第一个interval加入答案，之后每次比较前一个的end和后一个start的大小，**注意相等的情况也要merge**。如果需要merge，将前一个interval的end变为 **前一个和后一个的end中的较大值**。
```
public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, new intervalComparator());
    List<Interval> res = new ArrayList<>();
    if (intervals.isEmpty()) return res;
    res.add(intervals.get(0));
    for (int i = 1; i < intervals.size(); i++) {
        Interval resLast = res.get(res.size() - 1);
        Interval intNext = intervals.get(i);
        if (resLast.end >= intNext.start) {
            resLast.end =
                resLast.end >= intNext.end ? resLast.end : intNext.end;
        } else {
            res.add(intervals.get(i));
        }
    }
    return res;
}
```
## Solutions
* Java note:
  - ```getLast``` for LinkedList
```
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        return merged;
    }
```    
