package DP;

/*
https://leetcode.com/problems/decode-ways/description/
You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: s = "12"
Output: 2

Explanation:

"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: s = "226"

Output: 3

Explanation:

"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:

Input: s = "06"

Output: 0

Explanation:

"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

 

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).

*/

public class DecodeWays {
    
    // TODO: Implement numDecodings method
    // Given a string s containing only digits, return the number of ways to decode it
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        // dp[i] represents the number of ways to decode substring s[0...i-1]
        int[] dp = new int[n + 1];
        
        // Base case: empty string has 1 way to decode
        dp[0] = 1;
        // Base case: first character (if not '0') has 1 way
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            
            // If current digit is valid (1-9), add ways from previous position
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            
            // If two-digit number is valid (10-26), add ways from two positions back
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        DecodeWays solution = new DecodeWays();
        
        // Test Case 1: Example from problem description
        // "12" can be decoded as "AB" (1 2) or "L" (12)
        System.out.println("Test Case 1:");
        String s1 = "12";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + solution.numDecodings(s1));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test Case 2: Example from problem description
        // "226" can be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
        System.out.println("Test Case 2:");
        String s2 = "226";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + solution.numDecodings(s2));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test Case 3: Example from problem description
        // "06" cannot be decoded (leading zero is invalid)
        System.out.println("Test Case 3:");
        String s3 = "06";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + solution.numDecodings(s3));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 4: Single digit
        System.out.println("Test Case 4:");
        String s4 = "1";
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Output: " + solution.numDecodings(s4));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test Case 5: Single digit zero (invalid)
        System.out.println("Test Case 5:");
        String s5 = "0";
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("Output: " + solution.numDecodings(s5));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 6: Example from problem description
        // "11106" can be decoded as "AAJF" (1, 1, 10, 6) or "KJF" (11, 10, 6)
        System.out.println("Test Case 6:");
        String s6 = "11106";
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("Output: " + solution.numDecodings(s6));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test Case 7: All single digits
        System.out.println("Test Case 7:");
        String s7 = "1234";
        System.out.println("Input: \"" + s7 + "\"");
        System.out.println("Output: " + solution.numDecodings(s7));
        System.out.println("Expected: 3");
        System.out.println("Explanation: (1,2,3,4), (12,3,4), (1,23,4)");
        System.out.println();
        
        // Test Case 8: Contains zero in middle (valid if preceded by 1 or 2)
        System.out.println("Test Case 8:");
        String s8 = "2101";
        System.out.println("Input: \"" + s8 + "\"");
        System.out.println("Output: " + solution.numDecodings(s8));
        System.out.println("Expected: 1");
        System.out.println("Explanation: Only (21, 0, 1) is invalid, so (2, 10, 1)");
        System.out.println();
        
        // Test Case 9: Large number of ways
        System.out.println("Test Case 9:");
        String s9 = "1111";
        System.out.println("Input: \"" + s9 + "\"");
        System.out.println("Output: " + solution.numDecodings(s9));
        System.out.println("Expected: 5");
        System.out.println("Explanation: (1,1,1,1), (11,1,1), (1,11,1), (1,1,11), (11,11)");
        System.out.println();
        
        // Test Case 10: String starting with zero
        System.out.println("Test Case 10:");
        String s10 = "012";
        System.out.println("Input: \"" + s10 + "\"");
        System.out.println("Output: " + solution.numDecodings(s10));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 11: String with invalid two-digit (27-99)
        System.out.println("Test Case 11:");
        String s11 = "27";
        System.out.println("Input: \"" + s11 + "\"");
        System.out.println("Output: " + solution.numDecodings(s11));
        System.out.println("Expected: 1");
        System.out.println("Explanation: Only (2, 7) - cannot use 27");
        System.out.println();
        
        // Test Case 12: Empty string
        System.out.println("Test Case 12:");
        String s12 = "";
        System.out.println("Input: \"" + s12 + "\"");
        System.out.println("Output: " + solution.numDecodings(s12));
        System.out.println("Expected: 0");
        System.out.println();
    }
}
