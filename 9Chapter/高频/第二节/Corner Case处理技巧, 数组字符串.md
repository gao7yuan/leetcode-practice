# Corner case处理技巧, 数组、字符串、栈与队列，快速点题

前缀和数组
- 数组a，s是它的前缀和数组
  - a[i] + a[i+1] + ... + a[j] = s[j] - s[i-1]
- 改滚动

## Missing Ranges

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:
```
Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
```
- 思路
  - 简单的模拟题
    - 两端点和一头一尾形成的区间 + for循环扫描中间形成的区间
    - 利用函数让自己的代码更简洁
  - 特殊输入
    - int范围
    - 去掉的点为空
  - O(n) time

  - 解决的问题
    1. long
    2. 空数组

```Java
public List<String> findMissingRanges(int[] nums, int lower, int upper) {
  List<String> ans = new ArrayList<>();

  if (nums == null || nums.length == 0) {
    addRange(ans, lower, upper);
    return ans;
  }

  addRange(ans, lower, (long)nums[0] - 1);

  for (int i = 1; i < nums.length; i++) {
    addRange(ans, (long)nums[i - 1] + 1, (long)nums[i] - 1);
  }
  addRange(ans, (long)nums[nums.length - 1] + 1, upper);
  return ans;
}
void addRange(List<String> ans, long st, long ed) {
  if (st > ed) {
    return;
  }
  if (st == ed) {
    ans.add(st + "");
    return;
  }
  ans.add(st + "->" + ed);
}
```

## Valid Number

Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.

- 思路
  - 一个数怎么构成：
    - 符号 + 浮点数 + e + 符号 + 整数
    - 浮点数必须: e.g. 3.56
    - ( )(+-)3.56(e(+-)5)( )
  - O(n) time
```Java
public boolean isNumber(String s) {
    int i = 0;
    s = s.trim() + " "; // get rid of leading and ending white space. " " used to limit i within length
    char[] sc = s.toCharArray();
    int len = sc.length - 1;

    if (sc[i] == '+' || sc[i] == '-') {
        i++;
    }

    int nDigit = 0, nPoint = 0;
    while (Character.isDigit(sc[i]) ||sc[i] == '.') {
        if (Character.isDigit(sc[i])) {
            nDigit++;
        }
        if (sc[i] == '.') {
            nPoint++;
        }
        i++;
    }
    if (nDigit <= 0 || nPoint > 1) {
        return false;
    }

    if (sc[i] == 'e') {
        i++;
        if (sc[i] == '+' || sc[i] == '-') {
            i++;
        }
        // if after e, there is no number, false
        if (i == len) {
            return false;
        }
        for (; i < len; i++) {
            if (!Character.isDigit(sc[i])) {
                return false;
            }
        }
    }
    return i == len;
}
```

## Moving Average from Data Stream

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
- 九章用的是数组或者list但是我觉得太麻烦
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

## Encode and Decode Strings
- Google

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
```
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
```
Machine 2 (receiver) has the function:
```
vector<string> decode(string s) {
  //... your code
  return strs;
}
```
So Machine 1 does:
```
string encoded_string = encode(strs);
```
and Machine 2 does:
```
vector<string> strs2 = decode(encoded_string);
```
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.

- Hints from 九章
  - 考虑escape character的意义。`\n`, `\\`
  - 用`:`表示转义，`:;`表示`;`（分隔符），`::`表示`:`，`;`还是`;`
```Java
// Encodes a list of strings to a single string.
public String encode(List<String> strs) {
    StringBuilder res = new StringBuilder();
    for (String str : strs) {
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (c == ':') {
                res.append("::");
            } else {
                res.append(c);
            }
        }
        res.append(":;");
    }
    return res.toString();
}

// Decodes a single string to a list of strings.
public List<String> decode(String s) {
    List<String> list = new ArrayList<>();
    StringBuilder str = new StringBuilder();
    char[] cs = s.toCharArray();
    for (int i = 0; i < cs.length; i++) {
        if (cs[i] == ':') {
            i++;
            if (cs[i] == ':') {
                str.append(":");
            } else {
                // cs[i] == ';'
                list.add(str.toString());
                str = new StringBuilder();
            }
        } else {
            str.append(cs[i]);
        }
    }
    return list;
}
```

## Longest Absolute File Path
Suppose we abstract our file system by a string in the following manner:

`The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"` represents:
```
dir
    subdir1
    subdir2
        file.ext
```
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string `"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"` represents:
```
dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
```
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that `a/aa/aaa/file1.txt` is not the longest file path, if there is another path `aaaaaaaaaaaaaaaaaaaaa/sth.png`.

- 有点麻烦的纯模拟
  1. 用`split('\n')`将原串分割开，相当于一次读一行
  2. 利用`\t`的个数来记录当前在第几层
  3. 从上到下一行一行读，用stack操作，把前面几层的字符串长度记下来

## Read N Characters Given Read4 II - Call multiple times
- 难点
  - 如果只读3个，剩下的一个怎么处理
  - Read4只读了两个怎么办
- 思路
  - 缓冲区的数据结构
    - Queue (FIFO) 因为要保持数据顺序不变
  - 缓冲区队列的逻辑
    - 队列为空时进队(read4)
    - 队列不为空时就满足内存需求，出队
Tag: Goole Facebook
考点
- Queue
- 细节处理
- onsite, 25min

# 例题总结
## Missing ranges
## Valid number
- Corner case
  - overflow maxint
  - empty input
  - out of boundary
- 技巧
  - 先写程序，再考虑corner case
  - 长整型long解决溢出int
  - 数组开大一点（加空格，dummy 0）
## Moving avg from data stream
- 快速求和
  - 前缀和数组
- 节省空间
  - 链表or滚动
- 写滚动的技巧
  - 先写程序后加滚动

# Practice
- String to integer
- One edit distance*
- Merge intervals
- Insert interval*
- Strobogrammic number*
- Interval sum?
- Min stack （前缀和数组）
- Valid Parentheses
- Evaluate Reverse Polish Notation*
