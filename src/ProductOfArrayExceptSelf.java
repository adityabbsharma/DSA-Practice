/*
* 238 Product of Array Except Self
* Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)


*
* */

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        // iterate from left to right
        // for each index calculate left product excluding that index
        // similarly iterate from right to left
        // for each index calculate right product excluding that index
        // iterate once more and now construct output array
        // use the output array to store the left product first and then
        // use the same in the second iteration to multiply the right product
        int n=nums.length;
        int[] left = new int[n];
        left[0] = 1;
        for(int i=1;i<n;i++) {
            left[i] = nums[i-1] * left[i-1]; // [2,4,7,9] left[1] = 2, left[2]=4*2,left[3]=7*8
        }
        int[] right = new int[n];
        int[] out = new int[n];
        out[n-1] = left[n-1];
        right[n-1] = 1;
        for(int i=n-2;i>=0;i--) {
            right[i] = nums[i+1] * right[i+1];
            out[i] = left[i] * right[i];
        }
        return out;
    }
}
