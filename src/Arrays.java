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
}
