package coursera.alg1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] openCells;
    private int openCount = 0;
    private int n;
    private int last;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        int size = n * (n + 2) + 2;
        this.openCells = new boolean[size];
        this.uf = new WeightedQuickUnionUF(size);
        this.n = n;
        last = n * (n + 2) + 1;
        initAuxCells();
    }

    public void open(int row, int col) {
        checkBounds(row, col);
        int ind = index(row, col);

        if (!openCells[ind]) {
            openCells[ind] = true;
            openCount++;
        }

        joinNeighbors(row, col);
    }

    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return openCells[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return uf.connected(0, index(row, col));
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(0, last);
    }

    private int index(int row, int col) {
        return row * n + col;
    }

    private void initAuxCells() {
        for (int i = 1; i <= n; i++) {
            openCells[i] = true;
            uf.union(0, i);
        }

        int from = n * (n + 1) + 1;
        int to = n * (n + 2);
        for (int i = from; i <= to; i++) {
            openCells[i] = true;
            uf.union(last, i);
        }
    }

    private void joinNeighbors(int row, int col) {
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                uf.union(index(row, col - 1), index(row, col));
            }
        }

        if (col < n) {
            if (isOpen(row, col+1)) {
                uf.union(index(row, col+1), index(row, col));
            }
        }

        if (openCells[index(row - 1, col)]) {
            uf.union(index(row -1, col), index(row, col));
        }

        if (openCells[index(row+1, col)]) {
            uf.union(index(row+1, col), index(row, col));
        }
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }
}
