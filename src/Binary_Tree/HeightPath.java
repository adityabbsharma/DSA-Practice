package Binary_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HeightPath {
    
    // TODO: Implement to return the path (list of node values)
    // that corresponds to the height (longest root-to-leaf path)
    // Example signature you could use:
    // public List<Integer> pathOfTreeHeight(TreeNode root) { 

    //     if (root==null) {
    //         return new ArrayList<>();
    //     }
    //     if(root.left==null && root.right==null) {
    //         List<Integer> path = new ArrayList<>();
    //         path.add(root.val);
    //         return path;
    //     }
    //     List<Integer> leftPath = pathOfTreeHeight(root.left);
    //     List<Integer> rightPath = pathOfTreeHeight(root.right);

    //     if(leftPath.size() > rightPath.size()) {
    //         leftPath.add(0, root.val);
    //     } else {
    //         rightPath.add(0, root.val);
    //     }

    //     return leftPath.size()>rightPath.size()? leftPath: rightPath;
    // }

    public List<List<Integer>> pathsOfTreeHeight(TreeNode root) {
        // first find out all paths from root to leaf nodes
        // get the maximum height by finding highest size of any of list
        // filter the paths that lead to height and return the result
        List<List<Integer>> allPaths = new ArrayList<>();
        if(root==null) {
            return allPaths;
        }
        List<Integer> currentPath = new ArrayList<>();
        findAllPathsFromRootToLeafNodes(root, currentPath, allPaths);
        int heightOfTree = allPaths.stream().mapToInt(s-> s.size()).max().getAsInt();
        List<List<Integer>> allPathsOfHeight = allPaths.stream().filter(s->s.size()==heightOfTree)
        .collect(Collectors.toList());
        return allPathsOfHeight;
        
    }

    public void findAllPathsFromRootToLeafNodes(TreeNode root,
                                        List<Integer> currentPath, 
                                        List<List<Integer>> allPaths ) {
        if (root==null) {
            return;
        }
        currentPath.add(root.val);
        if (root.left==null && root.right==null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findAllPathsFromRootToLeafNodes(root.left, currentPath, allPaths);
            findAllPathsFromRootToLeafNodes(root.right, currentPath, allPaths);
        }
        currentPath.remove(currentPath.size()-1);
    }

    
    public static void main(String[] args) {
        HeightPath solution = new HeightPath();
        
        // Test Case 1: Balanced tree
        //       1
        //      / \
        //     2   3
        //    / \   
        //   4   5  
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root1));
        System.out.println("Expected: [[1, 2, 4], [1, 2, 5]]");
        System.out.println();
        
        // Test Case 2: Left-skewed tree
        //     1
        //    /
        //   2
        //  /
        // 3
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{1, 2, null, 3});
        System.out.println("Tree: [1, 2, null, 3]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root2));
        System.out.println("Expected: [[1, 2, 3]]");
        System.out.println();
        
        // Test Case 3: Right-skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test Case 3:");
        TreeNode root3 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3});
        System.out.println("Tree: [1, null, 2, null, 3]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root3));
        System.out.println("Expected: [[1, 2, 3]]");
        System.out.println();
        
        // Test Case 4: Single node
        System.out.println("Test Case 4:");
        TreeNode root4 = TreeNode.createTree(new Integer[]{7});
        System.out.println("Tree: [7]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root4));
        System.out.println("Expected: [[7]]");
        System.out.println();
        
        // Test Case 5: Empty tree
        System.out.println("Test Case 5:");
        TreeNode root5 = null;
        System.out.println("Tree: []");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root5));
        System.out.println("Expected: []");
        System.out.println();
        
        // Test Case 6: Unbalanced tree (deeper left branch)
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        //    /
        //   6
        System.out.println("Test Case 6:");
        TreeNode root6 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, null, null, 6});
        System.out.println("Tree: [1, 2, 3, 4, 5, null, null, 6]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root6));
        System.out.println("Expected: [[1, 2, 4, 6]]");
        System.out.println();
        
        // Test Case 7: Multiple deepest leaves
        //          1
        //         / \
        //        2   3
        //       /   / \
        //      4   5   6
        //         /
        //        7
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, 7});
        System.out.println("Tree: [1, 2, 3, 4, null, 5, 6, null, null, 7]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root7));
        System.out.println("Expected: [[1, 3, 5, 7]]");
        System.out.println();
        
        // Test Case 8: Larger complete tree
        //            1
        //          /   \
        //         2     3
        //        / \   / \
        //       4   5 6   7
        //      / \
        //     8   9
        System.out.println("Test Case 8:");
        TreeNode root8 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7, 8, 9]");
        System.out.println("Actual:   " + solution.pathsOfTreeHeight(root8));
        System.out.println("Expected: [[1, 2, 4, 8], [1, 2, 4, 9]]");
    }
}
