import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrices {
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int right = 0;
        int down = 1;
        int left = 2;
        int up = 3;
        int direction = right;
        int row = 0;
        int col = 0;

        List<Integer> result = new ArrayList<>();

        while (true) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            if ((col == n - 1 || visited[row][col + 1]) && (row == m - 1 || visited[row + 1][col]) && (col == 0 || visited[row][col - 1]) && (row == 0 || visited[row - 1][col])) {
                break;
            }

            switch (direction) {
                case 0:
                    if (col == n - 1 || visited[row][col + 1]) {
                        direction = down;
                        row++;
                    } else {
                        col++;
                    }
                    break;
                case 1:
                    if (row == m - 1 || visited[row + 1][col]) {
                        direction = left;
                        col--;
                    } else {
                        row++;
                    }
                    break;
                case 2:
                    if (col == 0 || visited[row][col - 1]) {
                        direction = up;
                        row--;
                    } else {
                        col--;
                    }
                    break;
                default:
                    if (row == 0 || visited[row - 1][col]) {
                        direction = right;
                        col++;
                    } else {
                        row--;
                    }
            }
        }

        return result;
    }

    public static int[][] setMatrixZeros(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        Set<Integer> zeroRows = new HashSet<>(), zeroColumns = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    zeroRows.add(i);
                    zeroColumns.add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0 && (zeroRows.contains(i) || zeroColumns.contains(j))) {
                    mat[i][j] = 0;
                }
            }
        }

        return mat;
    }

    public static int[][] rotateImage(int[][] matrix) {
        int n = matrix.length;

        // For each "layer"
        for (int i = 0; i < n / 2; i++) {
            // For each cell in a layer
            for (int j = i; j < n - i - 1; j++) {
                // Swap top left with top right
                swap(matrix, i, j, j, n - 1 - i);

                // Swap bottom left with top left
                swap(matrix, n - 1 - j, i, i, j);

                // Swap bottom right with bottom left
                swap(matrix, n - 1 - i, n - 1 - j, n - 1 - j, i);
            }
        }

        return matrix;
    }

    public static void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }
}
