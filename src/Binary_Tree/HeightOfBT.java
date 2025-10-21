package Binary_Tree;

public class HeightOfBT {
    
    // TODO: Implement to find the height/max depth of a binary tree
    // Height = number of edges on the longest path from root to leaf
    // OR Max Depth = number of nodes on the longest path from root to leaf
    // (This implementation uses Max Depth definition - counting nodes)
    public int maxDepth(TreeNode root) {
        // Your implementation here
        if (root==null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public static void main(String[] args) {
        HeightOfBT solution = new HeightOfBT();
        
        // Test Case 1: Balanced tree
        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Tree: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Height/Max Depth: " + solution.maxDepth(root1));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test Case 2: Single node
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{1});
        System.out.println("Tree: [1]");
        System.out.println("Height/Max Depth: " + solution.maxDepth(root2));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test Case 3: Empty tree
        System.out.println("Test Case 3:");
        TreeNode root3 = null;
        System.out.println("Tree: []");
        System.out.println("Height/Max Depth: " + solution.maxDepth(root3));
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
        System.out.println("Height/Max Depth: " + solution.maxDepth(root4));
        System.out.println("Expected: 3");
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
        System.out.println("Height/Max Depth: " + solution.maxDepth(root5));
        System.out.println("Expected: 4");
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
        System.out.println("Height/Max Depth: " + solution.maxDepth(root6));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test Case 7: Unbalanced tree
        //         1
        //        / \
        //       2   3
        //      /
        //     4
        //    /
        //   5
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, null, null, null, 5});
        System.out.println("Tree: [1, 2, 3, 4, null, null, null, 5]");
        System.out.println("Height/Max Depth: " + solution.maxDepth(root7));
        System.out.println("Expected: 4");
    }
}
