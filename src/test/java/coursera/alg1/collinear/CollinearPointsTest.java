package coursera.alg1.collinear;

import coursera.TestFilePath;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CollinearPointsTest {
    private TestFilePath p = new TestFilePath("coursera/alg1/collinear");

    @Test
    public void simpleBrute() throws Exception {
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(2, 3),
                new Point(2, 2),
                new Point(3, 3),
                new Point(1, 1)
        };
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(1));
    }

    @Test
    public void simpleFast() throws Exception {
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(2, 3),
                new Point(2, 2),
                new Point(3, 3),
                new Point(1, 1)
        };
        FastCollinearPoints solution = new FastCollinearPoints(points);
        System.out.println(Arrays.toString(solution.segments()));
        assertThat(solution.numberOfSegments(), equalTo(1));
    }

    @Test
    public void input8Brute() throws Exception {
        Point[] points = readPoints(p.file("input8.txt"));
        BruteCollinearPoints solution = new BruteCollinearPoints(points);
        assertThat(solution.numberOfSegments(), equalTo(2));
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
        assertThat(solution.numberOfSegments(), equalTo(1));
    }

    private Point[] readPoints(String fileName) {
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
