package hackerrank.algo.strings;

import java.util.LinkedList;
import java.util.Scanner;

import static java.util.stream.Collectors.toCollection;

public class SuperReducedStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = superReducedString(s);
        System.out.println(result);
    }

    static String superReducedString(String s) {
        LinkedList<Character> chars = s.chars()
                .mapToObj(i -> ((char) i))
                .collect(toCollection(LinkedList::new));

        boolean changedInLastIteration = true;

        while (!chars.isEmpty() && changedInLastIteration) {
            changedInLastIteration = false;
            for (int i = 0; i < chars.size() - 1; i++) {
                if (chars.get(i).equals(chars.get(i+1))) {
                    chars.remove(i);
                    chars.remove(i);
                    changedInLastIteration = true;
                }
            }
        }

        if (chars.isEmpty()) {
            return "Empty String";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Character c : chars) {
                builder.append(c);
            }
            return builder.toString();
        }
    }
}
