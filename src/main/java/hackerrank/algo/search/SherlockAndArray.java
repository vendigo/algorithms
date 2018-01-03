package hackerrank.algo.search;

import java.util.Scanner;

public class SherlockAndArray {
    static String solve(int[] a) {
        if (a.length == 0) {
            return "NO";
        }

        if (a.length == 1) {
            return "YES";
        }

        int sum = 0; //Sum without first element
        for (int i = 1; i < a.length; i++) {
            sum+=a[i];
        }

        int left = 0;
        int right = sum;

        for (int i = 1; i < a.length; i++) {
            if (left == right) {
                return "YES";
            }

            int prev = a[i-1];
            int current = a[i];
            left += prev;
            right -= current;
        }

        return "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int[] a = new int[n];
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
            }
            String result = solve(a);
            System.out.println(result);
        }
    }
}
