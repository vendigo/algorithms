package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxValue = oneNounValue(nouns[0], nouns);
        String outcast = nouns[0];

        for (int i = 1; i < nouns.length; i++) {
            int value = oneNounValue(nouns[i], nouns);
            if (value > maxValue) {
                maxValue = value;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    private int oneNounValue(String current, String[] nouns) {
        int sum = 0;
        for (String noun : nouns) {
            if (!noun.equals(current)) {
                sum += wordNet.distance(current, noun);
            }
        }
        return sum;
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
