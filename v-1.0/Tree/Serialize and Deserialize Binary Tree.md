# 297. Serialize and Deserialize Binary Tree
5/29/19
*Hard*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:
```
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
```
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

## Solutions
```Java

// Serialization
public class Codec {
  public String rserialize(TreeNode root, String str) {
    // Recursive serialization.
    if (root == null) {
      str += "null,";
    } else {
      str += str.valueOf(root.val) + ",";
      str = rserialize(root.left, str);
      str = rserialize(root.right, str);
    }
    return str;
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    return rserialize(root, "");
  }
};


public class Codec {
  public TreeNode rdeserialize(List<String> l) {
    // Recursive deserialization.
    if (l.get(0).equals("null")) {
      l.remove(0);
      return null;
    }

    TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
    l.remove(0);
    root.left = rdeserialize(l);
    root.right = rdeserialize(l);

    return root;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] data_array = data.split(",");
    List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
    return rdeserialize(data_list);
  }
};
```

- my code
  - note: use list is easier to handle pointers than using an array
```Java
// Encodes a tree to a single string.
public String serialize(TreeNode root) {
    StringBuilder str = new StringBuilder();
    str = dfsSerialize(str, root);
    System.out.println(str.toString());
    return str.toString();
}

StringBuilder dfsSerialize(StringBuilder str, TreeNode cur) {
    if (cur == null) {
        str.append("null").append(",");
        return str;
    }
    str.append(cur.val).append(",");
    str = dfsSerialize(str, cur.left);
    str = dfsSerialize(str, cur.right);

    return str;
}

// Decodes your encoded data to tree.
public TreeNode deserialize(String data) {
    String[] arr = data.split(",");
    List<String> list = new LinkedList<>(Arrays.asList(arr));
    TreeNode root = dfsDeserialize(list);
    return root;
}

TreeNode dfsDeserialize(List<String> list) {
    String value = list.get(0);
    if (value.equals("null")) {
        list.remove(0);
        return null;
    }
    TreeNode cur = new TreeNode(Integer.parseInt(value));
    list.remove(0);
    cur.left = dfsDeserialize(list);
    cur.right = dfsDeserialize(list);
    return cur;
}
}
```
