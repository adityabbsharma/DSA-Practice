/*
 * Problem: Minimum Operations to Transform Array
 *
 * You are given two integer arrays `nums1` of length n and `nums2` of length n + 1.
 *
 * Operation Rules:
 *  1. Increment `nums1[i]` by 1.
 *  2. Decrement `nums1[i]` by 1.
 *  3. Append the current value `nums1[i]` to the end of the array.
 *
 * Goal: Transform `nums1` into `nums2` using the minimum number of operations.
 *
 * Example 1:
 *  nums1 = [2, 8], nums2 = [1, 7, 3] → minimum operations = 4
 *
 * Example 2:
 *  nums1 = [1, 3, 6], nums2 = [2, 4, 5, 3] → minimum operations = 4
 *
 * Example 3:
 *  nums1 = [2], nums2 = [3, 4] → minimum operations = 3
 *
 * Constraints:
 *  1 <= n == nums1.length <= 1e5
 *  nums2.length == n + 1
 *  1 <= nums1[i], nums2[i] <= 1e5
 *
 * Notes for Implementation:
 *  - Design an efficient algorithm. The naive search will not scale to 1e5 elements.
 *  - Think about how the append operation interacts with index alignment between the two arrays.
 *  - Greedy or dynamic programming approaches may be helpful. Consider prefix/suffix differences.
 */

package contest;

import java.util.Arrays;

public class MinOperationsToTransformArray {

    /**
     * Computes the minimum number of operations required to transform nums1 into nums2.
     *
     * @param nums1 initial array of length n
     * @param nums2 target array of length n + 1
     * @return minimum number of operations
     */
    public int minOperations(int[] nums1, int[] nums2) {
        // TODO: implement the algorithm
        int n = nums1.length, v = nums2[n], last = 100000;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums1[i], b = nums2[i];
            res += Math.abs(a - b);
            if ((a <= v && v <= b) || (b <= v && v <= a))
                last = 0;
            else {
                int d = Math.min(Math.abs(a - v), Math.abs(b - v));
                last = Math.min(last, d);
            }
        }
        return (int) (res + last + 1);
        // throw new UnsupportedOperationException("minOperations is not implemented yet");
    }

    /**
     * Simple manual test harness.
     *
     * Add more scenarios covering corner cases:
     *  - Already matching arrays where only append is needed.
     *  - Large differences requiring many increments/decrements.
     *  - Cases where the optimal strategy defers append until later indices.
     */
    public static void main(String[] args) {
        MinOperationsToTransformArray solver = new MinOperationsToTransformArray();

        int[][] samplesNums1 = {
                {2, 8},
                {1, 3, 6},
                {2},    
                {5, 5, 5},
                {10}
        };

        int[][] samplesNums2 = {
                {1, 7, 3},
                {2, 4, 5, 3},
                {3, 4},
                {5, 5, 5, 5},
                {9, 10}
        };

        System.out.println("Manual test cases for MinOperationsToTransformArray:");
        for (int i = 0; i < samplesNums1.length; i++) {
            try {
                int result = solver.minOperations(samplesNums1[i], samplesNums2[i]);
                System.out.printf("Test %d -> nums1=%s, nums2=%s, operations=%d%n",
                        i + 1,
                        Arrays.toString(samplesNums1[i]),
                        Arrays.toString(samplesNums2[i]),
                        result);
            } catch (UnsupportedOperationException ex) {
                System.out.printf("Test %d -> nums1=%s, nums2=%s, status=NOT IMPLEMENTED%n",
                        i + 1,
                        Arrays.toString(samplesNums1[i]),
                        Arrays.toString(samplesNums2[i]));
            }
        }
    }
}