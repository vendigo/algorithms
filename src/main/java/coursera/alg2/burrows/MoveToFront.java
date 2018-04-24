package coursera.alg2.burrows;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int R = 8;
    private static final int ZERO = 0;

    public static void encode() {
        List<Character> chars = init();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(R);
            int index = chars.indexOf(c);
            BinaryStdOut.write(index, R);
            if (index > ZERO) {
                chars.remove(index);
                chars.add(ZERO, c);
            }
        }
        BinaryStdOut.close();
    }

    private static List<Character> init() {
        List<Character> result = new LinkedList<>();
        for (char c = 0x00; c <= 0xff; c++) {
            result.add(c);
        }
        return result;
    }

    public static void decode() {
        List<Character> chars = init();

        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readInt(R);
            char ch = chars.remove(i);
            BinaryStdOut.write(ch, R);
            chars.add(ZERO, ch);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else {
            decode();
        }
    }
}
