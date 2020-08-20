// GraphTests.java
package L8_Graphs.Test;

import L8_Graphs.Graph;
import L4_Stacks_Queues_Sets.Set;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GraphTests {
    private Graph<String, Integer> graph;

    @BeforeMethod
    public void beforeMethod() {
        graph = new Graph<String, Integer>();
    }

    // ============================ Vertex tests ============================
    @Test
    public void containsVertex_EmptyGraph() {
        assertFalse(graph.containsVertex("foo"));
    }

    @Test
    public void containsVertex_1VertexGraph() {
        graph.insertVertex("foo");

        assertTrue(graph.containsVertex("foo"));
        assertFalse(graph.containsVertex("bar"));
    }

    @Test
    public void containsVertex_2VertexGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        assertTrue(graph.containsVertex("foo"));
        assertTrue(graph.containsVertex("bar"));
        assertFalse(graph.containsVertex("baz"));
    }

    @Test
    public void getNumVertices_EmptyGraph() {
        assertEquals(graph.getNumVertices(), 0);
    }

    @Test
    public void getNumVertices_1VertexGraph() {
        graph.insertVertex("foo");

        assertEquals(graph.getNumVertices(), 1);
    }

    @Test
    public void getNumVertices_2VertexGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        assertEquals(graph.getNumVertices(), 2);
    }

    @Test
    public void getVertices_EmptyGraph() {
        Set<String> seen = new Set<String>();
        for (Graph<String, Integer>.Vertex vertex : graph.getVertices()) {
            seen.insert(vertex.getData());
        }

        assertEquals(seen.getSize(), 0);
    }

    @Test
    public void getVertices_1VertexGraph() {
        graph.insertVertex("foo");

        Set<String> seen = new Set<String>();
        for (Graph<String, Integer>.Vertex vertex : graph.getVertices()) {
            seen.insert(vertex.getData());
        }

        assertEquals(seen.getSize(), 1);
        assertTrue(seen.isMember("foo"));
    }

    @Test
    public void getVertices_2VertexGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        Set<String> seen = new Set<String>();
        for (Graph<String, Integer>.Vertex vertex : graph.getVertices()) {
            seen.insert(vertex.getData());
        }

        assertEquals(seen.getSize(), 2);
        assertTrue(seen.isMember("foo"));
        assertTrue(seen.isMember("bar"));
    }

    @Test
    public void getVertex_1VertexGraph() {
        graph.insertVertex("foo");

        Graph<String, Integer>.Vertex vertex = graph.getVertex("foo");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "foo");
        assertFalse(vertex.getEdgesIncidentFrom().iterator().hasNext());
        assertFalse(vertex.getEdgesIncidentTo().iterator().hasNext());
    }

    @Test
    public void getVertex_2VertexGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        Graph<String, Integer>.Vertex vertex = graph.getVertex("foo");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "foo");
        assertFalse(vertex.getEdgesIncidentFrom().iterator().hasNext());
        assertFalse(vertex.getEdgesIncidentTo().iterator().hasNext());

        vertex = graph.getVertex("bar");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "bar");
        assertFalse(vertex.getEdgesIncidentFrom().iterator().hasNext());
        assertFalse(vertex.getEdgesIncidentTo().iterator().hasNext());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getVertex_Null() {
        graph.getVertex(null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void getVertex_NonExistent() {
        graph.getVertex("foo");
    }

    @Test
    public void insertVertex_Once() {
        graph.insertVertex("foo");

        assertEquals(graph.getNumVertices(), 1);

        Graph<String, Integer>.Vertex vertex = graph.getVertex("foo");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "foo");
    }

    @Test
    public void insertVertex_Twice() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        assertEquals(graph.getNumVertices(), 2);

        Graph<String, Integer>.Vertex vertex = graph.getVertex("foo");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "foo");

        vertex = graph.getVertex("bar");
        assertNotNull(vertex);
        assertEquals(vertex.getData(), "bar");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void insertVertex_Null() {
        graph.insertVertex(null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertVertex_Duplicate() {
        graph.insertVertex("foo");
        graph.insertVertex("foo");
    }

    @Test(expectedExceptions = NullPointerException.class)
    void removeVertex_Null() {
        graph.removeVertex(null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void removeVertex_NonExistent() {
        graph.insertVertex("foo");

        graph.removeVertex("bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void removeVertex_EdgesIncidentFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 0);

        graph.removeVertex("foo");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void removeVertex_EdgesIncidentTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("bar", "foo", 0);

        graph.removeVertex("foo");
    }

    @Test
    void removeVertex_1VertexGraph() {
        String foo = "foo";
        graph.insertVertex(foo);

        String data = graph.removeVertex("foo");

        assertEquals(graph.getNumVertices(), 0);
        assertSame(data, foo);
    }

    @Test
    void removeVertex_2VertexGraph() {
        String foo = "foo";
        String bar = "bar";
        graph.insertVertex(foo);
        graph.insertVertex(bar);

        String data = graph.removeVertex("foo");
        assertEquals(graph.getNumVertices(), 1);
        assertSame(data, foo);

        data = graph.removeVertex("bar");
        assertEquals(graph.getNumVertices(), 0);
        assertSame(data, bar);
    }

    // ============================== Edge tests ==============================
    @Test
    public void containsEdge_NullFrom() {
        graph.insertVertex("bar");

        assertFalse(graph.containsEdge(null, "bar"));
    }

    @Test
    public void containsEdge_NullTo() {
        graph.insertVertex("foo");

        assertFalse(graph.containsEdge("foo", null));
    }

    @Test
    public void containsEdge_NonExistentFrom() {
        graph.insertVertex("bar");

        assertFalse(graph.containsEdge("foo", "bar"));
    }

    @Test
    public void containsEdge_NonExistentTo() {
        graph.insertVertex("foo");

        assertFalse(graph.containsEdge("foo", "bar"));
    }

    @Test
    public void containsEdge_NonExistentFromAndTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        assertFalse(graph.containsEdge("foo", "bar"));
    }

    @Test
    public void containsEdge_1EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 0);

        assertTrue(graph.containsEdge("foo", "bar"));
        assertFalse(graph.containsEdge("bar", "foo"));
    }

    @Test
    public void containsEdge_2EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 0);
        graph.insertEdge("bar", "foo", 0);

        assertTrue(graph.containsEdge("foo", "bar"));
        assertTrue(graph.containsEdge("bar", "foo"));
    }

    @Test
    public void getNumEdges_EmptyGraph() {
        assertEquals(graph.getNumEdges(), 0);
    }

    @Test
    public void getNumEdges_0EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        assertEquals(graph.getNumEdges(), 0);
    }

    @Test
    public void getNumEdges_1EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 0);

        assertEquals(graph.getNumEdges(), 1);
    }

    @Test
    public void getNumEdges_2EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 0);
        graph.insertEdge("bar", "foo", 0);

        assertEquals(graph.getNumEdges(), 2);
    }

    @Test
    public void getEdges_0EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");

        Set<Integer> seen = new Set<Integer>();
        for (Graph<String, Integer>.Edge edge : graph.getEdges()) {
            seen.insert(edge.getData());
        }

        assertEquals(seen.getSize(), 0);
    }

    @Test
    public void getEdges_1EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);

        Set<Integer> seen = new Set<Integer>();
        for (Graph<String, Integer>.Edge edge : graph.getEdges()) {
            seen.insert(edge.getData());
        }

        assertEquals(seen.getSize(), 1);
        assertTrue(seen.isMember(1));
    }

    @Test
    public void getEdges_2EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        Set<Integer> seen = new Set<Integer>();
        for (Graph<String, Integer>.Edge edge : graph.getEdges()) {
            seen.insert(edge.getData());
        }

        assertEquals(seen.getSize(), 2);
        assertTrue(seen.isMember(1));
        assertTrue(seen.isMember(2));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getEdge_NullFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        graph.getEdge(null, "bar");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getEdge_NullTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        graph.getEdge("foo", null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void getEdge_NonExistentFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        graph.getEdge("baz", "bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void getEdge_NonExistentTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        graph.getEdge("foo", "baz");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void getEdge_NonExistentFromAndTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        graph.getEdge("baz", "qux");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void insertEdge_NullFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge(null, "bar", 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void insertEdge_NullTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", null, 1);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertEdge_NonExistentFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("baz", "bar", 1);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertEdge_NonExistentTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "baz", 1);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertEdge_NonExistentFromAndTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("baz", "qux", 1);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertEdge_Duplicate() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("foo", "bar", 2);  // Data not part of equality test
    }

    @Test
    public void insertEdge_Once() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);

        assertEquals(graph.getNumEdges(), 1);

        Graph<String, Integer>.Edge edge = graph.getEdge("foo", "bar");
        assertNotNull(edge);
        assertEquals(edge.getData(), (Integer) 1);
        assertEquals(edge.getFrom().getData(), "foo");
        assertEquals(edge.getTo().getData(), "bar");
    }

    @Test
    public void insertEdge_Twice() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        assertEquals(graph.getNumEdges(), 2);

        Graph<String, Integer>.Edge edge = graph.getEdge("foo", "bar");
        assertNotNull(edge);
        assertEquals(edge.getData(), (Integer) 1);
        assertEquals(edge.getFrom().getData(), "foo");
        assertEquals(edge.getTo().getData(), "bar");

        edge = graph.getEdge("bar", "foo");
        assertNotNull(edge);
        assertEquals(edge.getData(), (Integer) 2);
        assertEquals(edge.getFrom().getData(), "bar");
        assertEquals(edge.getTo().getData(), "foo");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeEdge_NullFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.removeEdge(null, "bar");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeEdge_NullTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.removeEdge("foo", null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeEdge_NonExistentFrom() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.removeEdge("baz", "bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeEdge_NonExistentTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.removeEdge("foo", "baz");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeEdge_NonExistentFromAndTo() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.removeEdge("baz", "qux");
    }

    @Test
    public void removeEdge_1EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);

        assertTrue(graph.getVertex("foo").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("foo").getEdgesIncidentTo()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertTrue(graph.getVertex("bar").getEdgesIncidentTo()
                .iterator().hasNext());

        Integer data = graph.removeEdge("foo", "bar");
        assertEquals(graph.getNumEdges(), 0);
        assertEquals(data, (Integer) 1);
        assertFalse(graph.getVertex("foo").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("foo").getEdgesIncidentTo()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentTo()
                .iterator().hasNext());
    }

    @Test
    public void removeEdge_2EdgeGraph() {
        graph.insertVertex("foo");
        graph.insertVertex("bar");
        graph.insertEdge("foo", "bar", 1);
        graph.insertEdge("bar", "foo", 2);

        assertTrue(graph.getVertex("foo").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertTrue(graph.getVertex("foo").getEdgesIncidentTo()
                .iterator().hasNext());
        assertTrue(graph.getVertex("bar").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertTrue(graph.getVertex("bar").getEdgesIncidentTo()
                .iterator().hasNext());

        Integer data = graph.removeEdge("foo", "bar");
        assertEquals(graph.getNumEdges(), 1);
        assertEquals(data, (Integer) 1);
        assertFalse(graph.getVertex("foo").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertTrue(graph.getVertex("foo").getEdgesIncidentTo()
                .iterator().hasNext());
        assertTrue(graph.getVertex("bar").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentTo()
                .iterator().hasNext());

        data = graph.removeEdge("bar", "foo");
        assertEquals(graph.getNumEdges(), 0);
        assertEquals(data, (Integer) 2);
        assertFalse(graph.getVertex("foo").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("foo").getEdgesIncidentTo()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentFrom()
                .iterator().hasNext());
        assertFalse(graph.getVertex("bar").getEdgesIncidentTo()
                .iterator().hasNext());
    }
}
