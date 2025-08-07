/*
* 3. Longest Substring Without Repeating Characters
* Given a string s, find the length of the longest substring without duplicate characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
*
*
* */


import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcadaadvcw"));
    }

    public int lengthOfLongestSubstring(String s) {
        // sliding window
        // max of the difference between the left and right pointers is the answer
        int p1=0,p2=0,max=0;
        Set<Character> set = new HashSet<>();
        char[] charArr = s.toCharArray();
        while (p2<s.length()) {
            if(!set.contains(charArr[p2])) {
                set.add(charArr[p2]);
                max = Math.max(max, p2-p1+1);
                p2++;
            } else {
                set.remove(charArr[p1]);
                p1++;
            }
        }
        return max;
    }
}
