package hw0;

public class EX4 {
    public static void windowPosSum(int[] a, int n) {
        /** your code here */
        for (int i = 0; i < a.length; i++) {
            if (a[i]>0){
                int sum=0;
                for (int j = i; j <=i+n ; j++) {
                    if (j<=5)
                        sum+=a[j];
                }
                a[i]=sum;
            }

        }
    }

    public static void main(String[] args) {
//        int[] a = {1, 2, -3, 4, 5, 4};
//        int n = 3;
        int[] a = {1, -1, -1, 10, 5, -1};
        int n=2;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
