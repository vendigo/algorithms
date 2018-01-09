package coursera.alg1.collinear;

import coursera.TestFilePath;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BruteCollinearPointsTest {
    private TestFilePath p = new TestFilePath("coursera/alg1/collinear");

    @Test
    public void lineSegment0() throws Exception {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 1);

        //assertThat(BruteCollinearPoints.lineSegment(p1, p3, p4, p5), equalTo(new LineSegment(p1, p4)));
        //assertThat(BruteCollinearPoints.lineSegment(p1, p3, p2, p5), nullValue());
    }

    @Test
    public void simpleBrute() throws Exception {
        Point[] points = new Point[]{
                new Point(0, 0),
                new Point(2, 3),
                new Point(2, 2),
                new Point(3, 3),
                new Point(1, 1)
        };
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
        LineSegment lineSegment = solution.segments()[0];
        assertThat(lineSegment.getP(), equalTo(new Point(0, 0)));
        assertThat(lineSegment.getQ(), equalTo(new Point(3, 3)));
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
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
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
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
        LineSegment lineSegment = solution.segments()[0];
        assertThat(lineSegment.getP(), equalTo(new Point(1, 1)));
        assertThat(lineSegment.getQ(), equalTo(new Point(1, 5)));
    }

    @Test
    public void input8Brute() throws Exception {
        Point[] points = readPoints(p.file("input8.txt"));
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(2));
    }

    static Point[] readPoints(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }
}
