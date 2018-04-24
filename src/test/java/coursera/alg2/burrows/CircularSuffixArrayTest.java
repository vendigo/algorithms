package coursera.alg2.burrows;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CircularSuffixArrayTest {

    @Test
    public void abra() {
        CircularSuffixArray suffixes = new CircularSuffixArray("ABRACADABRA!");
        assertThat(suffixes.index(0), equalTo(11));
        assertThat(suffixes.index(1), equalTo(10));
        assertThat(suffixes.index(2), equalTo(7));
        assertThat(suffixes.index(3), equalTo(0));
        assertThat(suffixes.index(4), equalTo(3));
        assertThat(suffixes.index(5), equalTo(5));
        assertThat(suffixes.index(6), equalTo(8));
        assertThat(suffixes.index(7), equalTo(1));
        assertThat(suffixes.index(8), equalTo(4));
        assertThat(suffixes.index(9), equalTo(6));
        assertThat(suffixes.index(10), equalTo(9));
        assertThat(suffixes.index(11), equalTo(2));
    }

    @Test
    public void correct1() {
        CircularSuffixArray suffixes = new CircularSuffixArray("ABAAAABBAA");
        assertThat(suffixes.length(), equalTo(10));
        assertThat(suffixes.index(0), equalTo(2));
    }

    @Test
    public void correct2() {
        CircularSuffixArray suffixes = new CircularSuffixArray("SINHPJVGBZ");
        assertThat(suffixes.length(), equalTo(10));
        assertThat(suffixes.index(0), equalTo(8));
    }

    @Test
    public void correct3() {
        char[] chars = { 0x55, 0x7b, 0x10, 0x42, 0x11, 0x3c, 0x0a, 0x21, 0x15, 0x70 };
        CircularSuffixArray suffixes = new CircularSuffixArray(new String(chars));
        assertThat(suffixes.length(), equalTo(10));
        assertThat(suffixes.index(0), equalTo(6));
    }
}
