import java.util.*;

public class Graphs {
    public static int minMinutesToRot(int[][] grid) {
        int fresh = 1;
        int rotten = 2;

        Queue<int[]> queue = new ArrayDeque<>();
        int min = 0;
        int numFresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == rotten) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == fresh) {
                    numFresh++;
                }
            }
        }

        int[][] neighbours = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {

            int numOrangesInMin = queue.size();

            while (numOrangesInMin > 0) {
                int[] orange = queue.remove();

                // Add neighbours to queue if they are still fresh
                for (int[] neighbour : neighbours) {
                    int row = orange[0] + neighbour[0];
                    int col = orange[1] + neighbour[1];

                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == fresh) {
                        queue.add(new int[]{row, col});
                        grid[row][col] = rotten;
                        numFresh--;
                    }
                }

                numOrangesInMin--;
            }

            min++;
        }

        return numFresh == 0 ? Math.max(min - 1, 0) : -1;
    }

    public static int wordLadder(String src, String dest, List<String> words) {
        Set<String> wordSet = new HashSet<>(words);

        if (!wordSet.contains(dest)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.add(src);
        int pathLength = 0;

        while (!queue.isEmpty()) {
            int wordsToCheck = queue.size();
            pathLength++;

            while (wordsToCheck > 0) {
                String currentWord = queue.remove();
                if (currentWord.equals(dest)) {
                    return pathLength;
                }

                Iterator<String> iter = wordSet.iterator();

                while (iter.hasNext()) {
                    String nextWord = iter.next();
                    if (canTransform(currentWord, nextWord)) {
                        iter.remove();
                        queue.add(nextWord);
                    }
                }
                wordsToCheck--;
            }
        }

        return 0;
    }

    public static boolean canTransform(String src, String dest) {
        boolean transformedLetter = false;

        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) != dest.charAt(i)) {
                if (transformedLetter) {
                    return false;
                }

                transformedLetter = true;
            }
        }

        return true;
    }
}
