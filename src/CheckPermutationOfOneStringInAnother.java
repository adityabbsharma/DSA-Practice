/*
* 567. Permutation in String
* Medium
*Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.



Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false


Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*
* */

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CheckPermutationOfOneStringInAnother {
    public static void main(String[] args) {
        CheckPermutationOfOneStringInAnother checkPermutationOfOneStringInAnother = new CheckPermutationOfOneStringInAnother();
        System.out.println(checkPermutationOfOneStringInAnother.checkInclusion("dinitrophenylhydrazine", "acetylphenylhydrazine"));


    }
    public boolean checkInclusion(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        Arrays.sort(s1Chars);
        String sortedS1 = new String(s1Chars);

        char[] s2Chars = s2.toCharArray();
        Arrays.sort(s2Chars);
        String sortedS2 = new String(s2Chars);
        if (s1.length()==s2.length() && !sortedS2.equals(sortedS1)) {
            return false;
        }
        Set<String> setOfSubstringsOfS2 = allSubstringsOfS2OfS1Length(s2,s1.length());
        for (String subString: setOfSubstringsOfS2) {
            char[] subChars = subString.toCharArray();
            Arrays.sort(subChars);
            String sortedSubS2 = new String(subChars);
            if (sortedSubS2.equals(sortedS1)) {
                return true;
            }
        }
        return false;
    }
    public Set<String> allSubstringsOfS2OfS1Length(String s2, int length) {
        int i=0;
        Set<String> set = new HashSet<>();
        while (i+length<=s2.length()){
            String s = s2.substring(i,i+length);
            set.add(s);
            i++;
        }
        return set;
    }
}
