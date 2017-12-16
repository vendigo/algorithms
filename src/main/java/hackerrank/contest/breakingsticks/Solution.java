package hackerrank.contest.breakingsticks;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Long> aArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            aArr.add(scanner.nextLong());
        }
        System.out.println(solve(aArr));
    }

    static long solve(List<Long> arr) {
        Map<Long, Long> cache = new HashMap<>();
        return arr.stream()
                .mapToLong(Number::longValue)
                .map(s -> solveOne(s, cache))
                .sum();
    }

    static long solveOne(long num, Map<Long, Long> cache) {
        if (num == 1) {
            return 1;
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        long maxRes = 0;
        for (long i = 2; i < num; i++) {
            if (num % i == 0) {
                long rest = num / i;
                long res = 1 + solveOne(i, cache) * rest;
                if (res > maxRes) {
                    maxRes = res;
                }
            }
        }
        if (maxRes == 0) { //Prime number
            maxRes = num + 1;
        }

        cache.put(num, maxRes);
        return maxRes;
    }

}
