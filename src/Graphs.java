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


    public static Node clone(Node root) {
        return cloneHelper(root, new HashMap<>());
    }

    public static Node cloneHelper(Node root, Map<Integer, Node> clones) {
        if (root == null) {
            return null;
        }

        Node clone = new Node(root.data);
        clones.put(clone.data, clone);

        for (Node neighbour : root.neighbors) {
            Node neighbourClone = clones.containsKey(neighbour.data) ? clones.get(neighbour.data) : cloneHelper(neighbour, clones);
            clone.neighbors.add(neighbourClone);
        }

        return clone;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Construct graph first
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        Set<Integer> finished = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : graph.keySet()) {
            if (dfsCycleFind(node, graph, finished, visited)) {
                return false;
            }
        }

        return true;
    }

    public static boolean dfsCycleFind(int node, Map<Integer, Set<Integer>> graph, Set<Integer> finished, Set<Integer> visited) {
        if (finished.contains(node)) {
            return false;
        }

        if (visited.contains(node)) {
            return true;
        }

        visited.add(node);

        for (int neighbour : graph.get(node)) {
            if (dfsCycleFind(neighbour, graph, finished, visited)) {
                return true;
            }
        }

        finished.add(node);

        return false;
    }

    public static class Node {
        int data;
        List<Node> neighbors;

        public Node(int data) {
            this.data = data;
            this.neighbors = new ArrayList<Node>();
        }
    }
}
