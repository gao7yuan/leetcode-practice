# 973. K Closest Points to Origin
5/30/19
*Medium*

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:
```
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
```
Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
```

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

## Attempts
```Java
public int[][] kClosest(int[][] points, int K) {
    Map<int[], Integer> dist = new HashMap<>();
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>(K, (p1, p2) -> dist.get(p2) - dist.get(p1));
    for (int i = 0; i < points.length; i++) {
        dist.put(points[i], points[i][0] * points[i][0] + points[i][1] * points[i][1]);
        maxHeap.add(points[i]);
        if (maxHeap.size() > K) {
            maxHeap.remove();
        }
    }
    int[][] res = new int[K][2];
    for (int i = 0; i < K; i++) {
        res[i] = maxHeap.remove();
    }
    return res;
}
```

## Solutions
### 1. sort
- use a distance array to store distance, sort, get the Kth closest distance
- iterate the points array and add all with distance <= Kth into result
- O(nlgn) time
```Java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
```

### 2. Divide and Conquer
- Quick select
```Java
import java.util.concurrent.ThreadLocalRandom;

class Solution {
    int[][] points;
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength)
            sort(i, mid - 1, K);
        else if (K > leftLength)
            sort(mid + 1, j, K - leftLength);
    }

    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;

        while (true) {
            while (i < j && dist(i) < pivot)
                i++;
            while (i <= j && dist(j) > pivot)
                j--;
            if (i >= j) break;
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
}
```
