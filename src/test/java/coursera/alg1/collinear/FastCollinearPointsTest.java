package coursera.alg1.collinear;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FastCollinearPointsTest {
    @Test
    public void createLineSegment() throws Exception {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        LineSegment segment = FastCollinearPoints.createSegment(p2, p3, p1);
        assertThat(segment, equalTo(new LineSegment(p1, p3)));
    }
}
