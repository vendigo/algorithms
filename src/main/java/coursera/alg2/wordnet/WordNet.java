package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.In;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class WordNet {
    private static final String COMA = ",";
    private static final String SPACE = " ";

    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        notNull(synsetsFile);
        notNull(hypernymsFile);

        List<Synset> synsets = readSynsets(synsetsFile);
        System.out.println(synsets);

        Map<Integer, Set<Integer>> hypernyms = readHypernyms(hypernymsFile);
        System.out.println("Hypernyms: " + hypernyms);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        notNull(word);

        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        notNull(nounA);
        notNull(nounB);

        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        notNull(nounA);
        notNull(nounB);

        return null;
    }

    private List<Synset> readSynsets(String fileName) {
        List<Synset> synsets = new ArrayList<>();
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String[] elems = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(elems[0]);
            Set<String> nouns = Stream.of(elems[1].split(SPACE)).collect(toSet());
            synsets.add(new Synset(synsetId, nouns));
        }
        return synsets;
    }

    private Map<Integer, Set<Integer>> readHypernyms(String fileName) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        In in = new In(fileName);

        while (in.hasNextLine()) {
            String[] parts = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(parts[0]);
            Set<Integer> links = new HashSet<>(parts.length - 1);
            for (int i = 1; i < parts.length; i++) {
                links.add(Integer.parseInt(parts[i]));
            }
            result.put(synsetId, links);
        }

        return result;
    }

    private void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet("D:\\learning\\Coursera\\alg2\\wordnet\\wordnet-testing\\synsets8.txt",
                "D:\\learning\\Coursera\\alg2\\wordnet\\wordnet-testing\\hypernyms8ManyAncestors.txt");
    }
}
