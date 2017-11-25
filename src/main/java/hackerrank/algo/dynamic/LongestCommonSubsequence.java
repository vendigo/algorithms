package hackerrank.algo.dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer> a = readArray(scanner, n);
        List<Integer> b = readArray(scanner, m);
        List<Integer> solution = solution(a, b);
        for (int i = 0; i < solution.size() - 1; i++) {
            System.out.print(solution.get(i) + " ");
        }
        System.out.println(solution.get(solution.size() - 1));

        scanner.close();
    }

    private static List<Integer> readArray(Scanner scanner, int n) {
        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }
        return a;
    }

    static List<Integer> solution(List<Integer> a, List<Integer> b) {
        int n = a.size();
        int m = b.size();
        int[][] matrix = new int[n + 1][m + 1];

        //Count length
        for (int row = 0; row <= n; row++) {
            for (int column = 0; column <= m; column++) {
                if (row == 0 || column == 0) {
                    matrix[row][column] = 0;
                } else if (a.get(row - 1).equals(b.get(column - 1))) {
                    matrix[row][column] = 1 + matrix[row - 1][column - 1];
                } else {
                    matrix[row][column] = Math.max(matrix[row - 1][column], matrix[row][column - 1]);
                }
            }
        }

        //Reconstruct sequence
        int row = n;
        int column = m;
        List<Integer> solution = new ArrayList<>(matrix[row][column]);

        while (row > 0 && column > 0) {
            if (a.get(row - 1).equals(b.get(column - 1))) {
                solution.add(a.get(row - 1));
                row--;
                column--;
            } else if (matrix[row - 1][column] > matrix[row][column - 1]) {
                row--;
            } else {
                column--;
            }
        }

        Collections.reverse(solution);
        return solution;
    }
}