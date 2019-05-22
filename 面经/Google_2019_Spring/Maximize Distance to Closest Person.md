# 849. Maximize Distance to Closest Person
5/21/19
*Easy*

In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:
```
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
```
Example 2:
```
Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
```
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.

## Solutions
### 1. Next Array
- left[] and right[] to record distance to the left, right with person sitting in it
- min(left, right) is the distance to a seat with person for any spot. find the maximum
- O(n) time, O(n) space
```Java
public int maxDistToClosest(int[] seats) {
    int n = seats.length;
    int[] left = new int[n];
    int[] right = new int[n];
    Arrays.fill(left, n);
    Arrays.fill(right, n);
    for (int i = 0; i < n; i++) {
        if (seats[i] == 1) {
            left[i] = 0;
        } else {
            if (i > 0) {
                left[i] = left[i - 1] + 1;
            }
        }
    }

    for (int i = n - 1; i >= 0; i--) {
        if (seats[i] == 1) {
            right[i] = 0;
        } else {
            if (i < n - 1) {
                right[i] = right[i + 1] + 1;
            }
        }
    }

    int maxDist = Math.min(left[0], right[0]);
    int index = 0;
    for (int i = 1; i < n; i++) {
        int dist = Math.min(left[i], right[i]);
        if (dist > maxDist) {
            maxDist = dist;
            index = i;
        }
    }

    return maxDist;
}
```

### 2. Two pointer
- `prev` filled seat at or to the left of i, `future` filled seat at or to the right of i
- at i, distance = min(i - prev, future - i), except at 0 or n - 1
- O(n) time, O(1) space
```Java
public int maxDistToClosest(int[] seats) {
    int n = seats.length;
    int dist = 0;
    int prev = -1;
    int future = 0;

    for (int i = 0; i < n; i++) {
        if (seats[i] == 1) {
            prev = i;
        }
        while (future < n && seats[future] == 0 || future < i) {
            future++;
        }
        int left = prev == -1 ? n : i - prev;
        int right = future == n ? n : future - i;
        int d = Math.min(left, right);
        dist = Math.max(dist, d);
    }
    return dist;
}
```

### 3. Group by zero
- if distance between two 1's is K, max dist = (K + 1) / 2
- if between edge and a 1, K.
- O(n) time, O(1) space
```Java
public int maxDistToClosest(int[] seats) {
    int res = 0;
    int n = seats.length;
    int dist = 0;
    for (int i = 0; i < n; i++) {
        if (seats[i] == 1) {
            dist = 0;
        } else {
            dist++;
            res = Math.max(res, (dist + 1) / 2);
        }
    }

    for (int i = 0; i < n; i++) {
        if (seats[i] == 1) {
            res = Math.max(res, i);
            break;
        }
    }

    for (int i = n - 1; i >= 0; i--) {
        if (seats[i] == 1) {
            res = Math.max(res, n - 1 - i);
            break;
        }
    }
    return res;
}
```
