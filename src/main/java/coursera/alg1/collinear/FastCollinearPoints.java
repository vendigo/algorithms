package coursera.alg1.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
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

        for (Point p : points) {
            Comparator<Point> comp = p.slopeOrder();
            List<Point> restPoints = new ArrayList<>(Arrays.asList(points));
            restPoints.sort(comp.thenComparing(Comparator.naturalOrder()));

            double prevSlope = p.slopeTo(restPoints.get(1));
            int equalSlopes = 1;
            for (int i = 2; i < restPoints.size(); i++) { // 0th element - p itself - skipped
                Point c = restPoints.get(i);
                double currentSlope = p.slopeTo(c);
                if (currentSlope == prevSlope) {
                    equalSlopes++;
                } else {
                    equalSlopes = 1;
                }
                if (equalSlopes >= 3) {
                    int firstElementIndex = i - equalSlopes + 1;
                    LineSegment lineSegment = createSegment(restPoints.get(firstElementIndex), restPoints.get(i), p);
                    lineSegments.add(lineSegment);
                }
                prevSlope = currentSlope;
            }

        }
    }

    static LineSegment createSegment(Point first, Point last, Point p) {
        if (p.compareTo(first) < 0) {
            first = p;
        }

        if (p.compareTo(last) > 0) {
            last = p;
        }

        return new LineSegment(first, last);
    }

    private void validatePoints(Point[] points) {
        notNull(points);
        Set<Point> uniquePoints = new HashSet<>();
        for (Point point : points) {
            notNull(point);
            uniquePoints.add(point);
        }
        if (points.length != uniquePoints.size()) {
            throw new IllegalArgumentException("Repeated points found");
        }
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }
}
