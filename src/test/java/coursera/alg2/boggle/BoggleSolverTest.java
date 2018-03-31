package coursera.alg2.boggle;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BoggleSolverTest {

    private static final String[] simpleDictionary = {
            "HELLO",
            "MONKEY",
            "ROBOT",
            "NO"
    };

    private static final char[][] chars = {new char[]{'H', 'E', 'L', 'L'},
            new char[]{'M', 'O', 'N', 'O'},
            new char[]{'O', 'T', 'K', 'E'},
            new char[]{'B', 'O', 'R', 'Y'}};

    @Test
    public void simpleCase() throws Exception {
        BoggleSolver solver = new BoggleSolver(simpleDictionary);
        assertThat(solver.scoreOf("HELLO"), equalTo(2));
        assertThat(solver.scoreOf("MONKEY"), equalTo(3));
        assertThat(solver.scoreOf("ROBOT"), equalTo(2));
        assertThat(solver.scoreOf("NO"), equalTo(0));
        assertThat(solver.scoreOf("LORD"), equalTo(0));

        Set<String> validWords = (Set<String>) solver.getAllValidWords(new BoggleBoard(chars));
        assertThat(validWords, equalTo(Sets.newHashSet("HELLO", "MONKEY", "ROBOT")));
    }
}
