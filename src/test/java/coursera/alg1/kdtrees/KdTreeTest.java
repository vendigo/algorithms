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
        kdTree.insert(new Point2D(1, 3));
        kdTree.insert(new Point2D(2, 2));
        kdTree.insert(new Point2D(4, -5));
        assertThat(kdTree.contains(new Point2D(1, 3)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(0, 0)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(4, -5)), equalTo(true));
        assertThat(kdTree.contains(new Point2D(2, 3)), equalTo(false));
        assertThat(kdTree.contains(new Point2D(4, -4)), equalTo(false));
        assertThat(kdTree.contains(new Point2D(3, -1)), equalTo(false));
    }

    @Test
    public void range() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
        kdTree.insert(new Point2D(1, 3));
        kdTree.insert(new Point2D(2, 2));
        kdTree.insert(new Point2D(4, -5));

        RectHV rect = new RectHV(0, 1, 3, 4);
        assertThat(kdTree.range(rect), hasItems(new Point2D(1, 3), new Point2D(2, 2)));
        assertThat((Collection<Point2D>) kdTree.range(rect), hasSize(2));
    }
}
