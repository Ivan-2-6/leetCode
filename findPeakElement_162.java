// link - https://leetcode.com/problems/find-peak-element/description/
public class findPeakElement_162 {
    
    // Method 1: Binary Search (Original approach, finds one peak efficiently)
    public static int findPeakElementBinarySearch(int[] arr) {
        int n = arr.length;
        
        // Handle edge cases
        if (n == 1) return 0;
        if (arr[0] > arr[1]) return 0;
        if (arr[n-1] > arr[n-2]) return n-1;
        
        int start = 1;
        int end = n-2;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) {
                return mid;
            } else if (arr[mid] < arr[mid+1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return start;
    }
    
    // Method 2: Linear Scan (Finds first peak, simple but less efficient)
    public static int findPeakElementLinear(int[] arr) {
        int n = arr.length;
        
        if (n == 1) return 0;
        
        // Check first element
        if (arr[0] > arr[1]) return 0;
        
        // Check last element
        if (arr[n-1] > arr[n-2]) return n-1;
        
        // Check middle elements
        for (int i = 1; i < n-1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                return i;
            }
        }
        
        return -1; // Should never reach here given problem constraints
    }
    
    // Method 3: Find All Peaks (Returns list of all peak indices)
    public static java.util.List<Integer> findAllPeakElements(int[] arr) {
        java.util.List<Integer> peaks = new java.util.ArrayList<>();
        int n = arr.length;
        
        if (n == 0) return peaks;
        
        // Check first element
        if (n == 1 || arr[0] > arr[1]) {
            peaks.add(0);
        }
        
        // Check middle elements
        for (int i = 1; i < n-1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                peaks.add(i);
            }
        }
        
        // Check last element
        if (n > 1 && arr[n-1] > arr[n-2]) {
            peaks.add(n-1);
        }
        
        return peaks;
    }
    
    // Method 4: Recursive Binary Search
    public static int findPeakElementRecursive(int[] arr) {
        return findPeakRecursive(arr, 0, arr.length-1);
    }
    
    private static int findPeakRecursive(int[] arr, int start, int end) {
        if (start == end) return start;
        
        int mid = start + (end - start) / 2;
        
        if ((mid == 0 || arr[mid] > arr[mid-1]) && 
            (mid == arr.length-1 || arr[mid] > arr[mid+1])) {
            return mid;
        }
        
        if (mid < arr.length-1 && arr[mid] < arr[mid+1]) {
            return findPeakRecursive(arr, mid + 1, end);
        }
        
        return findPeakRecursive(arr, start, mid);
    }

    // another one , but important one
        public static int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Peak is on the left side (including mid)
                end = mid;
            } else {
                // Peak is on the right side
                start = mid + 1;
            }
        }

        // At the end, start == end pointing to a peak
        return start;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 7, 6, 4};
        
        // Test Binary Search
        System.out.println("Binary Search Peak Index: " + 
            findPeakElementBinarySearch(arr));
        
        // Test Linear Scan
        System.out.println("Linear Scan Peak Index: " + 
            findPeakElementLinear(arr));
        
        // Test Find All Peaks
        System.out.println("All Peak Indices: " + 
            findAllPeakElements(arr));
        
        // Test Recursive Binary Search
        System.out.println("Recursive Binary Search Peak Index: " + 
            findPeakElementRecursive(arr));
    }
}
