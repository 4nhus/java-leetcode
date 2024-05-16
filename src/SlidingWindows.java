import java.util.HashMap;
import java.util.Map;

public class SlidingWindows {
    public static String minWindow(String s, String t) {
        Map<Character, Integer> tCharCount = new HashMap<>();
        Map<Character, Integer> extraCount = new HashMap<>();
        Map<Character, Integer> zeroCount = new HashMap<>();

        for (char c : t.toCharArray()) {
            tCharCount.put(c, tCharCount.getOrDefault(c, 0) + 1);
            zeroCount.put(c, 0);
            extraCount.put(c, 0);
        }

        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int minLeft = -1;

        char[] sChars = s.toCharArray();

        // Move window right, trimming from the left when satisfying t character count
        while (right < sChars.length) {
            if (tCharCount.containsKey(sChars[right])) {
                // Decrement t character count or increment extra character count
                if (tCharCount.get(sChars[right]) == 0) {
                    extraCount.put(sChars[right], extraCount.get(sChars[right]) + 1);
                } else {
                    tCharCount.put(sChars[right], tCharCount.get(sChars[right]) - 1);
                }

                // Trim from the left when satisfying t character count
                while (tCharCount.equals(zeroCount)) {
                    int length = right - left + 1;

                    // Check if we have new smallest window
                    if (length < minLength) {
                        minLeft = left;
                        minLength = length;
                    }

                    // Trim from the left
                    if (extraCount.containsKey(sChars[left])) {
                        // Decrement extra character count or increment t character count
                        if (extraCount.get(sChars[left]) == 0) {
                            tCharCount.put(sChars[left], tCharCount.get(sChars[left]) + 1);
                        } else {
                            extraCount.put(sChars[left], extraCount.get(sChars[left]) - 1);
                        }
                    }

                    left++;
                }
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }

    public static int maxProfit(int[] prices) {
        int profit = Integer.MIN_VALUE;
        int buy = 0;
        int sell = 0;

        while (sell < prices.length) {
            // Update profit if new max
            if (prices[sell] >= prices[buy]) {
                profit = Math.max(profit, prices[sell] - prices[buy]);
            } else { // Else move buy pointer if new lowest price is found
                buy = sell;
            }

            sell++;
        }

        return profit;
    }

    public static int findLongestSubstring(String str) {
        Map<Character, Integer> frequency = new HashMap<>();

        int left = 0;
        int right = 0;
        int longestSubstringLength = 1;

        char[] chars = str.toCharArray();

        while (right < chars.length) {
            frequency.put(chars[right], frequency.getOrDefault(chars[right], 0) + 1);

            // Check for repetition at new character
            if (frequency.get(chars[right]) == 1) {
                longestSubstringLength = Math.max(right - left + 1, longestSubstringLength);
            } else {
                // Trim from left until repeated character reached
                while (frequency.get(chars[right]) == 2) {
                    if (chars[left] == chars[right]) {
                        frequency.put(chars[right], 1);
                    } else {
                        frequency.put(chars[left], 0);
                    }

                    left++;
                }
            }

            right++;
        }

        return longestSubstringLength;
    }
}
