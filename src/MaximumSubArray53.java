/*
*
* 53. Maximum Subarray
*
* Given an integer array nums, find the subarray with the largest sum, and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*
* */

public class MaximumSubArray53 {

    public int maxSubArray(int[] nums) {
        //
        int n = nums.length;
        int positiveSum=0,largestSum=nums[0];
        boolean presenceOfPositiveNumber = false;
        for(int i=0;i<n;i++) {
            if(nums[i] > 0) {
                presenceOfPositiveNumber = true;
                break;
            }
        }
        if(!presenceOfPositiveNumber) {
            for(int i=0;i<n;i++) {
                largestSum = Math.max(nums[i],largestSum);
            }
        } else {
            for(int i=0;i<n;i++) {
                if(positiveSum+nums[i]<0) {
                    positiveSum=0;
                } else {
                    positiveSum += nums[i];
                }
                largestSum = Math.max(largestSum,positiveSum);
            }
        }

        return largestSum;
    }

}
