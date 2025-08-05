/*
* 128. Longest Consecutive Sequence
* Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Example 3:

Input: nums = [1,0,1,2]
Output: 3


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
*
*
* */


import java.util.Arrays;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // sort the array
        // iterate the sorted array
        // keep maxLength, currentMaxLength
        // initially maxLength zero
        // for the first index, increment it to 1
        // if the next index element is +1 of last index
        // then increment currentMaxLength by 1
        // update maxLength = Math.max(maxLength,currentMaxLength)
        // do this in each iteration step
        Arrays.sort(nums);
        int n = nums.length;
        int maxLength=1, currentMaxLength=1;
        if(n==0) {
            return 0;
        }
        for(int i=1;i<n;i++) {
            if((nums[i]-nums[i-1])==1) {
                currentMaxLength++;
            } else if(nums[i]-nums[i-1]>1) {
                currentMaxLength=1;
            }
            maxLength=Math.max(maxLength,currentMaxLength);
        }
        return maxLength;
    }
}
