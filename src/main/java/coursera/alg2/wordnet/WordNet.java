package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class WordNet {
    private static final String COMA = ",";
    private static final String SPACE = " ";
    private Map<String, Set<Integer>> nounsToSyns;
    private Map<Integer, Set<String>> synsToNouns;
    private Map<Integer, Set<Integer>> hypernyms;
    private Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        notNull(synsetsFile);
        notNull(hypernymsFile);

        readSynsets(synsetsFile);
        readHypernyms(hypernymsFile);
        createGraph();
        isRootedDAG(graph);
    }

    private void isRootedDAG(Digraph graph) {
        Topological topological = new Topological(graph);
        if (topological.hasOrder()) {
            if (graph.V() > 1) {
                Iterator<Integer> iterator = topological.order().iterator();
                iterator.next();
                Integer second = iterator.next();
                if (graph.outdegree(second) == 0) {
                    throw new IllegalArgumentException("DAG is not single rooted");
                }
            }
        } else {
            throw new IllegalArgumentException("Not a DAG");
        }
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet("D:\\learning\\Coursera\\alg2\\wordnet\\wordnet-testing\\synsets8.txt",
                "D:\\learning\\Coursera\\alg2\\wordnet\\wordnet-testing\\hypernyms8ManyAncestors.txt");
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounsToSyns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        notNull(word);
        return nounsToSyns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        notNull(nounA);
        notNull(nounB);
        assertTrue(isNoun(nounA));
        assertTrue(isNoun(nounB));


        return 0;
    }

    // a synset (second field of nounsToSyns.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        notNull(nounA);
        notNull(nounB);
        assertTrue(isNoun(nounA));
        assertTrue(isNoun(nounB));

        return null;
    }

    private void readSynsets(String fileName) {
        nounsToSyns = new HashMap<>();
        synsToNouns = new HashMap<>();
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String[] elems = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(elems[0]);
            Set<String> nouns = Stream.of(elems[1].split(SPACE)).collect(toSet());
            synsToNouns.put(synsetId, nouns);
            for (String noun : nouns) {
                if (!nounsToSyns.containsKey(noun)) {
                    Set<Integer> ids = new HashSet<>();
                    ids.add(synsetId);
                    nounsToSyns.put(noun, ids);
                } else {
                    nounsToSyns.get(noun).add(synsetId);
                }
            }
        }
    }

    private void readHypernyms(String fileName) {
        hypernyms = new HashMap<>();
        In in = new In(fileName);

        while (in.hasNextLine()) {
            String[] parts = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(parts[0]);
            Set<Integer> links = new HashSet<>(parts.length - 1);
            for (int i = 1; i < parts.length; i++) {
                links.add(Integer.parseInt(parts[i]));
            }
            hypernyms.put(synsetId, links);
        }
    }

    private void createGraph() {
        graph = new Digraph(synsToNouns.size());
        hypernyms.forEach((id, hypers) -> {
            for (Integer hyper : hypers) {
                graph.addEdge(id, hyper);
            }
        });
    }

    private void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private void assertTrue(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Condition is not met");
        }
    }
}
