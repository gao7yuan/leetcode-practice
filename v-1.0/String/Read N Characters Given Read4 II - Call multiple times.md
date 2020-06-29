# 158. Read N Characters Given Read4 II - Call multiple times
6/23/19
*Hard*

## Solutions
- 硬盘 ---- 缓冲区 ---- 内存
- FIFO, Queue
  - 内存请求
  - 缓冲是否为空
    - 空：read4，进队
    - 不空：出队
```Java
Queue<Character> q = new LinkedList<>();
char[] buffer = new char[4];

public int read(char[] buf, int n) {
    int readBytes = 0;
    boolean eof = false;
    while (readBytes < n && !eof) {
        if (q.isEmpty()) {
            // if (eof) {
            //     break;
            // }
            int curBytes = read4(buffer);
            if (curBytes != 4) {
                eof = true;
            }
            for (int i = 0; i < curBytes; i++) {
                q.add(buffer[i]);
            }
        }
        while (readBytes < n && !q.isEmpty()) {
            buf[readBytes++] = q.remove();
        }
    }
    return readBytes;
}
```
