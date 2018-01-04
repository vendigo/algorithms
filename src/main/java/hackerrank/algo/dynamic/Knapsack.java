package hackerrank.algo.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int ti = 0; ti < t; ti++) {
            int n = scanner.nextInt();
            int expectedSum = scanner.nextInt();
            List<Integer> values = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                values.add(scanner.nextInt());
            }
            System.out.println(knapsack(values, expectedSum));
        }
        scanner.close();
    }

    static int knapsack(List<Integer> values, int expectedSum) {
        return subKnapsack(values, values.size() - 1, expectedSum, new HashMap<>());
    }

    private static int subKnapsack(List<Integer> values, int n, int expectedSum, Map<String, Integer> cache) {
        if (n == -1 || expectedSum == 0) {
            return 0;
        }

        String key = n + "-" + expectedSum;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int lastValue = values.get(n);
        int maxValue = subKnapsack(values, n - 1, expectedSum, cache); //Non picking option
        int whatIsLeft = expectedSum - lastValue;
        int pickedLast = lastValue;

        while (whatIsLeft >= 0) {
            int currentValue = subKnapsack(values, n - 1, whatIsLeft, cache) + pickedLast;
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
            whatIsLeft -= lastValue;
            pickedLast += lastValue;
        }

        cache.put(key, maxValue);
        return maxValue;
    }
}
