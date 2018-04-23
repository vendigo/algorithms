package coursera.alg2.burrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    private static final int R = 8;

    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray suffixes = new CircularSuffixArray(s);
        int first = suffixes.index(0);
        char[] t = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int index = suffixes.index(i);
            t[index] = lastForShift(s, i);
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
        List<Character> lastColumn = new ArrayList<>();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(R);
            lastColumn.add(c);
        }
        List<Character> firstColumn = new ArrayList<>(lastColumn);
        Collections.sort(firstColumn);
        int[] next = constructNext(firstColumn, lastColumn);
        int current = first;
        for (int i = 0; i < firstColumn.size(); i++) {
            Character ch = firstColumn.get(current);
            BinaryStdOut.write(ch, R);
            current = next[current];
        }
        BinaryStdOut.close();
    }

    private static int[] constructNext(final List<Character> firstColumn, final List<Character> lastColumn) {
        Map<Character, List<Integer>> lastOccurences = occurences(lastColumn);
        Map<Character, Integer> occurences = new HashMap<>();
        int[] next = new int[lastColumn.size()];

        for (int i = 0; i < firstColumn.size(); i++) {
            Character ch = firstColumn.get(i);
            Integer occurence = occurences.getOrDefault(ch, 1);
            Integer nextValue = lastOccurences.get(ch).get(occurence);
            next[i] = nextValue;
            occurences.put(ch, occurence + 1);
        }

        return next;
    }

    private static Map<Character, List<Integer>> occurences(List<Character> column) {
        Map<Character, List<Integer>> occurences = new HashMap<>();

        for (int i = 0; i < column.size(); i++) {
            Character ch = column.get(i);
            List<Integer> forChar = occurences.getOrDefault(ch, new LinkedList<>());
            forChar.add(i);
            occurences.put(ch, forChar);
        }

        return occurences;
    }

    public static void main(String args[]) {

    }
}
