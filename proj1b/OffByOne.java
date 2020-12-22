public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        int i = y - x;
        return Math.abs(i) == 1;
    }
}
