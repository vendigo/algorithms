package coursera.alg1.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private static final double RADIUS = 0.01;
    private Node root;
    private int size;

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        kdTree.draw();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        notNull(p);
        root = put(root, null, p, true, true);
    }

    private Node put(Node n, Node parent, Point2D point, boolean xNode, boolean isLeft) {
        if (n == null) {
            size++;
            RectHV rect = buildRect(isLeft, parent);
            return new Node(xNode, point, rect);
        }
        int cmp = n.compareTo(point);
        if (cmp < 0) {
            n.leftChild = put(n.leftChild, n, point, !xNode, true);
        } else if (cmp > 0) {

            n.rightChild = put(n.rightChild, n, point, !xNode, false);
        }
        return n;
    }

    private RectHV buildRect(boolean isLeft, Node parent) {
        RectHV rect = parent == null ? new RectHV(0, 0, 1, 1) : parent.rect;
        Point2D parentPoint = parent == null ? new Point2D(1, 1) : parent.point;
        boolean xNode = parent == null || parent.xNode;

        if (isLeft) {
            if (xNode) {
                return new RectHV(rect.xmin(), rect.ymin(), parentPoint.x(), rect.ymax());
            } else {
                return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), parentPoint.y());
            }
        } else {
            if (xNode) {
                return new RectHV(parentPoint.x(), rect.ymin(), rect.xmax(), rect.ymax());
            } else {
                return new RectHV(rect.xmin(), parentPoint.y(), rect.xmax(), rect.ymax());
            }
        }
    }

    public boolean contains(Point2D p) {
        notNull(p);
        return find(root, p);
    }

    private boolean find(Node n, Point2D point) {
        if (n == null) {
            return false;
        }
        int cmp = n.compareTo(point);
        if (cmp < 0) {
            return find(n.leftChild, point);
        } else {
            return cmp <= 0 || find(n.rightChild, point);
        }
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
        StdDraw.setPenRadius(RADIUS);
        StdDraw.point(n.point.x(), n.point.y());
        if (n.xNode) {
            StdDraw.setPenRadius();
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
            StdDraw.setPenRadius();
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

        if (root == null) {
            return Collections.emptyList();
        }

        List<Point2D> inRange = new LinkedList<>();
        if (rect.contains(root.point)) {
            inRange.add(root.point);
        }

        range(inRange, root.leftChild, rect);
        range(inRange, root.rightChild, rect);

        return inRange;
    }

    private void range(List<Point2D> inRange, Node node, RectHV rect) {
        if (node == null || !node.rect.intersects(rect)) {
            return;
        }

        if (rect.contains(node.point)) {
            inRange.add(node.point);
        }

        range(inRange, node.leftChild, rect);
        range(inRange, node.rightChild, rect);
    }

    public Point2D nearest(Point2D p) {
        notNull(p);
        return nearest(p, root, null);
    }

    private Point2D nearest(Point2D p, Node node, Point2D nearest) {
        double nearestDistance = (nearest == null) ? Double.MAX_VALUE : nearest.distanceSquaredTo(p);
        if (node == null || nearestDistance < node.rect.distanceSquaredTo(p)) {
            return nearest;
        }

        if (node.point.distanceSquaredTo(p) < nearestDistance) {
            nearest = node.point;
        }

        int cmp = root.point.compareTo(p);

        if (cmp == 0) {
            return nearest;
        } else if (cmp < 0) {
            nearest = nearest(p, node.leftChild, nearest);
            return nearest(p, node.rightChild, nearest);
        } else {
            nearest = nearest(p, node.rightChild, nearest);
            return nearest(p, node.leftChild, nearest);
        }
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
        RectHV rect;

        public Node(boolean xNode, Point2D point, RectHV rect) {
            this.xNode = xNode;
            this.point = point;
            this.rect = rect;
        }

        public int compareTo(Point2D o) {
            return xNode ? compareByTwo(o.x(), point.x(), o.y(), point.y()) : compareByTwo(o.y(), point.y(), o.x(),
                    point.x());
        }

        private int compareByTwo(double firstO, double firstT, double secondO, double secondT) {
            int compareFirst = Double.compare(firstO, firstT);
            if (compareFirst == 0) {
                return Double.compare(secondO, secondT);
            } else {
                return compareFirst;
            }
        }
    }
}
