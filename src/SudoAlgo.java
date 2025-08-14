import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudoAlgo {
    private static final int N = 9;
    private static final int[][] grid = new int[N][N];
    private static final boolean[][] locked = new boolean[N][N];

    public static void reset() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = 0;
                locked[r][c] = false;
            }
        }
    }

    public static boolean isLocked(int r, int c) { return locked[r][c]; }

    public static int getValue(int r, int c) { return grid[r][c]; }

    public static void setValue(int r, int c, int v) {
        if (!locked[r][c]) {
            grid[r][c] = (v >= 1 && v <= 9) ? v : 0;
        }
    }

    public static boolean hasUserEntries() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!locked[r][c] && grid[r][c] != 0) return true;
            }
        }
        return false;
    }

    public static boolean isSafe(int row, int col, int n) {
        if (n < 1 || n > 9){
            return false;
        }

        // Rows
        for (int c = 0; c < N; c++){ 
            if (c != col && grid[row][c] == n){ 
                return false;
            }
        }
        // Columns
        for (int r = 0; r < N; r++){
            if (r != row && grid[r][col] == n){ 
                return false;
            }
        }
        
        // Surrounding boxes
        int sr = 3 * (row / 3), sc = 3 * (col / 3);
        for (int r = sr; r < sr + 3; r++)
            for (int c = sc; c < sc + 3; c++)
                if (!(r == row && c == col) && grid[r][c] == n){ 
                    return false;
                }
        return true;
    }

    // Generate a full solved grid, then remove cells to leave `clues` fixed numbers
    public static void generatePuzzle(int clues) {
        reset();
        fillSolved(0, 0);

        // Lock all cells
        for (int r = 0; r < N; r++){ 
            for (int c = 0; c < N; c++){
                locked[r][c] = true;
            }
        }

        // Generates list of clues
        int cellsToRemove = Math.max(0, 81 - Math.min(81, Math.max(clues, 17)));
        List <int[]> cells = new ArrayList<>();
        for (int r = 0; r < N; r++){ 
            for (int c = 0; c < N; c++){
                cells.add(new int[]{r, c});
            }
        }

        Collections.shuffle(cells);

        // Add clues to cells
        for (int i = 0; i < cellsToRemove; i++) {
            int[] p = cells.get(i);
            grid[p[0]][p[1]] = 0;
            locked[p[0]][p[1]] = false;
        }
    }

    private static boolean fillSolved(int row, int col) {
        if (row == N) return true; // Finished last row
        int nextRow = (col == N - 1) ? row + 1 : row;
        int nextCol = (col == N - 1) ? 0 : col + 1;

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);

        for (int n : nums) {
            if (isSafe(row, col, n)) {
                grid[row][col] = n;
                if (fillSolved(nextRow, nextCol)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean sudoSolve(int row, int col) {
        if (row == N) return true; // Solved
        int nextRow = (col == N - 1) ? row + 1 : row;
        int nextCol = (col == N - 1) ? 0 : col + 1;

        if (grid[row][col] != 0) return sudoSolve(nextRow, nextCol);

        for (int n = 1; n <= 9; n++) {
            if (isSafe(row, col, n)) {
                grid[row][col] = n;
                if (sudoSolve(nextRow, nextCol)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }
}
