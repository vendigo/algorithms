package hackerrank.ctci;

import java.util.Scanner;

/*
  https://www.hackerrank.com/challenges/ctci-merge-sort/problem
*/
public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int d = scanner.nextInt();

        for (int i = 0; i < d; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            System.out.println(mergeSort(arr));
        }
    }

    static long mergeSort(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        long leftCount = mergeSort(arr, temp, left, mid);
        long rightCount = mergeSort(arr, temp, mid + 1, right);
        long mergeCount = mergeHalves(arr, temp, left, right);
        return leftCount + rightCount + mergeCount;
    }

    private static long mergeHalves(int[] arr, int[] temp, int left, int right) {
        int mid = (left + right) / 2;
        int i = left;
        int j = mid + 1;
        int k = left;
        long count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        System.arraycopy(arr, i, temp, k, mid - i + 1);
        System.arraycopy(arr, j, temp, k, right - j + 1);
        System.arraycopy(temp, left, arr, left, right - left + 1);
        return count;
    }
}
