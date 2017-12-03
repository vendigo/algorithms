package coursera.alg1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean;
    private double stdev;
    private double lo;
    private double hi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] results = new double[trials];
        for (int i = 0; i < trials; i++) {
            results[i] = singleExperiment(n);
        }

        mean = StdStats.mean(results);
        stdev = StdStats.stddev(results);
        double p = (1.96 * stdev) / Math.sqrt(trials);
        lo = mean - p;
        hi = mean + p;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stdev;
    }

    public double confidenceLo() {
        return lo;
    }

    public double confidenceHi() {
        return hi;
    }

    private double singleExperiment(int n) {
        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            perc.open(row, col);
        }
        return (double) perc.numberOfOpenSites() / (n * n);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, t);
        System.out.println("mean = " + percStats.mean());
        System.out.println("stddev = " + percStats.stddev());
        System.out.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}
