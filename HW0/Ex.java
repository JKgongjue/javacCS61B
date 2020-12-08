package hw0;

public class Ex {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int max = m[0];
        int size = m.length;
        for (int i = 0; i < size; i++) {
            if (max<m[i]){
                max = m[i];
            }
        }
        return max;
    }
    public static int forMax(int[] m) {
        int max = m[0];
        int size = m.length;
        for (int i = 0; i < size; i++) {
            if (max<m[i]){
                max = m[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        int max = max(numbers);
        System.out.println(max);
    }
}
