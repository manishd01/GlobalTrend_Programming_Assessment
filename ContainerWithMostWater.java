public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // Calculate the current area
            int currentArea = (right - left) * Math.min(height[left], height[right]);
            // Update maxArea if the current area is greater
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the smaller height inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Example usage
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Area for height1: " + solution.maxArea(height1)); // Expected output: 49

        int[] height2 = {1, 1};
        System.out.println("Max Area for height2: " + solution.maxArea(height2)); // Expected output: 1

        int[] height3 = {4, 3, 2, 1, 4};
        System.out.println("Max Area for height3: " + solution.maxArea(height3)); // Expected output: 16
    }
}
