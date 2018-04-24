package coursera.alg2.burrows;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    private static final int R = 8;
    private static final int NUM_CHAR_VALUES = 1 << 8;

    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray suffixes = new CircularSuffixArray(s);
        int first = 0;
        char[] t = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int shift = suffixes.index(i);
            t[i] = lastForShift(s, shift);
            if (shift == 0) {
                first = i;
            }
        }
        BinaryStdOut.write(first);
        for (char c : t) {
            BinaryStdOut.write(c, R);
        }
        BinaryStdOut.close();
    }

    private static char lastForShift(String s, int shift) {
        if (shift > 0) {
            return s.charAt(shift - 1);
        } else {
            return s.charAt(s.length() - 1);
        }
    }

    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        List<Integer>[] occurences = new List[NUM_CHAR_VALUES];
        int i = 0;

        //Read data and construct occurences
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(R);
            List<Integer> forChar = occurences[c];
            if (forChar == null) {
                occurences[c] = new LinkedList<>();
            }
            occurences[c].add(i++);
        }
        int size = i;

        //Construct next and firstRow
        int[] next = new int[size];
        char[] firstRow = new char[size];
        int j = 0;
        for (char c = 0x00; c <= 0xFF; c++) {
            List<Integer> forChar = occurences[c];
            if (forChar != null) {
                for (int charPos : forChar) {
                    next[j] = charPos;
                    firstRow[j++] = c;
                }
            }
        }

        //Construct original string using next and firstRow
        int current = first;
        for (int k = 0; k < size; k++) {
            char ch = firstRow[current];
            BinaryStdOut.write(ch, R);
            current = next[current];
        }

        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        } else {
            inverseTransform();
        }
    }
}
