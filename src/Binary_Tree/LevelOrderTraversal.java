/*
 * 102. Binary Tree Level Order Traversal
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
 * 
 */

package Binary_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    
    // TODO: Implement level order traversal
    // Return nodes' values level by level (left to right)

    // finding out using queue approach
    // public List<List<Integer>> levelOrder(TreeNode root) {
    //     List<List<Integer>> result = new ArrayList<>();
    //     // Your implementation here
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     if (root==null) {
    //         return result;
    //     }
    //     queue.add(root);
    //     List<Integer> list1 = new ArrayList<>();
    //     list1.add(root.val);
    //     result.add(list1);
    //     int n = 1;
    //     while(!queue.isEmpty()) {
    //         // find the size of (n-1)th element inner list
    //         // poll the queue that many time
    //         // for each polled element, add the left and right to queue
    //         // also create another inner list and add these to that list
    //         // move the nth pointer ahead and repeat the above
    //         List<Integer> newInnerList = new ArrayList<>();
    //         for(int i=0; i<result.get(n-1).size(); i++) {
    //             if(queue.peek()!=null) {
    //                 TreeNode node = queue.poll();
    //                 if (node.left != null) {
    //                     queue.add(node.left);
    //                     newInnerList.add(node.left.val);
    //                 }
    //                 if (node.right != null) {
    //                     queue.add(node.right);
    //                     newInnerList.add(node.right.val);
    //                 }                    
    //             } else {
    //                 queue.poll();
    //             }
    //         }
    //         if (newInnerList != null && newInnerList.size()>0) {
    //             result.add(newInnerList);
    //         }
    //         n++;
    //     }
    //     return result;
    // }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result =  new ArrayList<>();
        levelOrderRecursive(root, result, 0);
        return result;
    }

    public void levelOrderRecursive(TreeNode node, List<List<Integer>> result, int currentLevel) {
        if (node == null) {
            return;
        }
        if (result.size() == currentLevel) {
            result.add(new ArrayList<>());
        }
        result.get(currentLevel).add(node.val);
        levelOrderRecursive(node.left, result, currentLevel+1);
        levelOrderRecursive(node.right, result, currentLevel+1);
    }

    
    public static void main(String[] args) {
        LevelOrderTraversal solution = new LevelOrderTraversal();
        
        // Test Case 1: Example from problem
        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Tree: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Level Order: " + solution.levelOrder(root1));
        System.out.println("Expected: [[3], [9, 20], [15, 7]]");
        System.out.println();
        
        // Test Case 2: Single node
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{1});
        System.out.println("Tree: [1]");
        System.out.println("Level Order: " + solution.levelOrder(root2));
        System.out.println("Expected: [[1]]");
        System.out.println();
        
        // Test Case 3: Empty tree
        System.out.println("Test Case 3:");
        TreeNode root3 = null;
        System.out.println("Tree: []");
        System.out.println("Level Order: " + solution.levelOrder(root3));
        System.out.println("Expected: []");
        System.out.println();
        
        // Test Case 4: Complete binary tree
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        System.out.println("Test Case 4:");
        TreeNode root4 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Level Order: " + solution.levelOrder(root4));
        System.out.println("Expected: [[1], [2, 3], [4, 5, 6, 7]]");
        System.out.println();
        
        // Test Case 5: Left-skewed tree
        //     1
        //    /
        //   2
        //  /
        // 3
        System.out.println("Test Case 5:");
        TreeNode root5 = TreeNode.createTree(new Integer[]{1, 2, null, 3});
        System.out.println("Tree: [1, 2, null, 3]");
        System.out.println("Level Order: " + solution.levelOrder(root5));
        System.out.println("Expected: [[1], [2], [3]]");
        System.out.println();
        
        // Test Case 6: Right-skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test Case 6:");
        TreeNode root6 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3});
        System.out.println("Tree: [1, null, 2, null, 3]");
        System.out.println("Level Order: " + solution.levelOrder(root6));
        System.out.println("Expected: [[1], [2], [3]]");
        System.out.println();
        
        // Test Case 7: Unbalanced tree
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   5   6
        //    /
        //   7
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, null, 6, 7});
        System.out.println("Tree: [1, 2, 3, 4, 5, null, 6, 7]");
        System.out.println("Level Order: " + solution.levelOrder(root7));
        System.out.println("Expected: [[1], [2, 3], [4, 5, 6], [7]]");
        System.out.println();
        
        // Test Case 8: Tree with many levels
        //           1
        //          / \
        //         2   3
        //        /
        //       4
        //      /
        //     5
        System.out.println("Test Case 8:");
        TreeNode root8 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, null, null, null, 5});
        System.out.println("Tree: [1, 2, 3, 4, null, null, null, 5]");
        System.out.println("Level Order: " + solution.levelOrder(root8));
        System.out.println("Expected: [[1], [2, 3], [4], [5]]");
    }
}
