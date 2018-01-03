package hackerrank.algo.bit.manipulation;

import java.util.Scanner;

public class FlippingBits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            System.out.println(flipBytes(scanner.nextLong()));
        }
    }

    static long flipBytes(long n) {
        String s = Long.toBinaryString(n);
        s = fixLength(s);
        String flipped = flipStr(s);
        return Long.parseLong(flipped, 2);
    }

    private static String flipStr(String s) {
        StringBuilder flippedBuilder = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                flippedBuilder.append('1');
            } else {
                flippedBuilder.append('0');
            }
        }
        return flippedBuilder.toString();
    }

    private static String fixLength(String s) {
        if (s.length() < 32) {
            int needZeros = 32 - s.length();
            StringBuilder zeros = new StringBuilder();
            for (int i = 0; i < needZeros; i++) {
                zeros.append('0');
            }
            return zeros.toString() + s;
        }
        return s;
    }
}
