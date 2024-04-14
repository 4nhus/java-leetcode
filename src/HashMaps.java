import java.util.*;

public class HashMaps {
    public static int longestPalindrome(String s) {
        Map<Character, Integer> counts = new HashMap<>();

        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        int longestLength = 1;

        for (int count : counts.values()) {
            if (count % 2 == 0) {
                longestLength += count;
            } else {
                longestLength += count - 1;
            }
        }

        return longestLength;
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static int findMajorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int majorityElement = nums[0];
        for (int num : nums) {
            if (map.get(num) > map.get(majorityElement)) {
                majorityElement = num;
            }
        }

        return majorityElement;
    }

    public static int[] twoSum(int[] arr, int t) {
        Map<Integer, Integer> map = new HashMap<>();
        int index1 = 0;
        int index2 = 1;

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(t - arr[i])) {
                index1 = i;
                index2 = map.get(t - arr[i]);
                break;
            }

            map.put(arr[i], i);
        }

        return new int[]{index1, index2};
    }

    public static List<Integer> findAnagrams(String a, String b) {
        List<Integer> anagramIndices = new ArrayList<>();

        if (b.length() > a.length()) {
            return anagramIndices;
        }

        Map<Character, Integer> countB = new HashMap<>();
        Map<Character, Integer> countWindow = new HashMap<>();

        for (char c : b.toCharArray()) {
            countB.put(c, countB.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < a.length(); i++) {
            countWindow.put(a.charAt(i), countWindow.getOrDefault(a.charAt(i), 0) + 1);

            if (i - b.length() + 1 > 0) {
                countWindow.put(a.charAt(i - b.length()), countWindow.get(a.charAt(i - b.length())) - 1);

                if (countWindow.get(a.charAt(i - b.length())) == 0) {
                    countWindow.remove(a.charAt(i - b.length()));
                }
            }

            if (countB.equals(countWindow)) {
                anagramIndices.add(i - b.length() + 1);
            }
        }

        return anagramIndices;
    }
}
