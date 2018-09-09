# 653. Two-sum IV - input is a BST
*Easy* *二刷*
7/30/18

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

**Example 1:**
```
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
```
**Example 2:**
```
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
```
## Attempts
* （看了答案提示。。。）
* In order traverse BST into a list.
* i and j index at head and tail. Similar as two sum with sorted array.
```
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        List<Integer> list = inorder(root);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i) + list.get(j) == k) return true;
            if (list.get(i) + list.get(j) < k) i++;
            else j--;
        }
        return false;
    }

    List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.addAll(inorder(root.left));
        list.add(root.val);
        list.addAll(inorder(root.right));
        return list;
    }
}
```

## Solution
* Approach 1: HashSet (DFS). Traverse the tree for once. For each value v, check if k-v exists in a HashSet. If not, add this v into the HashSet.
  - ```contains()``` of HashSet is O(1) time.
  - Time: O(n)
  - Space: O(n)
```
public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
}
```
* Approach 2: BFS and HashSet.
  - Similar as Approach 1 but using a queue to BFS the tree.
```
public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        Queue < TreeNode > queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else
                queue.remove();
        }
        return false;
    }
}
```
* Approach 3: BST -> List, then similar as two sum with sorted array.
An optional way of implementing ```inorder()```:
```
public void inorder(TreeNode root, List < Integer > list) {
       if (root == null) return;
       inorder(root.left, list);
       list.add(root.val);
       inorder(root.right, list);
   }
```   
