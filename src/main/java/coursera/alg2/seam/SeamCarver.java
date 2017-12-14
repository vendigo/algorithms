package coursera.alg2.seam;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private double[][] energy;

    public SeamCarver(Picture picture) {
        notNull(picture);
        this.picture = new Picture(picture);
        this.width = picture.width();
        this.height = picture.height();
        calculateEnergy();
    }

    public Picture picture() {
        return picture;
    }


    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public double energy(int x, int y) {
        checkBounds(x, y);
        return energy[y][x];
    }

    public int[] findHorizontalSeam() {
        return null;
    }

    public int[] findVerticalSeam() {
        double[][] distTo = new double[height][width];

        for (int i = 0; i < width; i++) { // Init top row
            distTo[0][i] = 1000;
        }

        for (int y = 1; y < height; y++) { //Fill distTo
            for (int x = 0; x < width; x++) {
                distTo[y][x] = distTo[y - 1][minTop3Index(distTo[y - 1], x)] + energy[y][x];
            }
        }

        int minIndex = 0;
        for (int i = 0; i < width; i++) { //Find first element of the seam - min value in the last row
            if (distTo[height - 1][i] < distTo[height - 1][minIndex]) {
                minIndex = i;
            }
        }

        int[] seam = new int[height];
        seam[height - 1] = minIndex;
        int prevX = minIndex;

        for (int y = height - 2; y >= 0; y--) { //Build seam
            prevX = minTop3Index(distTo[y], prevX);
            seam[y] = prevX;
        }

        return seam;
    }

    public void removeHorizontalSeam(int[] seam) {
        validateSeam(seam, width, height);
    }

    public void removeVerticalSeam(int[] seam) {
        validateSeam(seam, height, width);
    }

    private int minTop3Index(double[] arr, int x) {
        if (width == 1) {
            return x;
        }

        if (x == 0) {
            return minArrIndex(arr, x, x + 1);
        }

        if (x == width - 1) {
            return minArrIndex(arr, x - 1, x);
        }

        return minArrIndex(arr, minArrIndex(arr, x - 1, x), x + 1);
    }

    private int minArrIndex(double[] arr, int i, int j) {
        return arr[i] <= arr[j] ? i : j;
    }

    private void calculateEnergy() {
        energy = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                energy[y][x] = cellEnergy(x, y);
            }
        }
    }

    private double cellEnergy(int x, int y) {
        if (isMarginal(x, y)) {
            return 1000;
        } else {
            return Math.sqrt(deltaX(x, y) + deltaY(x, y));
        }
    }

    private double deltaX(int x, int y) {
        double r = picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed();
        double g = picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen();
        double b = picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue();
        return r * r + g * g + b * b;
    }

    private double deltaY(int x, int y) {
        double r = picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed();
        double g = picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen();
        double b = picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue();
        return r * r + g * g + b * b;
    }

    private boolean isMarginal(int x, int y) {
        return x == 0 || x == width - 1 || y == 0 || y == height - 1;
    }

    private void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("(x, y) are out of bounds");
        }
    }

    private void validateSeam(int[] seam, int len, int bound) {
        notNull(seam);
        if (seam.length != len) {
            throw new IllegalArgumentException("Seam has invalid length");
        }

        int prev = seam[0];
        for (int v : seam) {
            if (v < 0 || v >= bound || Math.abs(v - prev) > 1) {
                throw new IllegalArgumentException("Invalid seam");
            }
        }
    }
}