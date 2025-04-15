class Solution {
    // Approach 1: Standard Binary Search
    public int searchBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target is in left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half must be sorted
            else {
                // Check if target is in right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // Approach 2: Find Pivot + Binary Search
    public int searchWithPivot(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        // Find pivot (minimum element index)
        int pivot = findPivot(nums);
        
        // If array is not rotated
        if (pivot == 0) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }
        
        // Decide which part to search
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot, nums.length - 1);
        }
    }
    
    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        if (nums[left] <= nums[right]) return 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return mid;
            }
            
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
    
    private int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Approach 3: Linear Search (for completeness, not optimal)
    public int searchLinear(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    // Approach 4: Recursive Binary Search
    public int searchRecursive(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return searchRecursiveHelper(nums, target, 0, nums.length - 1);
    }
    
    private int searchRecursiveHelper(int[] nums, int target, int left, int right) {
        if (left > right) return -1;
        
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid;
        }
        
        // Check if left half is sorted
        if (nums[left] <= nums[mid]) {
            // Check if target is in left half
            if (nums[left] <= target && target < nums[mid]) {
                return searchRecursiveHelper(nums, target, left, mid - 1);
            }
            return searchRecursiveHelper(nums, target, mid + 1, right);
        }
        // Right half is sorted
        else {
            // Check if target is in right half
            if (nums[mid] < target && target <= nums[right]) {
                return searchRecursiveHelper(nums, target, mid + 1, right);
            }
            return searchRecursiveHelper(nums, target, left, mid - 1);
        }
    }
    
    // Default method to use (most efficient)
    public int search(int[] nums, int target) {
        return searchBinarySearch(nums, target);
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        
        System.out.println("Standard Binary Search: " + 
            sol.searchBinarySearch(nums, target));
        System.out.println("Pivot + Binary Search: " + 
            sol.searchWithPivot(nums, target));
        System.out.println("Linear Search: " + 
            sol.searchLinear(nums, target));
        System.out.println("Recursive Binary Search: " + 
            sol.searchRecursive(nums, target));
    }
}