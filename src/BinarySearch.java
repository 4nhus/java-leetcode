import java.util.List;

public class BinarySearch {
    public static boolean isBadVersion(int v){ // isBadVersion() is the API function that returns true or false depending upon whether the provided version ID is bad or not
        return true;
    }

    public static int[] firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int counter = 0;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (BinarySearch.isBadVersion(middle)) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }

            counter++;
        }

        return new int[]{left, counter};
    }
    
    public static int binarySearchRotated(List<Integer> nums, int target) {
        int start = 0;
        int end = nums.size() - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (nums.get(middle) == target) {
                return middle;
            } else if (nums.get(middle) < nums.get(end)) { // If right half of array is sorted
                if (target <= nums.get(end) && target > nums.get(middle)) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            } else { // Left half of array is sorted
                if (target >= nums.get(start) && target < nums.get(middle)) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            }
        }

        return -1;
    }

    public static int binarySearch (int []nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return -1;
    }
}
