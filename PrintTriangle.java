package hw0;

public class PrintTriangle {
    public static void main(String[] args) {
        DrawTriangle(10);
    }
    public static void DrawTriangle(int N){
        for (int i = 0; i <N ; i++) {
            for (int z = 0; z <(N-i) ; z++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {

                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
