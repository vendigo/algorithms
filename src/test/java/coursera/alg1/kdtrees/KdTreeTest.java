package coursera.alg1.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class KdTreeTest {

    @Test
    public void find() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(0.1, 0.3));
        kdTree.insert(new Point2D(0.2, 0.2));
        kdTree.insert(new Point2D(0.4, 0.5));
        assertThat(kdTree.contains(new Point2D(0.1, 0.3)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(0, 0)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(0.4, 0.5)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(0.2, 0.3)), equalTo(false));
        assertThat(kdTree.contains(new Point2D(0.4, 0.4)), equalTo(false));
        assertThat(kdTree.contains(new Point2D(0.3, 0.1)), equalTo(false));
    }

    @Test
    public void find2() {
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.5, 0.6);
        Point2D p2 = new Point2D(0.3, 0.3);
        Point2D p3 = new Point2D(0.7, 0.2);
        Point2D p4 = new Point2D(0.8, 0.1);
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);
        assertThat(kdTree.contains(p2), equalTo(true));
    }

    @Test
    public void range() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(0.1, 0.3));
        kdTree.insert(new Point2D(0.2, 0.2));
        kdTree.insert(new Point2D(0.4, 0.5));

        RectHV rect = new RectHV(0, 0.1, 0.3, 0.4);
        assertThat(kdTree.range(rect), hasItems(new Point2D(0.1, 0.3), new Point2D(0.2, 0.2)));
        assertThat((Collection<Point2D>) kdTree.range(rect), hasSize(2));
    }

    @Test
    public void range2() {
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.5, 0.6);
        Point2D p2 = new Point2D(0.3, 0.3);
        Point2D p3 = new Point2D(0.7, 0.2);
        Point2D p4 = new Point2D(0.8, 0.1);
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);

        RectHV rect = new RectHV(0.2, 0.1, 0.75, 0.6);
        assertThat(kdTree.range(rect), hasItems(p1, p2, p3));
        assertThat((Collection<Point2D>) kdTree.range(rect), hasSize(3));
    }

    @Test
    public void nearest() {
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.5, 0.6);
        Point2D p2 = new Point2D(0.3, 0.3);
        Point2D p3 = new Point2D(0.7, 0.2);
        Point2D p4 = new Point2D(0.8, 0.1);
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);

        assertThat(kdTree.nearest(new Point2D(0.8, 0.3)), equalTo(p3));
        assertThat(kdTree.nearest(new Point2D(0.3, 0.8)), equalTo(p1));
        assertThat(kdTree.nearest(new Point2D(0.3, 0.4)), equalTo(p2));
    }
}
