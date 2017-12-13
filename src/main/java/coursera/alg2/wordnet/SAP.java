package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import static coursera.alg2.wordnet.Validator.notNull;

public class SAP {
    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph graph) {
        notNull(graph);
        this.graph = graph;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return singleVertice(v, w, false);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return singleVertice(v, w, true);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return multyVertices(v, w, false);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return multyVertices(v, w, true);
    }

    private int singleVertice(int v, int w, boolean returnAnc) {
        validateV(v);
        validateV(w);

        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(graph, w);

        return findSAP(returnAnc, vPaths, wPaths);
    }

    private int multyVertices(Iterable<Integer> v, Iterable<Integer> w, boolean returnAnc) {
        v.forEach(this::validateV);
        w.forEach(this::validateV);

        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(graph, w);

        return findSAP(returnAnc, vPaths, wPaths);
    }

    private int findSAP(boolean returnAnc, BreadthFirstDirectedPaths vPaths, BreadthFirstDirectedPaths wPaths) {
        int anc = -1;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < graph.V(); i++) {
            if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) {
                int len = vPaths.distTo(i) + wPaths.distTo(i);
                if (len < minLen) {
                    minLen = len;
                    anc = i;
                }
            }
        }

        if (returnAnc) {
            return anc;
        } else {
            return minLen;
        }
    }

    private void validateV(int v) {
        if (v < 0 || v > graph.V() - 1) {
            throw new IllegalArgumentException("v is out of range");
        }
    }
}
