package coursera.alg2.boggle;

import com.google.common.collect.Sets;
import coursera.TestFilePath;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class BoggleSolverTest {
    private TestFilePath p = new TestFilePath("coursera/alg2/boggle");

    @Test
    public void scoreOf() throws Exception {
        String[] simpleDictionary = readDictionary("simpleDictionary.txt");
        BoggleSolver solver = new BoggleSolver(simpleDictionary);
        assertThat(solver.scoreOf("HELLO"), equalTo(2));
        assertThat(solver.scoreOf("MONKEY"), equalTo(3));
        assertThat(solver.scoreOf("ROBOT"), equalTo(2));
        assertThat(solver.scoreOf("NO"), equalTo(0));
        assertThat(solver.scoreOf("LORD"), equalTo(0));
        assertThat(solver.scoreOf("QUIT"), equalTo(1));
    }

    @Test
    public void simpleCase() throws Exception {
        testSolver("simpleDictionary.txt", "simpleBoard.txt",
                "HELLO", "MONKEY", "ROBOT", "HOKYR", "HELLONO", "HELNOMO");
    }

    @Test
    public void test1() throws Exception {
        Set<String> words = solve("dictionary-algs4.txt", "board4x4.txt");
        assertThat(words, hasSize(29));
    }

    @Test
    public void test2() throws Exception {
        Set<String> words = solve("dictionary-algs4.txt", "board-q.txt");
        assertThat(words, hasSize(29));
    }

    @Test
    public void test16q() throws Exception {
        solve("dictionary-16q.txt", "board-16q.txt");
    }

    @Test
    public void aqua() throws Exception {
        testSolver("dictionary-common.txt", "board-aqua.txt",
                "QUA");
    }

    @Test
    public void yawlDictionaryRandomBoards() {
        BoggleSolver solver = new BoggleSolver(readDictionary("dictionary-yawl.txt"));
        for (int i = 0; i < 50; i++) {
            solver.getAllValidWords(new BoggleBoard());
        }
    }

    private Set<String> solve(String dictionaryFile, String boardFile) {
        String[] simpleDictionary = readDictionary(dictionaryFile);
        BoggleBoard simpleBoard = new BoggleBoard(p.file(boardFile));
        BoggleSolver solver = new BoggleSolver(simpleDictionary);
        return (Set<String>) solver.getAllValidWords(simpleBoard);
    }

    private void testSolver(String dictionaryFile, String boardFile, String... expected) {
        Set<String> actualWords = solve(dictionaryFile, boardFile);
        Set<String> expectedWords = Sets.newHashSet(expected);
        assertThat(actualWords, equalTo(expectedWords));
    }

    private String[] readDictionary(String fileName) {
        In in = new In(p.file(fileName));
        return in.readAllStrings();
    }
}
