package hackerrank.ctci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DFS {
    private int[][] grid;
    private boolean[][] marked;
    private int[] size;
    private int count = 0;
    private int n;
    private int m;

    DFS(int[][] grid, int n, int m) {
        this.grid = grid;
        this.n = n;
        this.m = m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        System.out.println(new DFS(grid, n, m).solution());
    }

    int solution() {
        marked = new boolean[n][m];
        size = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !marked[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        return IntStream.of(size).max().orElse(0);
    }

    private void dfs(int i, int j) {
        marked[i][j] = true;
        size[count]++;
        for (Pair p : adj(i, j, n, m)) {
            if (grid[p.i][p.j] == 1 && !marked[p.i][p.j]) {
                dfs(p.i, p.j);
            }
        }

    }

    private List<Pair> adj(int i, int j, int n, int m) {
        List<Pair> adj = new ArrayList<>();

        if (inRange(i - 1, j - 1, n, m)) {
            adj.add(new Pair(i - 1, j - 1));
        }

        if (inRange(i - 1, j, n, m)) {
            adj.add(new Pair(i - 1, j));
        }

        if (inRange(i - 1, j + 1, n, m)) {
            adj.add(new Pair(i - 1, j + 1));
        }

        if (inRange(i, j + 1, n, m)) {
            adj.add(new Pair(i, j + 1));
        }

        if (inRange(i + 1, j + 1, n, m)) {
            adj.add(new Pair(i + 1, j + 1));
        }

        if (inRange(i + 1, j, n, m)) {
            adj.add(new Pair(i + 1, j));
        }

        if (inRange(i + 1, j - 1, n, m)) {
            adj.add(new Pair(i + 1, j - 1));
        }

        if (inRange(i, j - 1, n, m)) {
            adj.add(new Pair(i, j - 1));
        }
        return adj;
    }

    private boolean inRange(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    static class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
