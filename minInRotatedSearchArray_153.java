
// Filename: searchminbs_153.java

public class searchminbs_153 {

    // 1. Brute Force (Linear Search)
    public static int findMinLinear(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    // 2. Binary Search (Optimized - Preferred)
    public static int findMinBinary(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }

    // 3. Pivot-Based Approach (Find max element, then return its next)
    public static int findMinPivot(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        // Not rotated at all
        if (nums[start] < nums[end]) return nums[0];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid < end && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (mid > start && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[start]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1; // Should not reach here for valid rotated sorted array
    }

    // Main method to test all
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Linear Search Min: " + findMinLinear(nums));
        System.out.println("Binary Search Min: " + findMinBinary(nums));
        System.out.println("Pivot-Based Min: " + findMinPivot(nums));
    }
}
