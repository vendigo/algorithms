package hackerrank.algo.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Candies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> arr = new ArrayList<>(n);
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr.add(in.nextInt());
        }
        long result = candies(arr);
        System.out.println(result);
        in.close();
    }

    static long candies(List<Integer> arr) {
        int n = arr.size();
        int nextCount = 0;
        int prev = 0;
        List<Integer> dist = new ArrayList<>(n);
        for (int r : arr) {
            if (r > prev) {
                nextCount++;
            } else {
                nextCount = 1;
            }
            dist.add(nextCount);
            prev = r;
        }

        for (int i = n-2; i>=0; i--) {
            if (arr.get(i) > arr.get(i+1) && dist.get(i) <= dist.get(i+1)) {
                int newValue = dist.get(i+1) + 1;
                dist.set(i, newValue);
            }
        }
        return dist.stream().mapToLong(i-> ((long)i)).sum();
    }
}
