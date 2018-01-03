package hackerrank.algo.dynamic;

import java.util.Scanner;

public class SamAndSubstrings {
    private static final long MOD = 1_000_000_000 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        long result = sum(s);
        System.out.println(result);
        in.close();
    }

    static long sum(String s) {
        int n = s.length();
        long sum = 0;
        long mn = 0;
        for (int i = n - 1; i >= 0; i--) {
            int ai = Character.getNumericValue(s.charAt(i));
            int iPlus1 = i + 1;
            mn = (10 * mn + 1) % MOD;

            long part = ai * iPlus1 * mn;
            part %= MOD;
            sum += part;
            sum %= MOD;
        }
        return sum;
    }
}
