package hackerrank.algo.bit.manipulation;

import java.util.Scanner;

public class LonelyInteger {
    static int lonelyinteger(int[] a) {
        int sum = 0;
        for (int el: a) {
            sum ^= el;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int result = lonelyinteger(a);
        System.out.println(result);
    }
}
