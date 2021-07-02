package learn.mavenunittests.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void shouldReturnTrueForValidPalindrome() {
        String test = "kayak";
        boolean actual = Palindrome.isPalindrome(test);
        assertTrue(actual);

        test = "tenet";
        actual = Palindrome.isPalindrome(test);
        assertTrue(actual);
    }

    @Test
    void shouldNotBeCaseSensitive() {
        String test = "kayAK";
        boolean actual = Palindrome.isPalindrome(test);
        assertTrue(actual);

        test = "Tenet";
        actual = Palindrome.isPalindrome(test);
        assertTrue(actual);
    }

    @Test
    void shouldAllowWhitespace() {
        String test = "Go hang a salami Im a lasagna hog";
        assertTrue(Palindrome.isPalindrome(test));
    }

    @Test
    void shouldAllowPunctuation() {
        String test = "Go hang a salami, I'm a lasagna hog";
        assertTrue(Palindrome.isPalindrome(test));
    }

    @Test
    void shouldReturnFalseForNonPalindrome() {
        String test = "This is not a palindrome";

        assertFalse(Palindrome.isPalindrome(test));
    }
}