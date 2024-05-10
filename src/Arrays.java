public class Arrays {
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (char c : str1.toCharArray()) {
            count1[c - 'a']++;
        }

        for (char c : str2.toCharArray()) {
            count2[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] ransomCount = new int[26];
        int[] magazineCount = new int[26];

        for (char c : ransomNote.toCharArray()) {
            ransomCount[c - 'a']++;
        }

        for (char c : magazine.toCharArray()) {
            magazineCount[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (magazineCount[i] < ransomCount[i]) {
                return false;
            }
        }

        return true;
    }

    public static int leastTime(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }

        int[] counts = new int[26];

        for (char task : tasks) {
            counts[task - 'A']++;
        }

        java.util.Arrays.sort(counts);

        int idleTime = (counts[counts.length - 1] - 1) * n;
        int i = counts.length - 2;

        while (counts[i] != 0) {
            idleTime -= Math.min(counts[counts.length - 1] - 1, counts[i]);

            if (idleTime <= 0) {
                break;
            }

            i--;
        }

        return tasks.length + Math.max(idleTime, 0);
    }
}
