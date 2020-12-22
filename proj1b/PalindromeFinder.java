/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
//        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
//        OffByOne obo = new OffByOne();
//        OffByN obn = new OffByN(5);
        int[] array = new int[26];
        String[] arrayMax = new String[26];
        for (int i = 0; i < 26; i++) {
            OffByN obi = new OffByN(i);
            int sum = 0;
            In in = new In("../library-sp18/data/words.txt");
            String max = "";
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, obi)) {
                    if (max.length() < word.length()) {
                        max = word;
                    }
                    sum++;
                }
            }
            arrayMax[i] = max;
            array[i] = sum;
        }
        for (int j = 0; j < 26; j++) {
            System.out.println("N为" + j + "的回文英文个数为：" + array[j] +
                    ",此时最长的回文单词为：" + arrayMax[j] + "长度为：" + arrayMax[j].length());
        }
//        while (!in.isEmpty()) {
//            String word = in.readString();
//            if (word.length() >= minLength && palindrome.isPalindrome(word, obn)) {
//                System.out.println(word);
//
//            }
//        }
    }
//    Uncomment this class once you've written isPalindrome.
}
