package coursera.alg1.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.List;


public class KdTree {
    private static final double RADIUS = 0.005;
    private Node root;
    private int size;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        notNull(p);
        root = put(root, p, true);
    }

    private Node put(Node n, Point2D point, boolean xNode) {
        if (n == null) {
            size++;
            return new Node(xNode, point);
        }
        int cmp = n.compareTo(point);
        if (cmp < 0) n.leftChild = put(n.leftChild, point, !xNode);
        else if (cmp > 0) n.rightChild = put(n.rightChild, point, !xNode);
        return n;
    }

    public boolean contains(Point2D p) {
        notNull(p);
        return find(root, p);
    }

    private boolean find(Node n, Point2D point) {
        if (n == null) return false;
        int cmp = n.compareTo(point);
        if (cmp < 0) return find(n.leftChild, point);
        else return cmp <= 0 || find(n.rightChild, point);
    }

    public void draw() {
        draw(root, null, true);
        StdDraw.show();
    }

    private void draw(Node n, Node parent, boolean leftPart) {
        if (n == null) {
            return;
        }

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledCircle(n.point.x(), n.point.y(), RADIUS);
        if (n.xNode) {
            StdDraw.setPenColor(Color.RED);
            double start = 0;
            double finish = 1;

            if (parent != null) {
                if (leftPart) {
                    finish = parent.point.y();
                } else {
                    start = parent.point.y();
                }
            }

            StdDraw.line(n.point.x(), start, n.point.x(), finish);
        } else {
            StdDraw.setPenColor(Color.BLUE);
            double start = 0;
            double finish = 1;

            if (parent != null) {
                if (leftPart) {
                    finish = parent.point.x();
                } else {
                    start = parent.point.x();
                }
            }

            StdDraw.line(start, n.point.y(), finish, n.point.y());
        }

        draw(n.leftChild, n, true);
        draw(n.rightChild, n, false);
    }

    public Iterable<Point2D> range(RectHV rect) {
        notNull(rect);


        return null;
    }

    private void range(List<Point2D> inRange, Node node, Node parent, RectHV rect) {

    }

    public Point2D nearest(Point2D p) {
        notNull(p);

        return null;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }

    private static class Node implements Comparable<Point2D> {
        boolean xNode;
        Point2D point;
        Node leftChild;
        Node rightChild;

        public Node(boolean xNode, Point2D point) {
            this.xNode = xNode;
            this.point = point;
        }

        public int compareTo(Point2D o) {
            return xNode ? Double.compare(o.x(), point.x()) : Double.compare(o.y(), point.y());
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        kdTree.draw();
    }
}
