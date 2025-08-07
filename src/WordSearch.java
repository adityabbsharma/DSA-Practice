import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 79. Word Search
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 *
 */


public class WordSearch {
    // DFS approach
    int rows,cols;
    Set<String> visited;
    public boolean exist(char[][] board, String word) {
        // implement a dfs method which accepts rowIndex, columnIndex, int k
        // k designates the index in the given word.
        // search using dfs starting from r=0,c=0,k=0
        // define base condition in the dfs to return true if k >= length of word
        // that says we have reached the depth
        // define another base condition which returns false if r or goes out of bounds
        // or the (r,c) cell is visited or the cell value is not equal to kth char of the word
        // Next the second base condition is not true i.e. the char is
        // found. We mark the (r,c) as visited. We search for the next character in adjacent cells.
        // Then search the around the cell using dfs while incrementing the next char
        // k+1.
        // then we mark the (r,c) as unvisited and return the result from the dfs search
        Map<Character, Integer> count = new HashMap<>();
        for (char c : word.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        if (count.getOrDefault(word.charAt(0), 0) > count.getOrDefault(word.charAt(word.length() - 1), 0)) {
            word = new StringBuilder(word).reverse().toString();
        }
        char[] wordCharArr = word.toCharArray();
        rows = board.length;
        cols = board[0].length;
        visited = new HashSet<>();
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(dfs(i,j,0,board,wordCharArr)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean dfs(int r, int c, int k, char[][] board,char[] word) {
        if(word.length == k) {
            return true;
        }
        String cellRC = r+","+c;
        if(r<0 || r>=rows || c<0 || c>=cols || visited.contains(cellRC) || board[r][c]!=word[k]) {
            return false;
        }
        visited.add(cellRC);
        boolean result = dfs(r+1,c,k+1,board,word) ||
                dfs(r-1,c,k+1,board,word) ||
                dfs(r,c+1,k+1,board,word) ||
                dfs(r,c-1,k+1,board,word);
        visited.remove(cellRC);
        return result;
    }
    /*
    * Another approach
    * Iterate through the cells. Keep a Map<Character,Set<String>. For each occurrence of the character add the r,c index value as a String( 0,1) -> "01" in the Set
    * Now iterate through the given word character by character
    * For each character find the Set of strings corresponding to the occurrence of the character at different positions in the matrix.
    * for each String find the row first. constitute a list of Strings. suppose string is 22. Then the adjacent cell can be 12 32 21 23 (touching four sides)
    * now check if 12 exists in the Set of strings of the next character in the given word in the map. if no check for others 32 21 23. if none of them match then return false
    * if any of them match, go to next character and do the same till the last character
    * */

}
