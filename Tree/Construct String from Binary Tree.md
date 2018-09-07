# 606. Construct String From Binary Tree
*Easy* *二刷* *忽略stack方法*
7/31/18

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

**Example 1:**
```
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"
```
**Explanation:** Originallay it needs to be "1(2(4)())(3()())",
but you need to omit all the unnecessary empty parenthesis pairs.
And it will be "1(2(4))(3)".
**Example 2:**
```
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4

Output: "1(2()(4))(3)"
```
**Explanation:** Almost the same as the first example,
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

## Attempts
* Pre-order traversal using recursion.
```
class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        StringBuilder str = new StringBuilder();
        str.append(t.val);
        if (t.left != null)
            str.append("(").append(tree2str(t.left)).append(")");
        else if (t.right != null) str.append("()");
        if (t.right != null)
            str.append("(").append(tree2str(t.right)).append(")");
        return str.toString();
    }
}
```

## Solution
* Another option is to use a stack.
  - Use a stack to do pre-order traversal. Use a set to keep track of visited node.
  - Stack is a Java class, but it is better to use Deque.
  - StringBuilder methods:
    - ```String substring(int start, int end)``` start is inclusive, end is exclusive.
    - ```int length()```  
```
public String tree2str(TreeNode t) {
        if (t == null) return "";
        Stack < TreeNode > stack = new Stack < > ();
        stack.push(t);
        Set < TreeNode > visited = new HashSet < > ();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }
```        
