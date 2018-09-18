# 590. N-ary Tree Postorder Traversal
*Easy* *二刷* *已整理*
8/1/18

Given an n-ary tree, return the postorder traversal of its nodes' values.
Note: Recursive solution is trivial, could you do it iteratively?

## Attempts
* Stack.
  - Need to empty the children of a node once the children are pushed to the stack. Otherwise when checking ```!cur.children.isEmpty()``` again, it always returns true.
```
public List<Integer> postorder(Node root) {
        List<Integer> output = new ArrayList<>();
        if (root == null) return output;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            if (!cur.children.isEmpty()) {
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.push(cur.children.get(i));
                }
                cur.children = new ArrayList<>();
            } else {
                output.add(stack.pop().val);
            }
        }
        return output;
    }
```
* Recursion.
```
    public List<Integer> postorder(Node root) {
    List<Integer> output = new ArrayList<>();
    if (root == null) return output;

    if (!root.children.isEmpty()) {
        for (Node node : root.children) {
            output.addAll(postorder(node));
        }
    }
    output.add(root.val);
    return output;
}
```

## Solution
* An alternative way to work with stack: do a somewhat preorder traversal (but node-right-left) and then reverse the list.
```
public List<Integer> postorder(Node root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) return list;

    Stack<Node> stack = new Stack<>();
    stack.add(root);

    while(!stack.isEmpty()) {
        root = stack.pop();
        list.add(root.val);
        for(Node node: root.children)
            stack.add(node);
    }
    Collections.reverse(list);
    return list;
}
```
