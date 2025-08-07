/*
* 49. Group Anagrams
*
* Given an array of strings strs, group the anagrams together. You can return the answer in any order.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]



Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*
* */

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        // iterate and lexicographacially order the string
        // store the lexicographacially ordered string as key and a list of Strings as
        // value. After ordering string see if there exists a key for this , then add
        // the original string to that list.
        // finally collect all the lists and construct another list and return it
        Map<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            String sorted = sortString(str);
            if(map.containsKey(sorted)) {
                map.get(sorted).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sorted, list);
            }
        }
        List<List<String>> finalList = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            finalList.add(entry.getValue());
        }
        return finalList;
    }

    public String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);  // Lexicographical sort
        return new String(chars);
    }
}
