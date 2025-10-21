package Binary_Tree;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {
    
    // TODO: Implement inorder traversal here
    // Inorder: Left -> Root -> Right
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Your implementation here
        inorderTraversalRecursive(root, result);
        return result;
    }

    public void inorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left, result);
        result.add(root.val);
        inorderTraversalRecursive(root.right, result);

    }
    
    public static void main(String[] args) {
        InOrderTraversal solution = new InOrderTraversal();
        
        // Test Case 1: Simple tree
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root1));
        System.out.println("Expected: [4, 2, 5, 1, 3]");
        System.out.println();
        
        // Test Case 2: Single node
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{1});
        System.out.println("Tree: [1]");
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root2));
        System.out.println("Expected: [1]");
        System.out.println();
        
        // Test Case 3: Empty tree
        System.out.println("Test Case 3:");
        TreeNode root3 = null;
        System.out.println("Tree: []");
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root3));
        System.out.println("Expected: []");
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
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root4));
        System.out.println("Expected: [3, 2, 1]");
        System.out.println();
        
        // Test Case 5: Right-skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test Case 5:");
        TreeNode root5 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3});
        System.out.println("Tree: [1, null, 2, null, 3]");
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root5));
        System.out.println("Expected: [1, 2, 3]");
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
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root6));
        System.out.println("Expected: [4, 2, 5, 1, 6, 3, 7]");
    }
}
