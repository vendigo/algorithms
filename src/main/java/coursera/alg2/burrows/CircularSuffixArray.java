package coursera.alg2.burrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircularSuffixArray {

    private final String s;
    private final Map<Integer, Integer> indices;

    public CircularSuffixArray(String s) {
        this.s = notNull(s);
        this.indices = countIndices(s);
    }

    private Map<Integer, Integer> countIndices(final String s) {
        List<RefString> suffixes = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            suffixes.add(new RefString(i));
        }
        Collections.sort(suffixes);
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < suffixes.size(); i++) {
            indices.put(i, suffixes.get(i).shift);
        }
        return indices;
    }

    public int length() {
        return s.length();
    }

    public int index(int i) {
        inRange(i);
        return indices.get(i);
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

    private class RefString implements Comparable<RefString> {

        private final int shift;

        RefString(final int shift) {
            this.shift = shift;
        }

        @Override
        public int compareTo(final RefString o) {
            int iT = shift;
            int iO = o.shift;

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
