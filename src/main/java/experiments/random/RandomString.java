package experiments.random;

import java.util.HashSet;
import java.util.Set;

public class RandomString {
    private static final int RADIX = 36;

    public static void main(String[] args) {
        //checkNNumbers(10_000_000, "100000", "zzzzzz", 1_299_827, false);
        //printNStrings(100, "100000", "zzzzzz", 1_299_827);
        printStats();
    }

    private static void printStats() {
        long minNumber = Long.parseLong("100000", RADIX);
        long maxNumber = Long.parseLong("zzzzzz", RADIX);
        System.out.println("From: " + minNumber);
        System.out.println("To: " + maxNumber);
        System.out.println("Unique numbers: " + (maxNumber - minNumber));
    }

    private static void printNStrings(int n, String fromS, String toS, int inc) {
        long from = Long.parseLong(fromS, RADIX);
        long to = Long.parseLong(toS, RADIX);

        for (int i = 0; i < n; i++) {
            System.out.println(Long.toString(oneNumber(from, to, inc, i), RADIX).toUpperCase());
        }
    }

    private static void checkNNumbers(int n, String fromS, String toS, int inc, boolean printNumbers) {
        long from = Long.parseLong(fromS, RADIX);
        long to = Long.parseLong(toS, RADIX);

        Set<Long> numbers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            long next = oneNumber(from, to, inc, i);
            numbers.add(next);
            if (printNumbers) {
                System.out.println(next);
            }
        }
        System.out.println(numbers.size() + " unique numbers");
        if (numbers.size() != n) {
            System.out.println("Duplicate found!");
        }
    }

    private static long oneNumber(long from, long to, long inc, int i) {
        long diff = to - from;
        long add = (inc * i) % diff;
        return from + add;
    }
}
