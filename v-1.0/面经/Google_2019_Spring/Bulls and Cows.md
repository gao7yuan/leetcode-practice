# 299. Bulls and Cows
5/22/19
*Medium*

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
```
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
```
Example 2:
```
Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
```
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

## Attempts
- use hash map to record digit and # of occurrences
- meanwhile record bulls
- if a key exists in both maps, take the min of two
- O(n) time, O(n) space
```Java
public String getHint(String secret, String guess) {
    if (secret == null || secret.length() == 0) {
        return "0A0B";
    }
    Map<Integer, Integer> smap = new HashMap<>();
    Map<Integer, Integer> gmap = new HashMap<>();
    int bulls = 0;
    for (int i = 0; i < secret.length(); i++) {
        int s = secret.charAt(i) - '0';
        int g = guess.charAt(i) - '0';
        if (s == g) {
            bulls++;
        } else {
            if (smap.containsKey(s)) {
                smap.put(s, smap.get(s) + 1);
            } else {
                smap.put(s, 1);
            }
            if (gmap.containsKey(g)) {
                gmap.put(g, gmap.get(g) + 1);
            } else {
                gmap.put(g, 1);
            }
        }
    }
    int cows = 0;
    for (Integer s : smap.keySet()) {
        if (gmap.containsKey(s)) {
            cows += Math.min(smap.get(s), gmap.get(s));
        }
    }
    return bulls + "A" + cows + "B";
}
```
