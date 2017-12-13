package coursera.alg2.wordnet;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SAPTest {

    @Test
    public void case0() throws Exception {
        Digraph graph = new Digraph(6);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 0);

        SAP sap = new SAP(graph);
        assertThat(sap.ancestor(1, 5), equalTo(0));
        assertThat(sap.ancestor(5, 1), equalTo(0));
        assertThat(sap.ancestor(2, 4), equalTo(4));
        assertThat(sap.length(5, 1), equalTo(2));
        assertThat(sap.length(1, 5), equalTo(2));
        assertThat(sap.length(2, 4), equalTo(2));
    }
}
