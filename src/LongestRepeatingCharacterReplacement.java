/*
* 424. Longest Repeating Character Replacement
* You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
*
* */

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // solve with sliding window
        // a map to contain frequency of each character in the window
        // if maxfrequency inside the window goes below the difference between
        // length of window and k then reduce the window from left. And as
        // you are reducing window from left, the left most pointer frequency
        // also decrement. Else go forward by moving the right pointer and
        // increasing the window size. finally return the window size
        int n = s.length();
        int maxCount = 0, left=0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int right=0;right<n;right++) {
            freqMap.put(s.charAt(right),freqMap.getOrDefault(s.charAt(right),0) + 1);
            maxCount = Math.max(maxCount, freqMap.get(s.charAt(right)));
            if((right-left+1) - maxCount > k) {
                freqMap.put(s.charAt(left), freqMap.get(s.charAt(left)) - 1);
                left++;
            }
        }
        return s.length()-left;
    }
}
