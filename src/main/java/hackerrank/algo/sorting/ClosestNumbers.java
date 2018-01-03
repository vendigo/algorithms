package hackerrank.algo.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClosestNumbers {
    static Integer[] closestNumbers(int[] arr) {
        Arrays.sort(arr);

        //Find min diff
        int minDiff = Math.abs(arr[0] - arr[1]);
        for (int i = 0; i < arr.length-1; i++) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        //Second iteration - select pairs with min diff
        List<Integer> closest = new ArrayList<>();
        for (int i = 0; i < arr.length-1; i++) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if (diff == minDiff) {
                closest.add(arr[i]);
                closest.add(arr[i+1]);
            }
        }
        return closest.toArray(new Integer[closest.size()]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        Integer[] result = closestNumbers(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }
}
