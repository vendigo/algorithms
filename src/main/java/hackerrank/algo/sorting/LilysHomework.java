package hackerrank.algo.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LilysHomework {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Long> arr = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            arr.add(scanner.nextLong());
        }
        System.out.println(solution(arr));
    }

    static long solution(List<Long> arr) {
        List<Long> sorted = new ArrayList<>(arr);
        sorted.sort(Comparator.naturalOrder());

        List<Long> arrCopy = new ArrayList<>(arr);
        List<Long> inverted = new ArrayList<>(arr);
        inverted.sort(Comparator.reverseOrder());

        return Math.min(countSwaps(arr, sorted), countSwaps(arrCopy, inverted));
    }

    private static long countSwaps(List<Long> arr, List<Long> sorted) {
        Map<Long, Integer> valueToPosition = groupValues(arr);
        long swaps = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).equals(sorted.get(i))) {
                int swapPos = valueToPosition.get(sorted.get(i));
                valueToPosition.put(arr.get(i), swapPos);
                Collections.swap(arr, i, swapPos);
                swaps++;
            }
        }
        return swaps;
    }

    private static Map<Long, Integer> groupValues(List<Long> list) {
        Map<Long, Integer> result = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            result.put(list.get(i), i);
        }

        return result;
    }
}
