package coursera.alg1.pazzle8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int[][] blocks;
    private final int size;
    private int manhattan;
    private int hamming;

    public Board(int[][] blocks) {
        this(blocks, true);
    }

    private Board(int[][] blocks, boolean copyBoard) {
        notNull(blocks);
        this.blocks = copyBoard ? cloneBoard(blocks) : blocks;
        this.size = blocks.length;
        this.manhattan = computeManhattan();
        this.hamming = computeHamming();
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        return hamming;
    }

    private int computeManhattan() {
        int res = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                res += distance(row, col, blocks[row][col]);
            }
        }

        return res;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        return hamming == 0;
    }

    public Board twin() {
        int row1 = 0;
        int col1 = 0;
        int row2 = 0;
        int col2 = 1;

        if (blocks[0][0] == 0) { //Two not empty out of (0, 0), (0, 1), (1, 0)
            row1 = 1;
        } else if (blocks[0][1] == 0) {
            row2 = 1;
            col2 = 0;
        }

        int[][] newBoard = cloneBoard(blocks);
        newBoard[row1][col1] = blocks[row2][col2];
        newBoard[row2][col2] = blocks[row1][col1];

        return new Board(newBoard, false);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return size == board.size && Arrays.deepEquals(blocks, board.blocks);
    }

    public Iterable<Board> neighbors() {
        int zeroRow = 0; //Find empty cell
        int zeroColumn = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (blocks[row][col] == 0) {
                    zeroColumn = col;
                    zeroRow = row;
                }
            }
        }

        List<Board> neighbors = new LinkedList<>();
        int[][] newBlocks;

        if (withinBounds(zeroRow - 1, zeroColumn)) {
            newBlocks = cloneBoard(blocks);
            swap(newBlocks, zeroRow, zeroColumn, zeroRow - 1, zeroColumn);
            neighbors.add(new Board(newBlocks, false));
        }

        if (withinBounds(zeroRow + 1, zeroColumn)) {
            newBlocks = cloneBoard(blocks);
            swap(newBlocks, zeroRow, zeroColumn, zeroRow + 1, zeroColumn);
            neighbors.add(new Board(newBlocks, false));
        }

        if (withinBounds(zeroRow, zeroColumn - 1)) {
            newBlocks = cloneBoard(blocks);
            swap(newBlocks, zeroRow, zeroColumn, zeroRow, zeroColumn - 1);
            neighbors.add(new Board(newBlocks, false));
        }

        if (withinBounds(zeroRow, zeroColumn + 1)) {
            newBlocks = cloneBoard(blocks);
            swap(newBlocks, zeroRow, zeroColumn, zeroRow, zeroColumn + 1);
            neighbors.add(new Board(newBlocks, false));
        }

        return neighbors;
    }

    private boolean withinBounds(int row, int col) {
        return row < size && row >= 0 && col >= 0 && col < size;
    }

    private void swap(int[][] arr, int row1, int col1, int row2, int col2) {
        int tmp = arr[row1][col1];
        arr[row1][col1] = arr[row2][col2];
        arr[row2][col2] = tmp;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size).append("\n");

        String format = "%2d";
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                builder.append(String.format(format, blocks[row][col]));
                if (col < size - 1) {
                    builder.append(" ");
                }
            }
            if (row < size - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private int[][] cloneBoard(int[][] board) {
        int[][] cloned = new int[board.length][];
        for (int row = 0; row < board.length; row++) {
            cloned[row] = board[row].clone();
        }
        return cloned;
    }

    private int computeHamming() {
        int res = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != targetValue(row, col)) {
                    res++;
                }
            }
        }
        return res;
    }

    private int distance(int row, int col, int value) {
        if (value == 0) {
            return 0;
        }
        int verticalTarget = (value - 1) / size;
        int horizontalTarget = (value - 1) % size;
        return Math.abs(row - verticalTarget) + Math.abs(col - horizontalTarget);
    }

    private int targetValue(int row, int col) {
        return row * size + col + 1;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }
}
