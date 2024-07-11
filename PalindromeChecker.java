public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        // Normalize the string: convert to lowercase and remove non-alphanumeric characters
        String normalizedStr = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        // Check if the normalized string is a palindrome
        int left = 0;
        int right = normalizedStr.length() - 1;

        while (left < right) {
            if (normalizedStr.charAt(left) != normalizedStr.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }

        return true; // It is a palindrome
    }

    public static void main(String[] args) {
        // Test cases
        String str1 = "A man, a plan, a canal, Panama!";
        String str2 = "race a car";
        String str3 = "Was it a car or a cat I saw?";
        String str4 = "No lemon, no melon";

        System.out.println(str1 + " is palindrome? —------  " + isPalindrome(str1));
        System.out.println(str2 + " is palindrome? —------ —------ " + isPalindrome(str2));
        System.out.println(str3 + " is palindrome? —------ " + isPalindrome(str3));
        System.out.println(str4 + " is palindrome? —------ " + isPalindrome(str4));
    }
}
