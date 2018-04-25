package coursera.alg2.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int R = 8;
    private static final int NUM_CHAR_VALUES = 1 << 8;

    public static void encode() {
        char[] chars = initArray();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(R);
            int index = indexOf(chars, c);
            BinaryStdOut.write(index, R);
            moveToFront(chars, index, c);
        }
        BinaryStdOut.close();
    }

    private static int indexOf(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static char[] initArray() {
        char[] result = new char[NUM_CHAR_VALUES];
        int i = 0;
        for (char c = 0x00; c <= 0xff; c++) {
            result[i++] = c;
        }
        return result;
    }

    public static void decode() {
        char[] chars = initArray();

        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readInt(R);
            char elem = chars[i];
            moveToFront(chars, i, elem);
            BinaryStdOut.write(elem, R);
        }
        BinaryStdOut.close();
    }

    private static void moveToFront(char[] chars, int i, char elem) {
        if (i > 0) {
            System.arraycopy(chars, 0, chars, 1, i);
            chars[0] = elem;
        }
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else {
            decode();
        }
    }
}
