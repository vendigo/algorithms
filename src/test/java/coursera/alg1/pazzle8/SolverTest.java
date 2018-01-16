package coursera.alg1.pazzle8;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;

import static coursera.alg1.pazzle8.BoardTest.readBoard;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SolverTest {
    @Test
    public void alreadySolved() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle3x3-00.txt"));
        Solver solver = new Solver(board);
        assertThat(solver.isSolvable(), equalTo(true));
        assertThat(solver.moves(), equalTo(0));
        assertThat(solver.solution(), equalTo(Collections.emptyList()));
    }

    @Test
    public void oneMoveToSolve() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle3x3-01.txt"));
        Solver solver = new Solver(board);
        for (Board b : solver.solution()) {
            System.out.println(b);
            System.out.println();
        }
        assertThat(solver.isSolvable(), equalTo(true));
        assertThat(solver.moves(), equalTo(1));
    }

    @Test
    public void unsolvable() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle3x3-unsolvable.txt"));
        Solver solver = new Solver(board);
        assertThat(solver.isSolvable(), equalTo(false));
        assertThat(solver.moves(), equalTo(-1));
        assertThat(solver.solution(), nullValue());
    }

    @Test
    public void solve4x4_05() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle4x4-05.txt"));
        Solver solver = new Solver(board);
        System.out.println(solver.isSolvable());
        System.out.println(solver.moves());
    }

    @Test
    @Ignore
    public void solve4x4_80() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle4x4-80.txt"));
        Solver solver = new Solver(board);
        System.out.println(solver.isSolvable());
        System.out.println(solver.moves());
    }

    @Test
    public void solve00() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle00.txt"));
        Solver solver = new Solver(board);
        assertThat(solver.isSolvable(), equalTo(true));
        assertThat(solver.moves(), equalTo(0));
    }

    @Test
    public void solve07() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle07.txt"));
        Solver solver = new Solver(board);
        assertThat(solver.isSolvable(), equalTo(true));
        assertThat(solver.moves(), equalTo(7));
    }

    @Test
    public void solve08() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle08.txt"));
        Solver solver = new Solver(board);
        assertThat(solver.isSolvable(), equalTo(true));
        assertThat(solver.moves(), equalTo(8));
    }

    @Test
    public void solve3x3_25() throws Exception {
        Board board = readBoard(BoardTest.p.file("puzzle3x3-25.txt"));
        Solver solver = new Solver(board);
        System.out.println(solver.isSolvable());
        System.out.println(solver.moves());
    }
}
