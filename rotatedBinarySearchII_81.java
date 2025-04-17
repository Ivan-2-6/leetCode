
// File: rotatedBinarySearchII_81.java

public class rotatedBinarySearchII_81 {

    // Approach 1: Optimized Binary Search with handling duplicates
    public static boolean searchOptimized(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(nums[mid] == target) return true;

            // Handle duplicates
            if(nums[start] == nums[mid] && nums[mid] == nums[end]){
                start++;
                end--;
            } 
            else if(nums[start] <= nums[mid]) { // Left side is sorted
                if(nums[start] <= target && target < nums[mid]){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // Right side is sorted
                if(nums[mid] < target && target <= nums[end]){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

    // Approach 2: Linear Search (Brute Force) - when performance is not critical
    public static boolean searchLinear(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }

    // Main method to test the different approaches
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;

        System.out.println("Using Optimized Binary Search: " + searchOptimized(nums, target));
        System.out.println("Using Linear Search: " + searchLinear(nums, target));
    }
}
