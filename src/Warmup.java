public class Warmup {
    public static String addBinary (String str1, String str2) {
        int p1 = str1.length() - 1;
        int p2 = str2.length() - 1;
        StringBuilder result = new StringBuilder();
        int carry = 0;

        // Iterate from back each bit string
        for (int i = 0; i < Math.max(str1.length(), str2.length()); i++) {
            // Pad shorter string with 0
            int bit1 = p1 >= 0 ? str1.charAt(p1) - '0' : 0;
            int bit2 = p2 >= 0 ? str2.charAt(p2) - '0' : 0;
            int sum = bit1 + bit2 + carry;

            // Add digits to result and calculate next carry
            result.append(sum % 2);
            carry = sum / 2;
            p1--;
            p2--;
        }

        if (carry == 1) {
            result.append(1);
        }

        return result.reverse().toString();
    }

    public static int myAtoi(String s) {
        int result = 0;
        int sign = 1;
        int i = 0;

        // Iterate past whitespace
        while (s.charAt(i) == ' ') {
            i++;
        }

        // Check for + or -
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        // Parse digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            // Checking for overflow
            if (Integer.MAX_VALUE / 10 - result < Character.getNumericValue(s.charAt(i))) {
                return sign < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + Character.getNumericValue(s.charAt(i));
            i++;
        }

        return result * sign;
    }
}
