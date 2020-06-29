# 346. Moving Average from Data Stream
6/19/19
*Easy*

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
```
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
```

## Attempts
- use a queue to record numbers in window
  - O(1) time, O(size) space
```Java
class MovingAverage {

    Queue<Integer> queue;
    int sum;
    int capacity;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        sum = 0;
        capacity = size;
    }

    public double next(int val) {
        if (queue.size() >= capacity) {
            sum -= queue.poll();
        }
        sum += val;
        queue.offer(val);
        return sum * 1.0 / queue.size();
    }
}
```
