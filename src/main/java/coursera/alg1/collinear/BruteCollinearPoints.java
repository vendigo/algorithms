package coursera.alg1.collinear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        findLineSegments(points);
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private void findLineSegments(Point[] points) {
        if (points.length < 4) {
            return;
        }

        for (int i1 = 0; i1 < points.length; i1++) {
            for (int i2 = i1 + 1; i2 < points.length; i2++) {
                for (int i3 = i2 + 1; i3 < points.length; i3++) {
                    for (int i4 = i3 + 1; i4 < points.length; i4++) {
                        LineSegment lineSegment = lineSegment(points[i1], points[i2], points[i3], points[i4]);
                        if (lineSegment != null) {
                            lineSegments.add(lineSegment);
                        }
                    }
                }
            }
        }
    }

    private static LineSegment lineSegment(Point p1, Point p2, Point p3, Point p4) {
        notEqual(p1, p2, p3, p4);
        double a1 = p1.slopeTo(p2);
        double a2 = p1.slopeTo(p3);
        double a3 = p1.slopeTo(p4);
        if (a1 == a2 && a1 == a3) {
            List<Point> line = new ArrayList<>();
            line.add(p1);
            line.add(p2);
            line.add(p3);
            line.add(p4);
            Collections.sort(line);
            return new LineSegment(line.get(0), line.get(3));
        } else {
            return null;
        }
    }

    private static void notEqual(Point p1, Point p2, Point p3, Point p4) {
        if (p1.equals(p2) || p1.equals(p3) || p1.equals(p4) || p2.equals(p3) || p3.equals(p4)) {
            throw new IllegalArgumentException("Found two equal points");
        }
    }

    private void validatePoints(Point[] points) {
        notNull(points);
        for (Point point : points) {
            notNull(point);
        }
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }
}
