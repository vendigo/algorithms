package coursera.alg1.collinear;

import coursera.TestFilePath;
import org.junit.Test;

import java.util.Arrays;

import static coursera.alg1.collinear.BruteCollinearPointsTest.readPoints;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FastCollinearPointsTest {
    @Test
    public void createLineSegment() throws Exception {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        //FastCollinearPoints.Segment segment = FastCollinearPoints.createSegment(p2, p3, p1);
        //assertThat(segment, equalTo(new FastCollinearPoints.Segment(p1, p3)));
    }

    private TestFilePath p = new TestFilePath("coursera/alg1/collinear");

    @Test
    public void simpleFast() throws Exception {
        Point[] points = new Point[]{
                new Point(0, 0),
                new Point(2, 3),
                new Point(2, 2),
                new Point(3, 3),
                new Point(1, 1)
        };
        FastCollinearPoints solution = new FastCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
    }

    @Test
    public void horizontal() throws Exception {
        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(4, 1),
                new Point(5, 1),
                new Point(3, 1),
                new Point(0, 6)
        };
        FastCollinearPoints solution = new FastCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
        LineSegment lineSegment = solution.segments()[0];
        assertThat(lineSegment.getP(), equalTo(new Point(1, 1)));
        assertThat(lineSegment.getQ(), equalTo(new Point(5, 1)));
    }

    @Test
    public void vertical() throws Exception {
        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(1, 4),
                new Point(1, 5),
                new Point(1, 3),
                new Point(0, 6)
        };
        FastCollinearPoints solution = new FastCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
        LineSegment lineSegment = solution.segments()[0];
        assertThat(lineSegment.getP(), equalTo(new Point(1, 1)));
        assertThat(lineSegment.getQ(), equalTo(new Point(1, 5)));
    }

    @Test
    public void input8Fast() throws Exception {
        Point[] points = readPoints(p.file("input8.txt"));
        FastCollinearPoints solution = new FastCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(2));
    }

    @Test
    public void input6Fast() throws Exception {
        Point[] points = readPoints(p.file("input6.txt"));
        FastCollinearPoints solution = new FastCollinearPoints(points);
        System.out.println(Arrays.toString(solution.segments()));
        assertThat(solution.numberOfSegments(), equalTo(1));
    }
}
