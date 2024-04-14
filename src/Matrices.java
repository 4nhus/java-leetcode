import java.util.ArrayList;
import java.util.List;

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
}
