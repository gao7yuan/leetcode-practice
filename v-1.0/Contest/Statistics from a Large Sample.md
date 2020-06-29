# 1093. Statistics from a Large Sample
6/22/19
*Medium*

We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.

Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.

(Recall that the median of a sample is:

The middle element, if the elements of the sample were sorted and the number of elements is odd;
The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)


Example 1:
```
Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
```
Example 2:
```
Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
```

Constraints:

count.length == 256
1 <= sum(count) <= 10^9
The mode of the sample that count represents is unique.
Answers within 10^-5 of the true value will be accepted as correct.

## Attempts
- straightforward...
```Java
public double[] sampleStats(int[] count) {
    double min = 0.0; //
    double max = 0.0; //
    int totalCount = 0;
    double mode = 0.0; //
    int maxCount = 0;
    double sum = 0.0;
    // find min
    for (int i = 0; i < 256; i++) {
        if (count[i] != 0) {
            min = i;
            break;
        }
    }
    //
    for (int i = 0; i < 256; i++) {
        // update sum and total count
        sum += count[i] * i;
        totalCount += count[i];
        // update max
        if (count[i] != 0) {
            max = i;
        }
        // update max count for mode
        if (count[i] > maxCount) {
            maxCount = count[i];
            mode = i;
        }
    }
    double mean = sum / totalCount; //

    double median = 0;

    if (totalCount % 2 == 0) {
        int which = totalCount / 2;
        int cur = 0;
        for (int i = 0; i < 256; i++) {
            cur += count[i];
            if (cur >= which) {
                median = i;
                break;
            }
        }
        which++;
        cur = 0;
        for (int i = 0; i < 256; i++) {
            cur += count[i];
            if (cur >= which) {
                median = (median + i) / 2;
                break;
            }
        }
    } else {
        int which = totalCount / 2 + 1;
        int cur = 0;
        for (int i = 0; i < 256; i++) {
            cur += count[i];
            if (cur >= which) {
                median = i;
                break;
            }
        }
    }
    double[] res = new double[5];
    res[0] = min;
    res[1] = max;
    res[2] = mean;
    res[3] = median;
    res[4] = mode;
    return res;
}
```
