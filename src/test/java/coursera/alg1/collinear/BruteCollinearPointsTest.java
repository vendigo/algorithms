package coursera.alg1.collinear;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BruteCollinearPointsTest {
    @Test
    public void lineSegment0() throws Exception {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 1);

        assertThat(BruteCollinearPoints.lineSegment(p1, p3, p4, p5), equalTo(new LineSegment(p1, p4)));
        assertThat(BruteCollinearPoints.lineSegment(p1, p3, p2, p5), nullValue());
    }
}
