# 155. Min Stack
5/29/19
*Easy*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
```

## Attempts
```Java
class MinStack {

    int size;
    Node top; // the next one to pop

    class Node {
        int val;
        int min;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    /** initialize your data structure here. */
    public MinStack() {
        size = 0;
        top = null;
    }

    public void push(int x) {
        if (size == 0) {
            top = new Node(x);
            top.min = x;
        } else {
            Node newtop = new Node(x);
            top.next = newtop;
            newtop.prev = top;
            newtop.min = Math.min(x, top.min);
            top = newtop;
        }
        size++;
    }

    public void pop() {
        if (size != 0) {
            Node newtop = this.top.prev;
            if (newtop != null) {
                newtop.next = null;
                top.prev = null;
            }
            top = newtop;
            size--;
        }
    }

    public int top() {
        if (size == 0) {
            return Integer.MAX_VALUE;
        } else {
            return top.val;
        }
    }

    public int getMin() {
        return this.top.min;
    }
}
```
