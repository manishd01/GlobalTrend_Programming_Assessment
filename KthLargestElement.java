import java.util.Random;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        // Adjust k to 0-based index for internal operations
        int kthLargestIndex = nums.length - k;
        
        // Apply quick select algorithm to find the kth largest element
        return quickSelect(nums, 0, nums.length - 1, kthLargestIndex);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        // Choose a random pivot index within the range [left, right]
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        
        // Partition the array around the pivot and get the final position of the pivot
        pivotIndex = partition(nums, left, right, pivotIndex);
        
        // Compare the pivot's final position with k
        if (pivotIndex == k) {
            return nums[k];
        } else if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        
        // Move pivot to end
        swap(nums, pivotIndex, right);
        
        // Initialize the pointer for the pivot's final position
        int storeIndex = left;
        
        // Partition and swap elements accordingly
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        // Move pivot back to its final position
        swap(nums, storeIndex, right);
        
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();

        // Example usage
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Kth largest element in nums1 for k = " + k1 + ": " + solution.findKthLargest(nums1, k1)); // Expected output: 5

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("Kth largest element in nums2 for k = " + k2 + ": " + solution.findKthLargest(nums2, k2)); // Expected output: 4
    }
}

