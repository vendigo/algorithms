package coursera.alg2.burrows;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {

    private final String s;
    private final Integer[] indices;

    public CircularSuffixArray(String s) {
        this.s = notNull(s);
        this.indices = countIndices(s);
    }

    private Integer[] countIndices(final String s) {
        Integer[] shifts = new Integer[s.length()];
        for (int i = 0; i < s.length(); i++) {
            shifts[i] = i;
        }
        Arrays.sort(shifts, new RefComparator());
        return shifts;
    }

    public int length() {
        return s.length();
    }

    public int index(int i) {
        inRange(i);
        return indices[i];
    }

    private String notNull(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("S should be not null");
        }
        return s;
    }

    private void inRange(int i) {
        if (i < 0 || i > length() - 1) {
            throw new IllegalArgumentException("I should be between 0 and n-1");
        }
    }

    private class RefComparator implements Comparator<Integer> {

        @Override
        public int compare(final Integer tShift, final Integer oShift) {
            int iT = tShift;
            int iO = oShift;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(iT) > s.charAt(iO)) {
                    return 1;
                } else if (s.charAt(iT) < s.charAt(iO)) {
                    return -1;
                }
                iT = inc(iT);
                iO = inc(iO);
            }

            return 0;
        }

        private int inc(int i) {
            if (i < s.length() - 1) {
                return i + 1;
            } else {
                return 0;
            }
        }
    }
}
