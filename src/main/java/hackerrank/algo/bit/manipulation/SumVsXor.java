package hackerrank.algo.bit.manipulation;

import java.util.Scanner;

public class SumVsXor {
    static long solve(long n) {
        if (n == 0) {
            return 1;
        }

        int zeroCount = Long.toBinaryString(n).length() - Long.bitCount(n);
        return (long)Math.pow(2, zeroCount);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long result = solve(n);
        System.out.println(result);
    }
}
