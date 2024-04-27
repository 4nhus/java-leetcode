import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DynamicProgramming {
    public static int coinChange(int[] coins, int total) {
        if (total == 0) {
            return 0;
        }

        int[] min = new int[total + 1];
        Arrays.fill(min, -1);
        min[0] = 0;

        for (int i = 1; i <= total; i++) {
            int currentMin = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (i - coin < 0 || min[i - coin] == -1) {
                    continue;
                }

                currentMin = Math.min(currentMin, min[i - coin]);
            }

            if (currentMin != Integer.MAX_VALUE) {
                min[i] = currentMin + 1;
            }
        }

        return min[total];
    }

    public static int climbStairs(int n) {
        int[] num = new int[n + 1];
        num[0] = 1;
        num[1] = 1;

        for (int i = 2; i <= n; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }

        return num[n];
    }

    public static int[][] updateMatrix(int[][] mat) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] != 0) {
                    int up = row > 0 ? mat[row - 1][col] : Integer.MAX_VALUE - 100000;
                    int left = col > 0 ? mat[row][col - 1] : Integer.MAX_VALUE - 100000;
                    mat[row][col] = Math.min(up, left) + 1;
                }
            }
        }

        for (int row = mat.length - 1; row >= 0; row--) {
            for (int col = mat[0].length - 1; col >= 0; col--) {
                if (mat[row][col] != 0) {
                    int down = row < mat.length - 1 ? mat[row + 1][col] : Integer.MAX_VALUE - 100000;
                    int left = col < mat[0].length - 1 ? mat[row][col + 1] : Integer.MAX_VALUE - 100000;
                    mat[row][col] = Math.min(mat[row][col], Math.min(down, left) + 1);
                }
            }
        }

        return mat;
    }

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<List<Integer>>> combinationsList = new ArrayList<>();
        combinationsList.add(new ArrayList<>());
        combinationsList.get(0).add(new ArrayList<>());

        for (int i = 1; i <= target; i++) {
            combinationsList.add(new ArrayList<>());
            for (int num : nums) {
                if (i - num < 0) {
                    continue;
                }

                for (List<Integer> combination : combinationsList.get(i - num)) {
                    List<Integer> combinationWithNum = new ArrayList<>(combination);
                    combinationWithNum.add(num);
                    Collections.sort(combinationWithNum);
                    if (!combinationsList.get(i).contains(combinationWithNum)) {
                        combinationsList.get(i).add(combinationWithNum);
                    }
                }
            }
        }

        return combinationsList.get(target);
    }
    
    public static String longestPalindromicSubstring(String s) {
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
        }

        int maxLength = 1;
        int maxI = 0;
        int maxJ = 0;

        for (int i = 0; i < n - 1; i++) {
            palindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (palindrome[i][i + 1]) {
                maxLength = 2;
                maxI = i;
                maxJ = i + 1;
            }
        }

        for (int length = 3; length <= n; length++) {
            int i = 0;
            for (int j = length - 1; j < n; j++) {
                palindrome[i][j] = palindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);

                if (palindrome[i][j]) {
                    if (j - i > maxLength) {
                        maxLength = j - i;
                        maxI = i;
                        maxJ = j;
                    }
                }
                i++;
            }
        }

        return s.substring(maxI, maxJ + 1);
    }

    public static boolean canPartitionArray(int[] arr) {
        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        sum /= 2;

        boolean[][] partition = new boolean[sum + 1][arr.length + 1];

        Arrays.fill(partition[0], true);

        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= arr.length; j++) {
                if (i - arr[j - 1] < 0) {
                    partition[i][j] = partition[i][j - 1];
                } else {
                    partition[i][j] = partition[i][j - 1] || partition[i - arr[j - 1]][j - 1];
                }
            }
        }

        return partition[sum][arr.length];
    }

    public static int uniquePaths(int m, int n) {
        int[][] num = new int[m][n];
        num[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int up = i > 0 ? num[i - 1][j] : 0;
                int left = j > 0 ? num[i][j - 1] : 0;
                num[i][j] = up + left;
            }
        }

        return num[m - 1][n - 1];
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] possible = new boolean[s.length() + 1];
        possible[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                if (i < word.length()) {
                    continue;
                }

                if (possible[i - word.length()] && s.startsWith(word, i - word.length())) {
                    possible[i] = true;
                    break;
                }
            }
        }

        return possible[s.length()];
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        List<int[]> jobs = new ArrayList<>(n);
        int[] profits = new int[n];

        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{startTime[i], endTime[i], profit[i]});
        }

        jobs.sort((jobA, jobB) -> jobA[1] - jobB[1]);

        for (int i = 0; i < n; i++) {
            int indexOfEarlierJob = binarySearchJobs(jobs.get(i)[0], jobs);
            int includeProfit = indexOfEarlierJob == -1 ? jobs.get(i)[2] : profits[indexOfEarlierJob] + jobs.get(i)[2];
            int excludeProfit = i > 0 ? profits[i - 1] : 0;
            profits[i] = Math.max(includeProfit, excludeProfit);
        }

        return profits[n - 1];
    }

    // Find the index of the latest job that does not conflict
    public static int binarySearchJobs(int startTime, List<int[]> jobs) {
        int left = 0;
        int right = jobs.size() - 1;
        int validIndex = -1;

        while (left <= right) {
            int middle = (left + right) / 2;
            int endTime = jobs.get(middle)[1];
            if (endTime == startTime) {
                return middle;
            } else if (endTime < startTime) {
                validIndex = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return validIndex;
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
