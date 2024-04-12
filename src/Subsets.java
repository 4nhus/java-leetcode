import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsets {
    public static ArrayList<String> permuteWord(String word) {
        ArrayList<String> result = new ArrayList<>();
        recursivePermuteWord(result, word, 0);
        return result;
    }

    public static void recursivePermuteWord(ArrayList<String> result, String word, int index) {
        if (index == word.length()) {
            result.add(word);
            return;
        }

        for (int nextIndex = index; nextIndex < word.length(); nextIndex++) {
            String swappedWord = swapWordIndices(word, index, nextIndex);
            recursivePermuteWord(result, swappedWord, index + 1);
        }
    }

    public static String swapWordIndices(String word, int i, int j) {
        char[] wordChars = word.toCharArray();
        char temp = wordChars[i];
        wordChars[i] = wordChars[j];
        wordChars[j] = temp;
        return new String(wordChars);
    }

    public static List<List<Integer>> findAllSubsets(int[] nums) {
        int numSubsets = (int) Math.pow(2, nums.length);

        List<List<Integer>> setsList = new ArrayList<>();

        for (int i = 0; i < numSubsets; i++) {
            List<Integer> subset = new ArrayList<>();

            for (int j = 0; j < nums.length; j++) {
                int bitMask = 1 << j;

                if ((i & bitMask) != 0) {
                    subset.add(nums[j]);
                }
            }

            setsList.add(subset);
        }

        return setsList;
    }

    public List<String> letterCombinations(String digits){
        Map<Character, String[]> digitsToLetters = new HashMap<>();
        digitsToLetters.put('2', new String[] {"a", "b", "c"});
        digitsToLetters.put('3', new String[] {"d", "e", "f"});
        digitsToLetters.put('4', new String[] {"g", "h", "i"});
        digitsToLetters.put('5', new String[] {"j", "k", "l"});
        digitsToLetters.put('6', new String[] {"m", "n", "o"});
        digitsToLetters.put('7', new String[] {"p", "q", "r", "s"});
        digitsToLetters.put('8', new String[] {"t", "u", "v"});
        digitsToLetters.put('9', new String[] {"w", "x", "y", "z"});

        List<String> combinations = new ArrayList<>();

        generateLetterCombinations(digits, new StringBuilder(), 0, combinations, digitsToLetters);

        return combinations;
    }

    public void generateLetterCombinations(String digits, StringBuilder combination, int index, List<String> combinations, Map<Character, String[]> digitsToLetters) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);

        for (String letter : digitsToLetters.get(digit)) {
            combination.append(letter);
            generateLetterCombinations(digits, combination, index + 1, combinations, digitsToLetters);
            combination.deleteCharAt(index);
        }
    }
}
