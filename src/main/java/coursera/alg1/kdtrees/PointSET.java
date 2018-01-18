package coursera.alg1.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private static final double RADIUS = 0.005;
    private TreeSet<Point2D> points = new TreeSet<>();

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        notNull(p);
        points.add(p);
    }

    public boolean contains(Point2D p) {
        notNull(p);
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            StdDraw.filledCircle(point.x(), point.y(), RADIUS);
        }
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        notNull(rect);
        List<Point2D> inRange = new LinkedList<>();
        for (Point2D p : inRange) {
            if (rect.contains(p)) {
                inRange.add(p);
            }
        }
        return inRange;
    }

    public Point2D nearest(Point2D p) {
        notNull(p);
        double minDistance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;

        for (Point2D d : points) {
            if (nearestPoint == null) {
                nearestPoint = d;
                minDistance = p.distanceSquaredTo(d);
            } else {
                double distance = p.distanceSquaredTo(d);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPoint = d;
                }
            }
        }
        return nearestPoint;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }

    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.2, 0.2));
        pointSET.insert(new Point2D(0.3, 0.3));
        pointSET.insert(new Point2D(0.4, 0.4));
        pointSET.insert(new Point2D(0.2, 0.8));
        pointSET.draw();
    }
}
