package hackerrank.ctci;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class RansomNote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        Map<String, Long> magazineWords = readCounting(scanner);
        Map<String, Long> noteWords = readCounting(scanner);

        System.out.println(enoughWords(magazineWords, noteWords)?"Yes":"No");
    }

    private static boolean enoughWords(Map<String, Long> magazineWords, Map<String, Long> noteWords) {
        for (Map.Entry<String, Long> entry : noteWords.entrySet()) {
            String word = entry.getKey();
            if (!magazineWords.containsKey(word) || magazineWords.get(word) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Long> readCounting(Scanner scanner) {
        return Stream.of(scanner.nextLine().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), counting()));
    }
}
