/*
* 152. Maximum Product Subarray
* Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.



Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
*
* */


public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0], min=nums[0];
        int positiveMax=1,negativeMax=1, zeroes=0;
        boolean postiveExist=false, zeroExist=false, minusOneOnly=true, zeroesOnly=true;
        for(int i=0;i<n;i++) {
            if(nums[i]>0) {
                postiveExist = true;
                break;
            } else if (nums[i]==0) {
                zeroExist=true;
                zeroes++;
            } else {
                zeroesOnly=false;
                if(nums[i] != -1) {
                    minusOneOnly = false;
                }
                min = Math.max(min, nums[i]);
            }
        }

        for(int i=0;i<n;i++) {
            if(nums[i]<0 && negativeMax==1) {
                negativeMax = positiveMax * nums[i];
            } else if (nums[i]<0 && negativeMax<0) {
                positiveMax = negativeMax * nums[i];
                negativeMax = 1;
            } else if (nums[i]>0 && negativeMax<0) {
                negativeMax = negativeMax * nums[i];
                positiveMax = Math.max(positiveMax,nums[i]);
            } else if (nums[i]>0 && negativeMax==1) {
                positiveMax = positiveMax * nums[i];
            } else if(nums[i]==0) {
                positiveMax=1;
                negativeMax=1;
            }
            max = Math.max(positiveMax, max);
        }
        int max1 = nums[n-1];
        positiveMax=1;
        negativeMax=1;
        for(int i=n-1;i>=0;i--) {
            if(nums[i]<0 && negativeMax==1) {
                negativeMax = positiveMax * nums[i];
            } else if (nums[i]<0 && negativeMax<0) {
                positiveMax = negativeMax * nums[i];
                negativeMax = 1;
            } else if (nums[i]>0 && negativeMax<0) {
                negativeMax = negativeMax * nums[i];
                positiveMax = Math.max(positiveMax,nums[i]);
            } else if (nums[i]>0 && negativeMax==1) {
                positiveMax = positiveMax * nums[i];
            } else if(nums[i]==0) {
                positiveMax=1;
                negativeMax=1;
            }
            max1 = Math.max(positiveMax, max1);
        }
        max=Math.max(max,max1);
        if(zeroExist && !postiveExist) {
            if(max==1) {
                if(!minusOneOnly || zeroesOnly) {
                    max = 0;
                }
            }
        }
        if (!zeroExist && !postiveExist) {
            if(n>1) {
                max = Math.max(min,max);
            } else {
                max = min;
            }
        }

        return max;
    }
}
