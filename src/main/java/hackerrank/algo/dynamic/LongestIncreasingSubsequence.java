package hackerrank.algo.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(scanner.nextInt());
        }

        System.out.println(lis(numbers));
        scanner.close();
    }

    static int lis(List<Integer> numbers) {
        List<Integer> tails = new ArrayList<>(numbers.size());
        tails.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);

            if (currentNumber <= tails.get(0)) { //We found new smallest element
                tails.set(0, currentNumber);
            } else if (currentNumber > tails.get(tails.size() - 1)) { //We found biggest element
                tails.add(currentNumber);
            } else { //We need to find place for inserting
                int index = findIndex(tails, currentNumber);
                tails.set(index, currentNumber);
            }
        }
        return tails.size();
    }

    static int findIndex(List<Integer> tails, int currentNumber) {
        int l = 0;
        int r = tails.size() - 1;

        while (l + 1 < r) {
            int pos = l + (r - l) / 2;
            if (tails.get(pos) >= currentNumber) {
                r = pos;
            } else {
                l = pos;
            }
        }
        return r;
    }
}
