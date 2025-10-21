/*
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 * 
 */

package Binary_Tree;

public class MaximumPathSum {
    
    
    
    // TODO: Implement to find the maximum path sum in a binary tree
    // A path can start and end at any node (doesn't need to go through root)
    // Path: sequence of nodes where each pair has an edge, node appears at most once
    public int maxPathSum(TreeNode root) {
        int[] maxi =  new int[]{Integer.MIN_VALUE};
        // Your implementation here
        maxPathSumHelper(root, maxi);
        return maxi[0];
    }
    
    // Helper method - returns the maximum path sum starting from current node
    // going down to its descendants (single path, not splitting)
    private int maxPathSumHelper(TreeNode node, int[] maxi) {
        // Your implementation here
        if(node == null) {
            return 0;
        }
        int leftMax = Math.max(0, maxPathSumHelper(node.left, maxi));
        int rightMax = Math.max(0, maxPathSumHelper(node.right, maxi));
        maxi[0] = Math.max(maxi[0], node.val+leftMax+rightMax);
        return node.val + Math.max(leftMax, rightMax);
    }
    
    public static void main(String[] args) {
        MaximumPathSum solution = new MaximumPathSum();
        
        // Test Case 1: Simple tree (from example 1)
        //     1
        //    / \
        //   2   3
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 2, 3});
        System.out.println("Tree: [1, 2, 3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root1));
        System.out.println("Expected: 6 (path: 2 -> 1 -> 3)");
        System.out.println();
        
        // Test Case 2: Tree with negative values (from example 2)
        //      -10
        //      /  \
        //     9    20
        //         /  \
        //        15   7
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println("Tree: [-10, 9, 20, null, null, 15, 7]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root2));
        System.out.println("Expected: 42 (path: 15 -> 20 -> 7)");
        System.out.println();
        
        // Test Case 3: Single node
        System.out.println("Test Case 3:");
        TreeNode root3 = TreeNode.createTree(new Integer[]{-3});
        System.out.println("Tree: [-3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root3));
        System.out.println("Expected: -3");
        System.out.println();
        
        // Test Case 4: All negative values
        //      -2
        //      /  \
        //    -1   -3
        System.out.println("Test Case 4:");
        TreeNode root4 = TreeNode.createTree(new Integer[]{-2, -1, -3});
        System.out.println("Tree: [-2, -1, -3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root4));
        System.out.println("Expected: -1 (path: single node -1)");
        System.out.println();
        
        // Test Case 5: Left-skewed tree with mixed values
        //      5
        //     /
        //    4
        //   /
        //  3
        System.out.println("Test Case 5:");
        TreeNode root5 = TreeNode.createTree(new Integer[]{5, 4, null, 3});
        System.out.println("Tree: [5, 4, null, 3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root5));
        System.out.println("Expected: 12 (path: 3 -> 4 -> 5)");
        System.out.println();
        
        // Test Case 6: Complex tree
        //        2
        //       / \
        //     -1   3
        //     /
        //    4
        System.out.println("Test Case 6:");
        TreeNode root6 = TreeNode.createTree(new Integer[]{2, -1, 3, 4});
        System.out.println("Tree: [2, -1, 3, 4]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root6));
        System.out.println("Expected: 8 (path: 4 -> -1 -> 2 -> 3)");
        System.out.println();
        
        // Test Case 7: Large positive values
        //       10
        //       / \
        //      2   10
        //     / \    \
        //    20  1   -25
        //       / \   / \
        //      3  4  3  4
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{10, 2, 10, 20, 1, null, -25, null, null, 3, 4, 3, 4});
        System.out.println("Tree: [10, 2, 10, 20, 1, null, -25, null, null, 3, 4, 3, 4]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root7));
        System.out.println("Expected: 42 (path: 20 -> 2 -> 10 -> 10)");
        System.out.println();
        
        // Test Case 8: Tree where path is single straight line
        //      1
        //       \
        //        2
        //         \
        //          3
        System.out.println("Test Case 8:");
        TreeNode root8 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3});
        System.out.println("Tree: [1, null, 2, null, 3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root8));
        System.out.println("Expected: 6 (path: 1 -> 2 -> 3)");
    }
}
