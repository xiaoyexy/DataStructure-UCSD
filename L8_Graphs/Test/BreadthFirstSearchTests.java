// BreadthFirstSearchTests.java
package L8_Graphs.Test;

import L8_Graphs.BreadthFirstSearch;
import L8_Graphs.Graph;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BreadthFirstSearchTests {
    private Graph<BreadthFirstSearch.Server, Integer> graph;

    @BeforeMethod
    public void beforeMethod() {
        // Build network graph with these nodes and connections:
        //      node1 <-> node2
        //      node1 <-> node3
        //      node2 <-> node3
        //      node2 <-> node4
        //      node3 <-> node5
        //      node4 <-> node5
        //      node5 <-> node6
        graph = new Graph<BreadthFirstSearch.Server, Integer>();

        // nodes
        BreadthFirstSearch.Server node1 =
                new BreadthFirstSearch.Server("node1");
        BreadthFirstSearch.Server node2 =
                new BreadthFirstSearch.Server("node2");
        BreadthFirstSearch.Server node3 =
                new BreadthFirstSearch.Server("node3");
        BreadthFirstSearch.Server node4 =
                new BreadthFirstSearch.Server("node4");
        BreadthFirstSearch.Server node5 =
                new BreadthFirstSearch.Server("node5");
        BreadthFirstSearch.Server node6 =
                new BreadthFirstSearch.Server("node6");
        graph.insertVertex(node1);
        graph.insertVertex(node2);
        graph.insertVertex(node3);
        graph.insertVertex(node4);
        graph.insertVertex(node5);
        graph.insertVertex(node6);

        // node1 <-> node2
        graph.insertEdge(node1, node2, 0);
        graph.insertEdge(node2, node1, 0);

        // node1 <-> node3
        graph.insertEdge(node1, node3, 0);
        graph.insertEdge(node3, node1, 0);

        // node2 <-> node3
        graph.insertEdge(node2, node3, 0);
        graph.insertEdge(node3, node2, 0);

        // node2 <-> node4
        graph.insertEdge(node2, node4, 0);
        graph.insertEdge(node4, node2, 0);

        // node3 <-> node5
        graph.insertEdge(node3, node5, 0);
        graph.insertEdge(node5, node3, 0);

        // node4 <-> node5
        graph.insertEdge(node4, node5, 0);
        graph.insertEdge(node5, node4, 0);

        // node5 <-> node6
        graph.insertEdge(node5, node6, 0);
        graph.insertEdge(node6, node5, 0);
    }

    @Test
    public void testNetworkHops() {
        BreadthFirstSearch.countNetworkHops(graph, "node1");

        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node1"))
                .getData().getHops(), 0);
        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node2"))
                .getData().getHops(), 1);
        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node3"))
                .getData().getHops(), 1);
        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node4"))
                .getData().getHops(), 2);
        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node5"))
                .getData().getHops(), 2);
        assertEquals(graph.getVertex(new BreadthFirstSearch.Server("node6"))
                .getData().getHops(), 3);
    }
}
