package coursera.alg1.pazzle8;

import coursera.TestFilePath;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BoardTest {
    static TestFilePath p = new TestFilePath("coursera/alg1/pazzle8");

    @Test
    public void case0() throws Exception {
        Board board1 = readBoard(p.file("puzzle3x3-00.txt"));
        System.out.println("Board");
        System.out.println(board1);
        assertThat(board1.dimension(), equalTo(3));
        assertThat(board1.isGoal(), equalTo(true));
        assertThat(board1.hamming(), equalTo(0));
        assertThat(board1.manhattan(), equalTo(0));
        System.out.println("Twin board");
        System.out.println(board1.twin());
        System.out.println("Neighbors");
        board1.neighbors().forEach(n -> System.out.println(n + "\n"));
    }

    @Test
    public void case1() throws Exception {
        Board board1 = readBoard(p.file("puzzle3x3-01.txt"));
        assertThat(board1.isGoal(), equalTo(false));
        assertThat(board1.hamming(), equalTo(1));
        assertThat(board1.manhattan(), equalTo(1));
    }

    @Test
    public void board00() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle00.txt"));
        assertThat(board.manhattan(), equalTo(0));
    }

    @Test
    public void puzzle27() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle27_1.txt"));
        assertThat(board.hamming(), equalTo(7));
    }

    static Board readBoard(String fileName) {
        int[][] blocks = readBlocks(fileName);
        return new Board(blocks);
    }

    static int[][] readBlocks(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        return blocks;
    }
}
