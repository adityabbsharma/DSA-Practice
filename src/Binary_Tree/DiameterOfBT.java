/*
 * 543. Diameter of Binary Tree
Easy
Topics
premium lock icon
Companies
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

 

Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
 * 
 */


package Binary_Tree;

public class DiameterOfBT {
    
    
    // TODO: Implement to find the diameter of a binary tree
    // Diameter = length of longest path between any two nodes (number of edges)
    // Path may or may not pass through root
    public int diameterOfBinaryTree(TreeNode root) {
         
        // Your implementation here
        int[] maxDia = new int[]{0};
        helperDiameter(root, maxDia);
        return maxDia[0];
    }

    
    // Helper method - returns the height of the tree rooted at node
    // and updates the diameter
    private int helperDiameter(TreeNode node, int[] maxDia) {
        // Your implementation here
        
        if (node==null) {
            return 0;
        }
        int leftHeight = helperDiameter(node.left, maxDia);
        int rightHeight = helperDiameter(node.right, maxDia);
        maxDia[0] = Math.max(maxDia[0], leftHeight+rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public static void main(String[] args) {
        DiameterOfBT solution = new DiameterOfBT();
        
        // Test Case 1: Example from problem (from example 1)
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root1));
        System.out.println("Expected: 3 (path: 4 -> 2 -> 1 -> 3 or 5 -> 2 -> 1 -> 3)");
        System.out.println();
        
        // Test Case 2: Simple two-node tree (from example 2)
        //     1
        //    /
        //   2
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{1, 2});
        System.out.println("Tree: [1, 2]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root2));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test Case 3: Single node
        System.out.println("Test Case 3:");
        TreeNode root3 = TreeNode.createTree(new Integer[]{1});
        System.out.println("Tree: [1]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root3));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 4: Left-skewed tree
        //     1
        //    /
        //   2
        //  /
        // 3
        System.out.println("Test Case 4:");
        TreeNode root4 = TreeNode.createTree(new Integer[]{1, 2, null, 3});
        System.out.println("Tree: [1, 2, null, 3]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root4));
        System.out.println("Expected: 2 (path: 3 -> 2 -> 1)");
        System.out.println();
        
        // Test Case 5: Right-skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        System.out.println("Test Case 5:");
        TreeNode root5 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3, null, 4});
        System.out.println("Tree: [1, null, 2, null, 3, null, 4]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root5));
        System.out.println("Expected: 3 (path: 1 -> 2 -> 3 -> 4)");
        System.out.println();
        
        // Test Case 6: Complete binary tree
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        System.out.println("Test Case 6:");
        TreeNode root6 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root6));
        System.out.println("Expected: 4 (path: 4 -> 2 -> 1 -> 3 -> 6 or similar)");
        System.out.println();
        
        // Test Case 7: Diameter not passing through root
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        //    / \
        //   6   7
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, null, null, 6, 7});
        System.out.println("Tree: [1, 2, 3, 4, 5, null, null, 6, 7]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root7));
        System.out.println("Expected: 4 (path: 6 -> 4 -> 2 -> 5 or 7 -> 4 -> 2 -> 5)");
        System.out.println();
        
        // Test Case 8: Unbalanced tree with longer left subtree
        //       1
        //      /
        //     2
        //    / \
        //   3   4
        //  /
        // 5
        System.out.println("Test Case 8:");
        TreeNode root8 = TreeNode.createTree(new Integer[]{1, 2, null, 3, 4, 5});
        System.out.println("Tree: [1, 2, null, 3, 4, 5]");
        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root8));
        System.out.println("Expected: 3 (path: 5 -> 3 -> 2 -> 4)");
    }
}
