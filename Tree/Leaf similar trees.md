# 872. Leaf Similar Trees
*Easy* *二刷*
7/29/18

Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.

```
        3
     /     \
    5       1
   / \     / \
  6   2   9   8
     / \
    7   4
```

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

**Note:**

Both of the given trees will have between 1 and 100 nodes.

## Attempts
* DFS. Generate sequence first and check if sequences are identical.
* O(n) time. O(n) space(?).
```
import java.util.List;
import java.util.ArrayList;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> sequence1 = new ArrayList<>();
        List<Integer> sequence2 = new ArrayList<>();
        sequence1 = leafValueSequence(sequence1, root1);
        sequence2 = leafValueSequence(sequence2, root2);
        if (sequence1.size() != sequence2.size()) return false;
        for (int i = 0; i < sequence1.size(); i++) {
            if (!sequence1.get(i).equals(sequence2.get(i))) return false;
        }
        return true;
    }

    List<Integer> leafValueSequence(List<Integer> sequence, TreeNode root) {
        if (root == null) return sequence;
        if (root.left == null && root.right == null) {
            sequence.add(root.val);
            return sequence;
        }
        if (root.left == null) return leafValueSequence(sequence, root.right);
        return leafValueSequence(leafValueSequence(sequence, root.left), root.right);  
    }
}
```

## Solutions
* Common idea: DFS whole tree to a list and compare lists.
* An idea of O(logn) space (why?) by comparing node by node.
  - Use a stack<TreeNode> to keep dfs path.
  - dfs(stack) will return next leaf.
  - Check leaves one by one, until the end or difference.
  - Only O(logN) space for stack, no extra space for comparison.
```
public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
    s1.push(root1); s2.push(root2);
    while (!s1.empty() && !s2.empty())
        if (dfs(s1) != dfs(s2)) return false;
    return s1.empty() && s2.empty();
}

public int dfs(Stack<TreeNode> s) {
    while (true) {
        TreeNode node = s.pop();
        if (node.right != null) s.push(node.right);
        if (node.left != null) s.push(node.left);
        if (node.left == null && node.right == null) return node.val;
    }
}
```
