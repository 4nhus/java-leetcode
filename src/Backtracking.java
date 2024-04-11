public class Backtracking {
    public static boolean wordSearch(char[][] grid, String word) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (wordSearchDFS(grid, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean wordSearchDFS(char[][] grid, String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }

        if (grid[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = grid[row][col];
        grid[row][col] = '*';

        int[][] offsets = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] offset : offsets) {
            if (row + offset[0] < 0 || row + offset[0] == grid.length || col + offset[1] < 0 || col + offset[1] == grid[0].length) {
                continue;
            }
            if (wordSearchDFS(grid, word, row + offset[0], col + offset[1], index + 1)) {
                return true;
            }
        }

        grid[row][col] = temp;

        return false;
    }

    public static int[][] floodFill(int[][] grid, int sr, int sc, int target) {
        floodFillDFS(grid, sr, sc, target, grid[sr][sc]);
        return grid;
    }

    public static void floodFillDFS(int[][] grid, int row, int col, int target, int sourceValue) {
        if (grid[row][col] != sourceValue || grid[row][col] == target) {
            return;
        }

        grid[row][col] = target;

        int[][] offsets = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] offset : offsets) {
            if (row + offset[0] < 0 || row + offset[0] == grid.length || col + offset[1] < 0 || col + offset[1] == grid[0].length) {
                continue;
            }

            floodFillDFS(grid, row + offset[0], col + offset[1], target, sourceValue);
        }
    }
}
