# 406. Queue Reconstruction by Height
*Greedy*
10/23/18

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example
```
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

## Solutions (my code after reading discussion)
* sort people for height from high to low first, if height is same, sort k from low to high
* 创建一个arraylist。好处是可以按照index insert，并且是O(1)。
* 将sort好的people按照k值插入list。这样能保证每次插入后k值都符合条件。
* 完成后将list转换成int[][]

注意
* Comparator, compare, lambda用法
```
public int[][] reconstructQueue(int[][] people) {
    if (people == null || people.length == 0 || people[0].length == 0) {
        return people;
    }

    // sort for height from high to low, if height is same, k from low to high
    Arrays.sort(people, new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        }
    });

    List<int[]> queue = new ArrayList<>();
    for (int i = 0; i < people.length; i++) {
        queue.add(people[i][1], people[i]);
    }

    int[][] res = new int[people.length][2];
    int i = 0;
    for (int[] person : queue) {
        res[i++] = person;
    }
    return res;
}
```
