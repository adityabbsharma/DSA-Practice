/*
 * HARD
 * 2458. Height of Binary Tree After Subtree Removal Queries
Attempted
Hard
Topics
premium lock icon
Companies
Hint
You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 

Example 1:


Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).
Example 2:


Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
m == queries.length
1 <= m <= min(n, 104)
1 <= queries[i] <= n
queries[i] != root.val
 * 
 */
package Binary_Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeightOfBTAfterSubTreeRemoval {

    public int[] treeQueries(TreeNode root, int[] queries) {
        // basically need to return the height of
        // tree excluding the element in the query
        // In finding the height of the tree, when you find the value
        // of node same as query, dont include it
        // but how to do it. when we are doing dfs traversal
        // first check if the node value is same as queried one
        // if yes return and dont call dfs traversal.
        // but taking more time complexity O(n*m)
        // need to improvise
        // can we iterate the tree once
        // before returning the height for that node
        // store the height of that node in a array or hashmap
        // Then if we can find the nodes that are present 
        // in the maximum height path then for each query
        // check if it exists in the maximum height path or not
        // if not then return height of tree for that query
        // if yes then traverse the tree for finding height by excluding
        // the queries node using the already written function.

        // to find the nodes that are present in the height path
        // first find out the height of the tree. Then again in another
        // recursive function, send this height and check if the

        // but need to find all the paths that may be in max height path
        HeightPath heightPath = new HeightPath();
        List<List<Integer>> pathsOfHeight = heightPath.pathsOfTreeHeight(root);
        System.out.println(pathsOfHeight);
        // store the nodes that are present in heightPath
        Set<Integer> setOfHeightPathNodes = pathsOfHeight.stream().flatMap(s->s.stream()).collect(Collectors.toSet());
        System.out.println(setOfHeightPathNodes);
        // iterate through the queries and if the query is not there
        // in the set then corresponding result is the height of tree
        // if the query is in path, then iterate through the list to find
        // the list which contains the query and its corresponding index
        // in that path , index+1 is the result for that query
        
        int n = queries.length;
        int[] result =  new int[queries.length];

        // find height of the tree first
        // int heightOfTree = findHeight(root);
        // store the nodes that are present in the path to find height of tree
        
        for(int i = 0; i < n; i++) {
            // result[i] = helperHeightRecursive(root, queries[i])-1;
            int query = queries[i];
            if (!setOfHeightPathNodes.contains(query)) {
                result[i] = pathsOfHeight.get(0).size()-1; // height of tree
            } else {
                // result[i] = pathsOfHeight.stream().filter(s->s.contains(query))
                // .findFirst().get().indexOf(query)-1;
                result[i] = helperHeightRecursive(root, queries[i])-1;
            }
        }
        return result;
    }

    public int findHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        return 1 + Math.max(leftHeight,rightHeight); 
    }

    // public boolean findPathOfHeightOfTree(TreeNode node, int ,List<Integer> path) {
        
    // }

    public int helperHeightRecursive(TreeNode node, int query) {
        if(node == null || node.val == query) {
            return 0;
        }
        int leftHeight = helperHeightRecursive(node.left, query);
        int rightHeight = helperHeightRecursive(node.right, query);
        return 1 + Math.max(leftHeight,rightHeight); 
    }

    public static void main(String[] args) {
        HeightOfBTAfterSubTreeRemoval solution = new HeightOfBTAfterSubTreeRemoval();
        
        // Test Case 1: Example 1 from problem
        //           1
        //          / \
        //         3   4
        //        /   / \
        //       2   6   5
        //              /
        //             7
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7});
        int[] queries1 = {4};
        System.out.println("Tree: [1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7]");
        System.out.println("Queries: " + Arrays.toString(queries1));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root1, queries1)));
        System.out.println("Expected: [2]");
        System.out.println();
        
        // Test Case 2: Example 2 from problem
        //         5
        //        / \
        //       8   9
        //      / \ / \
        //     2  1 3  7
        //    / \
        //   4   6
        System.out.println("Test Case 2:");
        TreeNode root2 = TreeNode.createTree(new Integer[]{5, 8, 9, 2, 1, 3, 7, 4, 6});
        int[] queries2 = {3, 2, 4, 8};
        System.out.println("Tree: [5, 8, 9, 2, 1, 3, 7, 4, 6]");
        System.out.println("Queries: " + Arrays.toString(queries2));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root2, queries2)));
        System.out.println("Expected: [3, 2, 3, 2]");
        System.out.println();
        
        // Test Case 3: Simple two-level tree
        //     1
        //    / \
        //   2   3
        System.out.println("Test Case 3:");
        TreeNode root3 = TreeNode.createTree(new Integer[]{1, 2, 3});
        int[] queries3 = {2, 3};
        System.out.println("Tree: [1, 2, 3]");
        System.out.println("Queries: " + Arrays.toString(queries3));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root3, queries3)));
        System.out.println("Expected: [1, 1]");
        System.out.println();
        
        // Test Case 4: Left-skewed tree
        //     1
        //    /
        //   2
        //  /
        // 3
        System.out.println("Test Case 4:");
        TreeNode root4 = TreeNode.createTree(new Integer[]{1, 2, null, 3});
        int[] queries4 = {2};
        System.out.println("Tree: [1, 2, null, 3]");
        System.out.println("Queries: " + Arrays.toString(queries4));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root4, queries4)));
        System.out.println("Expected: [0]");
        System.out.println();
        
        // Test Case 5: Right-skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test Case 5:");
        TreeNode root5 = TreeNode.createTree(new Integer[]{1, null, 2, null, 3});
        int[] queries5 = {2};
        System.out.println("Tree: [1, null, 2, null, 3]");
        System.out.println("Queries: " + Arrays.toString(queries5));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root5, queries5)));
        System.out.println("Expected: [0]");
        System.out.println();
        
        // Test Case 6: Balanced tree with multiple queries
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        System.out.println("Test Case 6:");
        TreeNode root6 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int[] queries6 = {2, 3, 4, 5, 6, 7};
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Queries: " + Arrays.toString(queries6));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root6, queries6)));
        System.out.println("Expected: [2, 2, 2, 2, 2, 2]");
        System.out.println();
        
        // Test Case 7: Unbalanced tree
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        //    /
        //   6
        System.out.println("Test Case 7:");
        TreeNode root7 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, null, null, 6});
        int[] queries7 = {4, 5, 3};
        System.out.println("Tree: [1, 2, 3, 4, 5, null, null, 6]");
        System.out.println("Queries: " + Arrays.toString(queries7));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root7, queries7)));
        System.out.println("Expected: [2, 3, 3]");
        System.out.println();
        
        // Test Case 8: Tree with only root and one child
        //   1
        //  /
        // 2
        System.out.println("Test Case 8:");
        TreeNode root8 = TreeNode.createTree(new Integer[]{1, 2});
        int[] queries8 = {2};
        System.out.println("Tree: [1, 2]");
        System.out.println("Queries: " + Arrays.toString(queries8));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root8, queries8)));
        System.out.println("Expected: [0]");
        System.out.println();
        
        // Test Case 9: Large test case from files (for memory limit testing)
        System.out.println("Test Case 9 (Large Input):");
        try {
            // Read tree data from file
            byte[] treeBytes = Files.readAllBytes(Paths.get("src/Binary_Tree/testcase_tree.txt"));
            String treeData = new String(treeBytes).trim();
            String[] treeStrings = treeData.substring(1, treeData.length() - 1).split(",");
            Integer[] treeValues = Stream.of(treeStrings)
                .map(String::trim)
                .map(s -> s.equals("null") ? null : Integer.parseInt(s))
                .toArray(Integer[]::new);
            
            // Read query data from file
            byte[] queryBytes = Files.readAllBytes(Paths.get("src/Binary_Tree/testcase_query.txt"));
            String queryData = new String(queryBytes).trim();
            String[] queryStrings = queryData.substring(1, queryData.length() - 1).split(",");
            int[] queries9 = Stream.of(queryStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
            
            System.out.println("Tree size: " + treeValues.length + " nodes");
            System.out.println("Queries count: " + queries9.length);
            
            TreeNode root9 = TreeNode.createTree(treeValues);
            long startTime = System.currentTimeMillis();
            int[] result9 = solution.treeQueries(root9, queries9);
            long endTime = System.currentTimeMillis();
            
            System.out.println("First 10 results: " + Arrays.toString(Arrays.copyOf(result9, Math.min(10, result9.length))));
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
            
        } catch (IOException e) {
            System.out.println("Error reading test files: " + e.getMessage());
            System.out.println("Make sure testcase_tree.txt and testcase_query.txt are in src/Binary_Tree/");
        }
        
    }
}