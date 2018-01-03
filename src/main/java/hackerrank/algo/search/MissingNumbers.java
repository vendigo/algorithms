package hackerrank.algo.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MissingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arrA = readArray(scanner);
        Map<Integer, Long> aCounts = countElements(arrA);
        List<Integer> arrB = readArray(scanner);
        Map<Integer, Long> bCounts = countElements(arrB);

        Set<Integer> missingNumbers = new TreeSet<>();
        bCounts.forEach((number, count) -> {
            if (!aCounts.containsKey(number) || aCounts.get(number) < count) {
                missingNumbers.add(number);
            }
        });

        Iterator<Integer> iter = missingNumbers.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
            if (iter.hasNext()) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static List<Integer> readArray(Scanner scanner) {
        int n = scanner.nextInt();
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(scanner.nextInt());
        }
        return arr;
    }

    private static Map<Integer, Long> countElements(List<Integer> arr) {
        return arr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
