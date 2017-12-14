package coursera.alg2.seam;

import coursera.TestFilePath;
import edu.princeton.cs.algs4.Picture;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class SeamCarverTest {
    private TestFilePath p = new TestFilePath("coursera/alg2/seam");

    @Test
    public void calculateEnergy() throws Exception {
        Picture picture = new Picture(p.file("5x6.png"));
        SeamCarver seamCarver = new SeamCarver(picture);
        assertThat(seamCarver.energy(0, 0), closeTo(1000, 0.1));
        assertThat(seamCarver.energy(0, 1), closeTo(1000, 0.1));
        assertThat(seamCarver.energy(1, 0), closeTo(1000, 0.1));
        assertThat(seamCarver.energy(1, 1), closeTo(300.07, 0.1));
        assertThat(seamCarver.energy(2, 2), closeTo(94.36, 0.1));
        assertThat(seamCarver.energy(2, 3), closeTo(312.36, 0.1));
        assertThat(seamCarver.energy(4, 5), closeTo(1000, 0.1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void seamInvalidLen() throws Exception {
        Picture picture = new Picture(p.file("5x6.png"));
        SeamCarver seamCarver = new SeamCarver(picture);
        seamCarver.removeHorizontalSeam(new int[]{2, 3, 4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void seamOutOfBounds() throws Exception {
        Picture picture = new Picture(p.file("5x6.png"));
        SeamCarver seamCarver = new SeamCarver(picture);
        seamCarver.removeHorizontalSeam(new int[]{3, 4, 5, 6, 5});
    }

    @Test(expected = IllegalArgumentException.class)
    public void seamGap() throws Exception {
        Picture picture = new Picture(p.file("5x6.png"));
        SeamCarver seamCarver = new SeamCarver(picture);
        seamCarver.removeHorizontalSeam(new int[]{1, 1, 2, 2, 4});
    }

    @Test
    public void drawEnergy() throws Exception {
        Picture picture = new Picture(p.file("chameleon.png"));
        SeamCarver seamCarver = new SeamCarver(picture);
        //SCUtility.toEnergyPicture(seamCarver).show();
        //TimeUnit.MINUTES.sleep(2);
    }
}
