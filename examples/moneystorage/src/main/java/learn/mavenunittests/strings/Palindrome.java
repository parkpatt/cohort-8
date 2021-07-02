package learn.mavenunittests.strings;

public class Palindrome {
    public static boolean isPalindrome(String input) {
        String test = input.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        for (int start = 0, end = test.length() - 1; start < end; start++, end--) {
            if (test.charAt(start) != test.charAt(end)) {
                return false;
            }
        }
        return true;
    }
}
