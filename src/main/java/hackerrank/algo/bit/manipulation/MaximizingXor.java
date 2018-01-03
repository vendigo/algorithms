package hackerrank.algo.bit.manipulation;

import java.util.Scanner;

public class MaximizingXor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int r = scanner.nextInt();

        int max = l ^ r;
        for (int a = l; a <= r; a++) {
            for (int b = l; b <= r; b++) {
                int current = a ^ b;
                if (current > max) {
                    max = current;
                }
            }
        }

        System.out.println(max);
    }
}
