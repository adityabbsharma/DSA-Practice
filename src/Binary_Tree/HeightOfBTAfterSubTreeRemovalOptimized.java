package Binary_Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * HARD
 * 2458. Height of Binary Tree After Subtree Removal Queries
 * Optimized solution for Height of Binary Tree After Subtree Removal Queries
 * 
 * Key insights:
 * 1. Precompute heights to avoid recalculation
 * 2. For each node, track the maximum height we can achieve WITHOUT going through it
 * 3. Answer queries in O(1) after O(n) preprocessing
 * 
 * Algorithm:
 * - First pass: Compute subtree height for each node (bottom-up)
 * - Second pass: For each node, compute max height excluding that node's subtree (top-down)
 * - Query: O(1) lookup
 */
public class HeightOfBTAfterSubTreeRemovalOptimized {
    
    // Maps to store precomputed values
    private Map<Integer, Integer> subtreeHeight = new HashMap<>();  // Height of subtree rooted at node
    private Map<Integer, Integer> nodeDepth = new HashMap<>();      // Depth of node from root
    private Map<Integer, Integer> maxHeightExcluding = new HashMap<>();  // Max height when excluding this node
    
    public int[] treeQueries(TreeNode root, int[] queries) {
        if (root == null) {
            return new int[queries.length];
        }
        
        // Step 1: Compute subtree heights (bottom-up DFS)
        computeSubtreeHeights(root);
        
        // Step 2: Compute max height excluding each node (top-down DFS)
        computeMaxHeightExcluding(root, 0, -1);
        
        // Step 3: Answer queries in O(1)
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = maxHeightExcluding.getOrDefault(queries[i], 0);
        }
        
        return result;
    }
    
    /**
     * Compute height of subtree rooted at each node - ITERATIVE using post-order traversal
     * Returns height (number of edges) from node to deepest leaf in its subtree
     */
    private int computeSubtreeHeights(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        // Post-order traversal using two stacks
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        
        stack1.push(root);
        
        // Push all nodes to stack2 in reverse post-order
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        
        // Process nodes in post-order (children before parents)
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            
            int leftHeight = node.left != null ? subtreeHeight.get(node.left.val) : -1;
            int rightHeight = node.right != null ? subtreeHeight.get(node.right.val) : -1;
            
            int height = 1 + Math.max(leftHeight, rightHeight);
            subtreeHeight.put(node.val, height);
        }
        
        return subtreeHeight.get(root.val);
    }
    
    /**
     * Compute maximum height of tree when excluding each node's subtree - ITERATIVE using BFS
     */
    private void computeMaxHeightExcluding(TreeNode root, int initialDepth, int initialMaxHeight) {
        if (root == null) {
            return;
        }
        
        // Queue stores: [node, depth, maxHeightFromAbove]
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, initialDepth, initialMaxHeight});
        
        while (!queue.isEmpty()) {
            Object[] curr = queue.poll();
            TreeNode node = (TreeNode) curr[0];
            int depth = (int) curr[1];
            int maxHeightFromAbove = (int) curr[2];
            
            if (node == null) {
                continue;
            }
            
            nodeDepth.put(node.val, depth);
            maxHeightExcluding.put(node.val, maxHeightFromAbove);
            
            // For children, compute what's the max height if we DON'T go through them
            int leftSubtreeHeight = node.left != null ? subtreeHeight.get(node.left.val) : -1;
            int rightSubtreeHeight = node.right != null ? subtreeHeight.get(node.right.val) : -1;
            
            // Max height going through left child (from this node down)
            int maxThroughLeft = depth + 1 + leftSubtreeHeight;
            
            // Max height going through right child (from this node down)
            int maxThroughRight = depth + 1 + rightSubtreeHeight;
            
            // For left child: if we exclude it, we can go through right or through ancestors
            int maxExcludingLeft = Math.max(maxHeightFromAbove, maxThroughRight);
            
            // For right child: if we exclude it, we can go through left or through ancestors
            int maxExcludingRight = Math.max(maxHeightFromAbove, maxThroughLeft);
            
            // Add children to queue
            if (node.left != null) {
                queue.offer(new Object[]{node.left, depth + 1, maxExcludingLeft});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, depth + 1, maxExcludingRight});
            }
        }
    }
    
    public static void main(String[] args) {
        HeightOfBTAfterSubTreeRemovalOptimized solution = new HeightOfBTAfterSubTreeRemovalOptimized();
        
        // Test Case 1: Example 1 from problem
        System.out.println("Test Case 1:");
        TreeNode root1 = TreeNode.createTree(new Integer[]{1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7});
        int[] queries1 = {4};
        System.out.println("Tree: [1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7]");
        System.out.println("Queries: " + Arrays.toString(queries1));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root1, queries1)));
        System.out.println("Expected: [2]");
        System.out.println();
        
        // Test Case 2: Example 2 from problem
        System.out.println("Test Case 2:");
        solution = new HeightOfBTAfterSubTreeRemovalOptimized();
        TreeNode root2 = TreeNode.createTree(new Integer[]{5, 8, 9, 2, 1, 3, 7, 4, 6});
        int[] queries2 = {3, 2, 4, 8};
        System.out.println("Tree: [5, 8, 9, 2, 1, 3, 7, 4, 6]");
        System.out.println("Queries: " + Arrays.toString(queries2));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root2, queries2)));
        System.out.println("Expected: [3, 2, 3, 2]");
        System.out.println();
        
        // Test Case 3: Simple two-level tree
        System.out.println("Test Case 3:");
        solution = new HeightOfBTAfterSubTreeRemovalOptimized();
        TreeNode root3 = TreeNode.createTree(new Integer[]{1, 2, 3});
        int[] queries3 = {2, 3};
        System.out.println("Tree: [1, 2, 3]");
        System.out.println("Queries: " + Arrays.toString(queries3));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root3, queries3)));
        System.out.println("Expected: [1, 1]");
        System.out.println();
        
        // Test Case 4: Balanced tree
        System.out.println("Test Case 4:");
        solution = new HeightOfBTAfterSubTreeRemovalOptimized();
        TreeNode root4 = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int[] queries4 = {2, 3, 4, 5, 6, 7};
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Queries: " + Arrays.toString(queries4));
        System.out.println("Result: " + Arrays.toString(solution.treeQueries(root4, queries4)));
        System.out.println("Expected: [2, 2, 2, 2, 2, 2]");
        System.out.println();
        
        // Test Case 5: Large test from files
        System.out.println("Test Case 5 (Large Input from files):");
        try {
            solution = new HeightOfBTAfterSubTreeRemovalOptimized();
            
            // Read tree data
            byte[] treeBytes = Files.readAllBytes(Paths.get("src/Binary_Tree/testcase_tree.txt"));
            String treeData = new String(treeBytes).trim();
            String[] treeStrings = treeData.substring(1, treeData.length() - 1).split(",");
            Integer[] treeValues = Stream.of(treeStrings)
                .map(String::trim)
                .map(s -> s.equals("null") ? null : Integer.parseInt(s))
                .toArray(Integer[]::new);
            
            // Read query data
            byte[] queryBytes = Files.readAllBytes(Paths.get("src/Binary_Tree/testcase_query.txt"));
            String queryData = new String(queryBytes).trim();
            String[] queryStrings = queryData.substring(1, queryData.length() - 1).split(",");
            int[] queries5 = Stream.of(queryStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
            
            System.out.println("Tree size: " + treeValues.length + " nodes");
            System.out.println("Queries count: " + queries5.length);
            
            TreeNode root5 = TreeNode.createTree(treeValues);
            
            long startTime = System.currentTimeMillis();
            int[] result5 = solution.treeQueries(root5, queries5);
            long endTime = System.currentTimeMillis();
            
            System.out.println("First 20 results: " + Arrays.toString(Arrays.copyOf(result5, Math.min(20, result5.length))));
            System.out.println("Last 20 results: " + Arrays.toString(Arrays.copyOfRange(result5, 
                Math.max(0, result5.length - 20), result5.length)));
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
            System.out.println("✓ SUCCESS - No stack overflow!");
            
        } catch (IOException e) {
            System.out.println("Error reading test files: " + e.getMessage());
        } catch (StackOverflowError e) {
            System.out.println("✗ FAILED - Stack overflow occurred");
        }
    }
}

