package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph graph) {
        notNull(graph);
        this.graph = graph;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateV(v);
        validateV(w);

        return 0;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateV(v);
        validateV(w);

        return 0;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }

    private void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private void validateV(int v) {
        if (v < 0 || v > graph.V() - 1) {
            throw new IllegalArgumentException("v is out of range");
        }
    }
}
