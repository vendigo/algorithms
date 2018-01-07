package coursera.alg1.collinear;

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        notNull(points);
    }

    public int numberOfSegments() {
        return 0;
    }

    public LineSegment[] segments() {
        return null;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }
}
