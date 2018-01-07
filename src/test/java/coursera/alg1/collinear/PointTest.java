package coursera.alg1.collinear;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PointTest {
    @Test
    public void slope() throws Exception {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(1, 5);
        assertThat(p1.slopeTo(p2), equalTo(Double.POSITIVE_INFINITY));
        assertThat(p2.slopeTo(p1), equalTo(Double.POSITIVE_INFINITY));
        assertThat(p1.slopeTo(p1), equalTo(Double.NEGATIVE_INFINITY));
    }
}
