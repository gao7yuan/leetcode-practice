# 621. Task Scheduler
*Medium*
10/30/18

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:
```
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
```

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

## Solutions
* Sorting
  - 先将array排序，依次将出现次数最大的任务排入队列，如果任务数量不够就idle。总体而言每次time++
  - 每排入n+1个任务之后，重新sort array，重复这个操作，直到所有任务都消失
  - O(time) time, O(1) space
```
public int leastInterval(char[] tasks, int n) {
    int[] map = new int[26];
    for (char task : tasks) {
        map[task - 'A']++;
    }
    Arrays.sort(map);
    int time = 0;
    // while there is task left undone
    while (map[25] > 0) {
        // from the task with highest frequency to next iteration, there are n + 1 tasks in total
        int i = 0;
        while (i <= n) {
            // if all tasks are finished
            if (map[25] == 0) {
                break;
            }
            // if there is still available tasks, put the task on task line, otherwise idle
            if (i < 26 && map[25 - i] > 0) {
                map[25 - i]--;
            }
            time++;
            i++;
        }
        Arrays.sort(map);
    }
    return time;
}
```
* PriorityQueue
  - 注意用temp暂时存放更新后的frequency
  - 注意只有frequency > 0才需要加入queue
```
public int leastInterval(char[] tasks, int n) {
    int[] map = new int[26];
    for (char task : tasks) {
        map[task - 'A']++;
    }
    int time = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
    for (int f : map) {
        if (f > 0) {
            queue.add(f);
        }   
    }
    while (!queue.isEmpty()) {
        List<Integer> temp = new LinkedList<>();
        int i = 0;
        while (i <= n) {
            if (!queue.isEmpty()) {
                if (queue.peek() > 1) {
                    temp.add(queue.poll() - 1);
                } else {
                    queue.poll();
                }
            }
            time++;
            if (queue.isEmpty() && temp.size() == 0) {
                break;
            }
            i++;
        }
        for (Integer f : temp) {
            queue.add(f);
        }
    }
    return time;
}
```
