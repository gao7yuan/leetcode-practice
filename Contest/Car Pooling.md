# 1094. Car Pooling
6/22/19
*Medium*

You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.



Example 1:
```
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
```
Example 2:
```
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
```
Example 3:
```
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
```
Example 4:
```
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
```


Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000


## Attempts
- Greedy, similar as Meeting Room II
  - sort array by start_location
  - create heap that stores index of trips, sort by end_location
- O(nlgn) time, space can be optimized...
```Java
public boolean carPooling(int[][] trips, int capacity) {
    // sort trips by start time
    Arrays.sort(trips, (trip1, trip2) -> trip1[1] - trip2[1]);
    PriorityQueue<Integer> heap = new PriorityQueue<>(10000001, (ind1, ind2) -> trips[ind1][2] - trips[ind2][2]); // min heap for end loc, store index of trip for num_pass times
    // init
    for (int i = 0; i < trips[0][0]; i++) {
        heap.add(0);
        if (heap.size() > capacity) {
            return false;
        }
    }
    // others
    for (int i = 1; i < trips.length; i++) {
        while (!heap.isEmpty()) {
            int prevInd = heap.peek();
            if (trips[prevInd][2] > trips[i][1]) {
                // prev not ended yet
                break;
            }
            // if prev ended, remove
            heap.remove();
        }
        for (int j = 0; j < trips[i][0]; j++) {
            heap.add(i);
            if (heap.size() > capacity) {
                return false;
            }
        }
    }
    return true;

}
```
- can optimize space and use a variable to keep track of total people on car

## Option
- another way to do it is use an array to record number of people to get of at each stop
- 模拟题
