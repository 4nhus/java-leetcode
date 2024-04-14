import java.util.Stack;

public class Stacks {
    public static int calculator(String expression) {
        int result = 0;
        int sign = 1;
        int number = 0;
        Stack<Integer> stack = new Stack<>();

        for (Character character : expression.toCharArray()) {
            if (character == ' ') {
                continue;
            } else if (Character.isDigit(character)) {
                number = number * 10 + Character.getNumericValue(character);
            } else if (character == '+' || character == '-') {
                result += number * sign;
                sign = character == '+' ? 1 : -1;
                number = 0;
            } else if (character == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else {
                result += number * sign;
                result *= stack.pop();
                result += stack.pop();
                number = 0;
                sign = 1;
            }
        }

        return result + number * sign;
    }

    public static int rpn(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int subtrahend = stack.pop();
                    int minuend = stack.pop();
                    stack.push(minuend - subtrahend);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static int largestRectangle(int[] heights) {
        Stack<Integer> heightIndices = new Stack<>();

        int maximumArea = 0;
        heightIndices.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (heightIndices.peek() != - 1 && heights[heightIndices.peek()] >= heights[i]) {
                int height = heights[heightIndices.pop()];
                int width = i - heightIndices.peek() - 1;
                maximumArea = Math.max(maximumArea, width * height);
            }

            heightIndices.push(i);
        }

        // Loop through stack to consider all heights remaining in the stack
        while (heightIndices.peek() != -1) {
            int height = heights[heightIndices.pop()];
            int width = heights.length - heightIndices.peek() - 1;
            maximumArea = Math.max(maximumArea, width * height);
        }

        return maximumArea;
    }

    class MyQueue {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void push(int x) {
            while (!stack1.isEmpty()) {
                stack2.push((stack1.pop()));
            }

            stack1.push(x);

            while (!stack2.isEmpty()) {
                stack1.push((stack2.pop()));
            }
        }

        public int pop() {
            return stack1.pop();
        }

        public int peek() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<>();

        for (Character parenthesis : s.toCharArray()) {
            switch(parenthesis) {
                case '(':
                case '[':
                case '{':
                    parentheses.push(parenthesis);
                    break;
                case ')':
                    if (parentheses.isEmpty() || parentheses.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (parentheses.isEmpty() || parentheses.pop() != '[') {
                        return false;
                    }
                    break;
                default:
                    if (parentheses.isEmpty() || parentheses.pop() != '{') {
                        return false;
                    }
            }
        }

        return parentheses.isEmpty();
    }
}
