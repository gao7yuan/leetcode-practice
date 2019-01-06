# 95. Unique Binary Search Trees II
1/5/19
*Medium*

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:
```
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

## Attempts
* Recursion
  - 遍历1-n,每个作为root一次，recursion在左右分别产生一个list，将list里的node分别attach到root上
  - 一开始的implementation没有注意到新建的root的scope，有些是global但是在for loops里面被改变了
```Java
public List<TreeNode> generateTrees(int n) {
    return bst (1, n);
}

private List<TreeNode> bst(int start, int end) {
    List<TreeNode> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
        List<TreeNode> left = bst(start, i - 1);
        List<TreeNode> right = bst(i + 1, end);
        if (left.size() > 0) {
            for (TreeNode leftTree : left) {
                if (right.size() > 0) {
                    for (TreeNode rightTree : right) {
                        TreeNode root = new TreeNode(i); // have to be local!
                        root.left = leftTree;
                        root.right = rightTree;
                        list.add(root);
                    }
                } else {
                    TreeNode root = new TreeNode(i); // have to be local!
                    root.left = leftTree;
                    list.add(root);
                }
            }
        } else if (right.size() > 0) {
            for (TreeNode rightTree : right) {
                TreeNode root = new TreeNode(i);
                root.right = rightTree;
                list.add(root);
            }
        } else {
            list.add(new TreeNode(i));
        }
    }
    return list;
}
```

* 改进，不需要分情况讨论left right是否存在.在list里面加null.因为list是List<TreeNode>，加上null的话list是有元素的，不会有size = 0的情况。但是要注意edge case，当n=0的时候，list是[[]]，所以需要单独讨论.

```Java
public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
        return new ArrayList<>();
    }
    return bst (1, n);
}

private List<TreeNode> bst(int start, int end) {
    List<TreeNode> list = new ArrayList<>();
    if (start > end) {
        list.add(null);
        return list;
    }
    for (int i = start; i <= end; i++) {
        List<TreeNode> left = bst(start, i - 1);
        List<TreeNode> right = bst(i + 1, end);

        for (TreeNode leftTree : left) {
            for (TreeNode rightTree : right) {
                TreeNode root = new TreeNode(i);
                root.left = leftTree;  
                root.right = rightTree;
                list.add(root);
            }
        }
    }
    return list;
}
```
