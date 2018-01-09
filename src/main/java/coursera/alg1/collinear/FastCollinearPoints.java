package coursera.alg1.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

        List<Point> restPoints = new ArrayList<>(Arrays.asList(points));
        List<Segment> segments = new ArrayList<>();

        for (Point p : points) {
            Comparator<Point> comp = p.slopeOrder();
            restPoints.sort(comp.thenComparing(Comparator.naturalOrder()));

            double prevSlope = p.slopeTo(restPoints.get(1));
            int equalSlopes = 1;
            for (int i = 2; i < restPoints.size(); i++) { // 0th element - p itself - skipped
                Point c = restPoints.get(i);
                double currentSlope = p.slopeTo(c);
                notEqual(p, c);
                if (currentSlope == prevSlope) {
                    equalSlopes++;
                } else {
                    equalSlopes = 1;
                }
                Double nextSlope = null;
                if (i < restPoints.size() - 1) {
                    nextSlope = p.slopeTo(restPoints.get(i+1));
                }
                if ((nextSlope == null || !nextSlope.equals(currentSlope)) && equalSlopes >= 3) {
                    int firstElementIndex = i - equalSlopes + 1;
                    Segment segment = createSegment(restPoints.get(firstElementIndex), restPoints.get(i), p);
                    segments.add(segment);
                }
                prevSlope = currentSlope;
            }
        }

        removeDuplicates(segments);
    }

    private void removeDuplicates(List<Segment> segments) {
        if (segments.size() > 1) {
            Collections.sort(segments);

            Segment prev = segments.get(0);
            lineSegments.add(prev.toLineSegment());
            for (int i = 1; i < segments.size(); i++) {
                Segment current = segments.get(i);
                if (!current.equals(prev)) {
                    lineSegments.add(current.toLineSegment());
                }
                prev = current;
            }
        }
    }

    private void notEqual(Point p, Point c) {
        if (p.equals(c)) {
            throw new IllegalArgumentException("Found two equal points");
        }
    }

    private static Segment createSegment(Point first, Point last, Point p) {
        if (p.compareTo(first) < 0) {
            first = p;
        }

        if (p.compareTo(last) > 0) {
            last = p;
        }

        return new Segment(first, last);
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

    private static class Segment implements Comparable<Segment> {
        private Point from;
        private Point to;
        private static final Comparator<Segment> comp = Comparator.comparing(Segment::getFrom)
                .thenComparing(Segment::getTo);

        Segment(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        Point getFrom() {
            return from;
        }

        Point getTo() {
            return to;
        }

        LineSegment toLineSegment() {
            return new LineSegment(from, to);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Segment segment = (Segment) o;
            return Objects.equals(from, segment.from) &&
                    Objects.equals(to, segment.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public int compareTo(Segment o) {
            return comp.compare(this, o);
        }
    }
}
