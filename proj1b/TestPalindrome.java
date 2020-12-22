import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
//    /*// You must use this palindrome, and not instantiate
//    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
//    Uncomment this class once you've created your Palindrome class. */
    @Test
    public void test1() {
        assertFalse(palindrome.isPalindrome("abcddcba"));

    }
    @Test
    public void test2() {

        assertFalse(palindrome.isPalindrome("cat"));
    }
    @Test
    public void test3() {
        assertTrue(palindrome.isPalindrome("ABaaaBA"));
    }
    @Test
    public void test4(){
        assertTrue(palindrome.isPalindrome("aba"));
    }
    @Test
    public void test5(){
        OffByOne obo = new OffByOne();
        boolean t = palindrome.isPalindrome("flake", obo);

    }
}
