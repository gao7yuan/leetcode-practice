# 252. Meeting Rooms
*Easy* *二刷*
09/30/18

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:
```
Input: [[0,30],[5,10],[15,20]]
Output: false
```
Example 2:
```
Input: [[7,10],[2,4]]
Output: true
```

## Attempts
* sort according to start first, then compare the end of the previous one with the start of the next one
  - sort: O(nlgn) time, compare: one pass, O(n) time. in total O(nlgn) time.
```
public boolean canAttendMeetings(Interval[] intervals) {
    Arrays.sort(intervals, new IntervalComp());
    for (int i = 0; i < intervals.length - 1; i++) {
        if (intervals[i].end > intervals[i + 1].start) return false;
    }
    return true;
}

private class IntervalComp implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        return a.start > b.start ? 1 : a.start == b.start ? 0 : -1;
    }
}
```

## Solutions
* Brute force
* sorting
  - lambda FYI
```
public boolean canAttendMeetings(Interval[] intervals) {
    Arrays.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }        
    });
    for (int i = 0; i < intervals.length - 1; i++) {
        if (intervals[i].end > intervals[i + 1].start) return false;
    }
    return true;
}
```

- 二刷，signature变了
```Java
public boolean canAttendMeetings(int[][] intervals) {
    if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
        return true;
    }
    // sort intervals based on start time
    Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
    int num = intervals.length;
    for (int i = 0; i < num - 1; i++) {
        // if start time of next is smaller than end time of this one, false
        if (intervals[i + 1][0] < intervals[i][1]) {
            return false;
        }
    }
    return true;
}
```
