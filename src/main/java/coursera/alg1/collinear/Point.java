package coursera.alg1.collinear;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }

        return (y - that.y)/(x - that.x);
    }

    public int compareTo(Point that) {
        int yCompare = Double.compare(y, that.y);

        if (yCompare == 0) {
            return Double.compare(x, that.y);
        } else {
            return yCompare;
        }
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeComparator(this);
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private static class SlopeComparator implements Comparator<Point> {
        private final Point point;

        public SlopeComparator(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(point.slopeTo(o1), point.slopeTo(o2));
        }
    }

    public static void main(String[] args) {
    }
}
