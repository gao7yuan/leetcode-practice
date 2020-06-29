# 271. Encode and Decode Strings
6/19/19
*Medium*

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

## Attempts
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
