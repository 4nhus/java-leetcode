import java.util.Arrays;

public class TwoPointers {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static int containerWithMostWater(int[] height) {
        int right = height.length - 1;
        int left = 0;
        int maximumWater = 0;

        while (left < right) {
            maximumWater = Math.max(maximumWater, Math.min(height[left], height[right]) * (right - left));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maximumWater;
    }
    public static int[] productExceptSelf(int[] arr) {
        int left = 0, right = arr.length - 1, leftProduct = 1, rightProduct = 1;
        int[] products = new int[arr.length];
        Arrays.fill(products, 1);

        while (left < arr.length && right >= 0) {
            products[left] *= leftProduct;
            products[right] *= rightProduct;
            leftProduct *= arr[left];
            rightProduct *= arr[right];
            left++;
            right--;
        }

        return products;
    }

    public static int[] sortColors (int[] colors) {
        int red = 0;
        int white = 0;
        int blue = colors.length - 1;

        while (white <= blue) {
            if (colors[white] == 0) {
                int temp = colors[red];
                colors[red] = colors[white];
                colors[white] = temp;
                red++;
                white++;
            } else if (colors[white] == 1) {
                white++;
            } else {
                int temp = colors[blue];
                colors[blue] = colors[white];
                colors[white] = temp;
                blue--;
            }
        }

        return colors;
    }

    public static boolean findSumOfThree(int[] nums, int target) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) {
                    return true;
                } else if (sum < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }

        return false;
    }

    public static int rainWater(int[] heights) {
        // Early return for no water capture possible
        if (heights.length == 1) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;

        // Move left pointer until next value is decrease
        while (left < heights.length - 1 && heights[left + 1] >= heights[left]) {
            left++;
        }

        // Move right pointer until next value is decrease
        while (right > 0 && heights[right - 1] >= heights[right]) {
            right--;
        }

        int minCaptureHeight = Math.min(heights[left], heights[right]);
        int capturedRainWater = 0;

        while (left < right) {
            if (heights[left] < heights[right]) {
                left++;

                // Only capture water below min capture height, otherwise recalculate min capture height
                if (heights[left] < minCaptureHeight) {
                    capturedRainWater += minCaptureHeight - heights[left];
                } else {
                    minCaptureHeight = Math.min(heights[left], heights[right]);
                }
            } else {
                right--;
                if (heights[right] < minCaptureHeight) {
                    capturedRainWater += minCaptureHeight - heights[right];
                } else {
                    minCaptureHeight = Math.min(heights[left], heights[right]);
                }
            }
        }

        return capturedRainWater;
    }
}
