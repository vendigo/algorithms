package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private static final String COMA = ",";
    private static final String SPACE = " ";
    private Map<String, Set<Integer>> nounsToSyns;
    private Map<Integer, String> syns;
    private Digraph graph;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        notNull(synsetsFile);
        notNull(hypernymsFile);

        readSynsets(synsetsFile);
        readHypernyms(hypernymsFile);
        isRootedDAG();
        this.sap = new SAP(graph);
    }

    private void isRootedDAG() {
        DirectedCycle cycle = new DirectedCycle(graph);
        if (!cycle.hasCycle()) {
            if (graph.V() > 1) {
                boolean foundFirstRoot = false;

                for (int v = 0; v < graph.V(); v++) {
                    if (graph.outdegree(v) == 0) {
                        if (!foundFirstRoot) {
                            foundFirstRoot = true;
                        } else {
                            throw new IllegalArgumentException("DAG is not single rooted");
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Not a DAG - Has cycle");
        }
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

        return sap.length(nounsToSyns.get(nounA), nounsToSyns.get(nounB));
    }

    // a synset (second field of nounsToSyns.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        notNull(nounA);
        notNull(nounB);
        assertTrue(isNoun(nounA));
        assertTrue(isNoun(nounB));

        int synsetId = sap.ancestor(nounsToSyns.get(nounA), nounsToSyns.get(nounB));
        return syns.get(synsetId);
    }

    private void readSynsets(String fileName) {
        nounsToSyns = new HashMap<>();
        syns = new HashMap<>();
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String[] elems = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(elems[0]);
            Set<String> nouns = collectToSet(elems[1]);
            syns.put(synsetId, elems[1]);
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

    private Set<String> collectToSet(String elem) {
        Set<String> result = new HashSet<>();
        result.addAll(Arrays.asList(elem.split(SPACE)));
        return result;
    }

    private void readHypernyms(String fileName) {
        In in = new In(fileName);
        graph = new Digraph(syns.size());

        while (in.hasNextLine()) {
            String[] parts = in.readLine().split(COMA);
            int synsetId = Integer.parseInt(parts[0]);

            for (int i = 1; i < parts.length; i++) {
                graph.addEdge(synsetId, Integer.parseInt(parts[i]));
            }
        }
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
