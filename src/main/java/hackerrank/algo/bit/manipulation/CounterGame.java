package hackerrank.algo.bit.manipulation;

import java.util.Scanner;

public class CounterGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(winner(scanner.nextLong()));
        }
    }

    static String winner(long i) {
        int move = 0;

        while (i > 1) {
            if (isPowerOf2(i)) {
                i = i >> 1;
            } else {
                i = minusPowerOf2(i);
            }
            move++;
        }

        return move % 2 == 0 ? "Richard" : "Louise";
    }

    private static long minusPowerOf2(long i) {
        long a = i;
        int power = 0;
        while (a > 1) {
            a = a >> 1;
            power++;
        }
        long b = 1;
        for (int j = 0; j < power; j++) {
            b = b << 1;
        }
        return i - b;
    }

    private static boolean isPowerOf2(long i) {
        return Long.bitCount(i) == 1;
    }
}
