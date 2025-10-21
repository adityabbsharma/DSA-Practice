package Binary_Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Utility class to visualize binary trees
 */
public class TreeVisualizer {
    
    /**
     * Print tree statistics
     */
    public static void printTreeStats(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        int height = getHeight(root);
        int totalNodes = countNodes(root);
        List<Integer> nodesPerLevel = getNodesPerLevel(root);
        
        System.out.println("=== Tree Statistics ===");
        System.out.println("Total nodes: " + totalNodes);
        System.out.println("Height (edges): " + height);
        System.out.println("Height (nodes): " + (height + 1));
        System.out.println("\nNodes per level:");
        for (int i = 0; i < nodesPerLevel.size(); i++) {
            System.out.println("  Level " + i + ": " + nodesPerLevel.get(i) + " nodes");
        }
        System.out.println("=======================\n");
    }
    
    /**
     * Print tree level by level (good for small to medium trees)
     */
    public static void printLevelOrder(TreeNode root, int maxLevels) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        
        System.out.println("=== Level Order Traversal ===");
        while (!queue.isEmpty() && level < maxLevels) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");
            
            List<String> levelValues = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    levelValues.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    levelValues.add("null");
                }
            }
            
            // Print first 20 values, then ... if more
            if (levelValues.size() <= 20) {
                System.out.println(String.join(", ", levelValues));
            } else {
                System.out.println(String.join(", ", levelValues.subList(0, 20)) + 
                    " ... (" + (levelValues.size() - 20) + " more)");
            }
            
            level++;
        }
        
        if (!queue.isEmpty()) {
            System.out.println("... (more levels below)");
        }
        System.out.println("============================\n");
    }
    
    /**
     * Print tree in ASCII format (only for small trees, first few levels)
     */
    public static void printTreeASCII(TreeNode root, int maxDepth) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        System.out.println("=== Tree Structure (First " + maxDepth + " levels) ===");
        printTreeASCIIHelper(root, "", true, 0, maxDepth);
        System.out.println("==========================================\n");
    }
    
    private static void printTreeASCIIHelper(TreeNode node, String prefix, boolean isTail, int depth, int maxDepth) {
        if (node == null || depth >= maxDepth) {
            return;
        }
        
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.val);
        
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printTreeASCIIHelper(node.left, prefix + (isTail ? "    " : "│   "), 
                    node.right == null, depth + 1, maxDepth);
            }
            if (node.right != null) {
                printTreeASCIIHelper(node.right, prefix + (isTail ? "    " : "│   "), 
                    true, depth + 1, maxDepth);
            }
        }
    }
    
    /**
     * Get height of tree (number of edges) - ITERATIVE to avoid stack overflow
     */
    private static int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int height = -1;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        return height;
    }
    
    /**
     * Count total nodes - ITERATIVE to avoid stack overflow
     */
    private static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            count++;
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        
        return count;
    }
    
    /**
     * Get number of nodes at each level
     */
    private static List<Integer> getNodesPerLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            result.add(levelSize);
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Find and print paths of maximum height
     */
    public static void printMaxHeightPaths(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findAllPaths(root, currentPath, allPaths);
        
        int maxLength = allPaths.stream().mapToInt(List::size).max().orElse(0);
        List<List<Integer>> maxPaths = new ArrayList<>();
        for (List<Integer> path : allPaths) {
            if (path.size() == maxLength) {
                maxPaths.add(path);
            }
        }
        
        System.out.println("=== Maximum Height Paths ===");
        System.out.println("Height: " + (maxLength - 1) + " edges (" + maxLength + " nodes)");
        System.out.println("Number of max height paths: " + maxPaths.size());
        
        int displayCount = Math.min(5, maxPaths.size());
        System.out.println("\nShowing first " + displayCount + " paths:");
        for (int i = 0; i < displayCount; i++) {
            List<Integer> path = maxPaths.get(i);
            System.out.println("Path " + (i + 1) + ": " + 
                (path.size() <= 20 ? path.toString() : 
                    path.subList(0, 20) + " ... (" + (path.size() - 20) + " more)"));
        }
        
        if (maxPaths.size() > displayCount) {
            System.out.println("... (" + (maxPaths.size() - displayCount) + " more paths)");
        }
        System.out.println("===========================\n");
    }
    
    private static void findAllPaths(TreeNode node, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (node == null) {
            return;
        }
        
        currentPath.add(node.val);
        
        if (node.left == null && node.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findAllPaths(node.left, currentPath, allPaths);
            findAllPaths(node.right, currentPath, allPaths);
        }
        
        currentPath.remove(currentPath.size() - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Tree Visualization Tool ===\n");
        
        // Example 1: Small tree
        System.out.println("Example 1: Small Tree");
        TreeNode smallTree = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        printTreeStats(smallTree);
        printTreeASCII(smallTree, 10);
        printLevelOrder(smallTree, 10);
        
        // Example 2: Load from file
        System.out.println("\n\n=== Loading Large Tree from File ===\n");
        try {
            byte[] treeBytes = Files.readAllBytes(Paths.get("src/Binary_Tree/testcase_tree.txt"));
            String treeData = new String(treeBytes).trim();
            String[] treeStrings = treeData.substring(1, treeData.length() - 1).split(",");
            Integer[] treeValues = Stream.of(treeStrings)
                .map(String::trim)
                .map(s -> s.equals("null") ? null : Integer.parseInt(s))
                .toArray(Integer[]::new);
            
            System.out.println("Building tree from " + treeValues.length + " values...");
            TreeNode largeTree = TreeNode.createTree(treeValues);
            
            printTreeStats(largeTree);
            printTreeASCII(largeTree, 5);  // Only show first 5 levels
            printLevelOrder(largeTree, 10); // Only show first 10 levels
            
            // Skip max height paths for very large trees
            if (treeValues.length < 10000) {
                printMaxHeightPaths(largeTree);
            } else {
                System.out.println("=== Maximum Height Paths ===");
                System.out.println("(Skipped for large tree - would be too memory intensive)");
                System.out.println("===========================\n");
            }
            
        } catch (IOException e) {
            System.out.println("Error reading testcase_tree.txt: " + e.getMessage());
            System.out.println("Make sure the file exists in src/Binary_Tree/");
        }
    }
}

