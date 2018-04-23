package coursera.alg2.burrows;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CircularSuffixArrayTest {

    @Test
    public void abra() {
        CircularSuffixArray suffixes = new CircularSuffixArray("ABRACADABRA!");
        assertThat(suffixes.index(11), equalTo(0));
        assertThat(suffixes.index(10), equalTo(1));
        assertThat(suffixes.index(7), equalTo(2));
        assertThat(suffixes.index(0), equalTo(3));
        assertThat(suffixes.index(3), equalTo(4));
        assertThat(suffixes.index(5), equalTo(5));
        assertThat(suffixes.index(8), equalTo(6));
        assertThat(suffixes.index(1), equalTo(7));
        assertThat(suffixes.index(4), equalTo(8));
        assertThat(suffixes.index(6), equalTo(9));
        assertThat(suffixes.index(9), equalTo(10));
        assertThat(suffixes.index(2), equalTo(11));
    }
}
