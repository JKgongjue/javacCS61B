public class Palindrome {
    private boolean isEqual(Deque deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        else {
            if (deque.removeLast() == deque.removeFirst()) {
                return isEqual(deque);
            }
        }
        return false;
    }
    private boolean isOffByOne(Deque deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        char ch1 = (char) deque.removeFirst();
        char ch2 = (char) deque.removeLast();
        if (cc.equalChars(ch1, ch2)) {
            return isOffByOne(deque, cc);
        }
        return false;
    }
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
    public boolean isPalindrome(String word) {
        if (word == null || word.length() == 1) {
            return true;
        }
        Deque<Character> d1 = wordToDeque(word);
        return isEqual(d1);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() == 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        return isOffByOne(d, cc);
    }
}
