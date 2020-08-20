// Graph.java
package L8_Graphs;

import L5_HashTables.HashSet;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

// Using google guava preconditions library:
// https://github.com/google/guava/wiki/PreconditionsExplained

/**
 * Adjacency list representation of a graph.
 * @param <V> Data type stored in each vertex.
 * @param <E> Data type stored in each edge.
 */
public class Graph<V, E> {
    public class Vertex {
        private V data;
        private HashSet<Edge> edgesIncidentFrom = new HashSet<Edge>();
        private HashSet<Edge> edgesIncidentTo = new HashSet<Edge>();

        private Vertex(V data) {
            this.data = data;
        }

        public V getData() {
            return data;
        }

        public Iterable<Edge> getEdgesIncidentFrom() {
            return new Iterable<Edge>() {
                public Iterator<Edge> iterator() {
                    return edgesIncidentFrom.iterator();
                }
            };
        }

        public Iterable<Edge> getEdgesIncidentTo() {
            return new Iterable<Edge>() {
                public Iterator<Edge> iterator() {
                    return edgesIncidentTo.iterator();
                }
            };
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            return data.equals(vertex.data);
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }

    public class Edge {
        private Vertex from;
        private Vertex to;
        private E data;

        private Edge(Vertex from, Vertex to) {
            this(from, to, null);
        }

        private Edge(Vertex from, Vertex to, E data) {
            this.from = from;
            this.to = to;
            this.data = data;
        }

        public Vertex getFrom() {
            return from;
        }

        public Vertex getTo() {
            return to;
        }

        public E getData() {
            return data;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            return from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            return result;
        }
    }

    private HashSet<Vertex> vertices = new HashSet<Vertex>();

    private HashSet<Edge> edges = new HashSet<Edge>();

    // ============================ Vertex methods ============================
    public boolean containsVertex(V data) {
        return vertices.isMember(new Vertex(data));
    }

    public int getNumVertices() {
        return vertices.getSize();
    }

    public Iterable<Vertex> getVertices() {
        return new Iterable<Vertex>() {
            public Iterator<Vertex> iterator() {
                return vertices.iterator();
            }
        };
    }

    public Vertex getVertex(V data) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkVertexPreconditions(data);
        checkState(containsVertex(data), "Vertex does not exist");

        return vertices.getMember(new Vertex(data));
    }

    public void insertVertex(V data) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkVertexPreconditions(data);
        checkState(!containsVertex(data), "Vertex already exists");

        vertices.insert(new Vertex(data));
    }

    public V removeVertex(V data) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkVertexPreconditions(data);
        checkState(containsVertex(data), "Vertex does not exist");
        Vertex vertex = vertices.getMember(new Vertex(data));
        checkState(vertex.edgesIncidentFrom.isEmpty(),
                "Vertex has edges incident from it");
        checkState(vertex.edgesIncidentTo.isEmpty(),
                "Vertex has edges incident to it");

        return vertices.remove(new Vertex(data)).data;
    }

    private void checkVertexPreconditions(V data) throws
            NullPointerException {
        checkNotNull(data, "data must not be null");
    }

    // ============================= Edge methods =============================
    public boolean containsEdge(V from, V to) {
        return from != null
                && to != null
                && containsVertex(from)
                && containsVertex(to)
                && edges.isMember(new Edge(getVertex(from), getVertex(to)));
    }

    public int getNumEdges() {
        return edges.getSize();
    }

    public Iterable<Edge> getEdges() {
        return new Iterable<Edge>() {
            public Iterator<Edge> iterator() {
                return edges.iterator();
            }
        };
    }

    public Edge getEdge(V from, V to) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkEdgePreconditions(from, to);
        checkState(containsEdge(from, to), "Edge does not exist");

        return edges.getMember(new Edge(getVertex(from), getVertex(to)));
    }

    public void insertEdge(V from, V to, E data) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkEdgePreconditions(from, to);
        checkState(!containsEdge(from, to), "Edge already exists");

        // Add to graph's edge set
        Vertex fromVertex = getVertex(from);
        Vertex toVertex = getVertex(to);
        Edge edge = new Edge(fromVertex, toVertex, data);
        edges.insert(edge);

        // Add to "from" vertex's incidence set
        fromVertex.edgesIncidentFrom.insert(edge);

        // Add to "to" vertex's incidence set
        toVertex.edgesIncidentTo.insert(edge);
    }

    public E removeEdge(V from, V to) throws
            NullPointerException,
            IllegalStateException {
        // Preconditions
        checkEdgePreconditions(from, to);
        checkState(containsEdge(from, to), "Edge does not exist");

        // Remove edge from graph's edge set
        Vertex fromVertex = getVertex(from);
        Vertex toVertex = getVertex(to);
        Edge edge = new Edge(fromVertex, toVertex);
        E data = edges.remove(edge).data;

        // Remove edge from "from" vertex's incidence set
        fromVertex.edgesIncidentFrom.remove(edge);

        // Remove edge from "to" vertex's incidence set
        toVertex.edgesIncidentTo.remove(edge);

        return data;
    }

    private void checkEdgePreconditions(V from, V to) throws
            NullPointerException,
            IllegalStateException {
        checkNotNull(from, "from must not be null");
        checkNotNull(to, "to must not be null");
        checkState(containsVertex(from), "from vertex does not exist");
        checkState(containsVertex(to), "to vertex does not exist");
    }
}
