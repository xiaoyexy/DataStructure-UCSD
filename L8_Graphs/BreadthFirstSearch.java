// BreadthFirstSearch.java
package L8_Graphs;


import L4_Stacks_Queues_Sets.Queue;

import static com.google.common.base.Preconditions.*;

public final class BreadthFirstSearch {
    public static final class Server {
        private String name;
        private VertexColor color;
        private int hops;

        public Server(String name) {
            checkNotNull(name, "name must not be null");

            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getHops() {
            return hops;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Server that = (Server) o;

            return name.equals(that.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static void countNetworkHops(Graph<Server, ?> graph, String start) {
        checkNotNull(graph, "graph must not be null");
        checkNotNull(start, "start must not be null");
        checkArgument(graph.containsVertex(new Server(start)),
                "start not found in graph");

        // Initialize all vertices in the graph
        for (Graph<Server, ?>.Vertex vertex : graph.getVertices()) {
            Server server = vertex.getData();
            if (start.equals(server.name)) {
                // Initialize the start vertex
                server.color = VertexColor.GRAY;
                server.hops = 0;
            } else {
                // Initialize vertices other than the start vertex
                server.color = VertexColor.WHITE;
                server.hops = -1;
            }
        }

        // Initialize queue with the start vertex
        Queue<Graph<Server, ?>.Vertex> queue =
                new Queue<Graph<Server, ?>.Vertex>();
        queue.enqueue(graph.getVertex(new Server(start)));

        // Perform breadth-first search setting each reachable vertex's hops
        while (!queue.isEmpty()) {
            Graph<Server, ?>.Vertex vertex = queue.dequeue();

            // Look at all vertices adjacent to current vertex
            for (Graph<Server, ?>.Edge edge
                    : vertex.getEdgesIncidentFrom()) {
                // If adjacent vertex is white, color it gray, update hops,
                // and add to queue
                Graph<Server, ?>.Vertex adjacentVertex = edge.getTo();
                if (adjacentVertex.getData().color == VertexColor.WHITE) {
                    adjacentVertex.getData().color = VertexColor.GRAY;
                    adjacentVertex.getData().hops = vertex.getData().hops + 1;
                    queue.enqueue(adjacentVertex);
                }
            }

            vertex.getData().color = VertexColor.BLACK;
        }
    }
}
