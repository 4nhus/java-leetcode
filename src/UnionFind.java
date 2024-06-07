import java.util.*;

public class UnionFind {
    static class NoOfIslands {
        static int[] island;
        static int[] islandSize;
        static int count;

        public static int findIsland(int cell) {
            int parent = island[cell];
            while (parent != island[parent]) {
                parent = island[parent];
            }
            island[cell] = parent;
            return parent;
        }

        public static void joinIsland(int cell1, int cell2) {
            int parent1 = findIsland(cell1);
            int parent2 = findIsland(cell2);
            if (parent1 == parent2) {
                return;
            }

            if (islandSize[parent1] < islandSize[parent2]) {
                island[parent1] = parent2;
                islandSize[parent2] += islandSize[parent1];
            } else {
                island[parent2] = parent1;
                islandSize[parent1] += islandSize[parent2];
            }

            count--;
        }

        public static int numIslands(List<List<Character>> grid) {
            int m = grid.size();
            int n = grid.get(0).size();
            island = new int[m * n];
            islandSize = new int[m * n];
            count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid.get(i).get(j) == '1') {
                        island[i * n + j] = i * n + j;
                        islandSize[i * n + j] = 1;
                        count++;
                    }
                }
            }


            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid.get(i).get(j) == '1') {
                        // Check left and up cells to combine islands
                        boolean left = j > 0 && grid.get(i).get(j - 1) == '1';
                        boolean up = i > 0 && grid.get(i - 1).get(j) == '1';

                        if (left) {
                            joinIsland(i * n + j, i * n + j - 1);
                        }
                        if (up) {
                            joinIsland(i * n + j, (i - 1) * n + j);
                        }
                    }
                }
            }

            return count;
        }
    }

    static class AccountsMerge {
        static int[] ids;

        public static List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            int m = accounts.get(0).size();
            ids = new int[n];

            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }

            Map<String, Integer> emailsToIds = new HashMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    String email = accounts.get(i).get(j);
                    if (!emailsToIds.containsKey(email)) {
                        emailsToIds.put(email, i);
                    } else {
                        // New set needs to get merged with existing set
                        ids[i] = emailsToIds.get(email);
                    }
                }
            }

            Map<Integer, Set<String>> idsToEmails = new HashMap<>();

            for (int i = 0; i < n; i++) {
                if (!idsToEmails.containsKey(ids[i])) {
                    idsToEmails.put(ids[i], new HashSet<>(accounts.get(i).subList(1, accounts.get(i).size())));
                } else {
                    idsToEmails.get(ids[i]).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
                }
            }

            List<List<String>> mergedAccounts = new ArrayList<>();
            for (Map.Entry<Integer, Set<String>> entry : idsToEmails.entrySet()) {
                List<String> account = new ArrayList<>(entry.getValue());
                Collections.sort(account);
                account.add(0, accounts.get(entry.getKey()).get(0));
                mergedAccounts.add(account);
            }

            return mergedAccounts;
        }
    }
}