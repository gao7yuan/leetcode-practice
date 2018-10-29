# 253. Meeting Rooms II
*Medium*
10/29/18

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:
```
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
```
Example 2:
```
Input: [[7,10],[2,4]]
Output: 1
```

## Solutions
* Priority queues
  - sort the meetings according to start time.
  - before starting a meeting, check the top of the min heap consisting of end times to see if any room is available. If there is an available room, remove the top of the heap.
  - once a meeting has started, add the end time to the priority queue, which is a mean heap
  - in the end, check the size of the heap.
  - O(nlgn) time (sort and extract min), O(n) space (heap)
```
public int minMeetingRooms(Interval[] intervals) {
    if (intervals.length == 0) {
        return 0;
    }

    PriorityQueue<Integer> rooms
        = new PriorityQueue<>(intervals.length,
                              new Comparator<Integer>() {
                                  public int compare(Integer a, Integer b) {
                                      return a - b;
                                  }
                              });

    Arrays.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    });

    rooms.add(intervals[0].end);

    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i].start >= rooms.peek()) {
            rooms.poll();
        }
        rooms.add(intervals[i].end);
    }
    return rooms.size();
}
```
* Chronological Ordering
  - create two arrays of the start and end time of meetings in increasing order.
  - go through the start times. if at that time any room is free, increment end time. otherwise add one meeting room.
  - O(nlgn) time (sort), O(n) space (arrays)
```
public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {
        return 0;
    }
    int[] start = new int[intervals.length];
    int[] end = new int[intervals.length];
    for (int i = 0; i < intervals.length; i++) {
        start[i] = intervals[i].start;
        end[i] = intervals[i].end;
    }
    Arrays.sort(start);
    Arrays.sort(end);
    int num = 0;
    int startInd = 0;
    int endInd = 0;
    while (startInd < intervals.length) {
        if (start[startInd] >= end[endInd]) {
            endInd++;
        } else {
            num++;
        }
        startInd++;
    }
    return num;
}
```
