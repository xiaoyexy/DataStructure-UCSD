// ShortestPaths.java
package cse41321.algorithms.graph;

import cse41321.containers.Graph;

import static com.google.common.base.Preconditions.*;

public final class ShortestPaths {
    public static final class City {
        private String name;
        private City parent;
        private VertexColor color;
        private double shortestPathCost;

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public City getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            City that = (City) o;

            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static final class Connection {
        private double cost;

        public Connection(double cost) {
            this.cost = cost;
        }
    }

    /**
     * Generates shortest paths tree from start to all other cities.
     * The "parent" field in each city is set to represent the tree.
     */
    public static void computeShortestPaths(
            Graph<City, Connection> graph,
            City start) {
        checkNotNull(graph, "graph must not be null");
        checkNotNull(start, "start must not be null");
        checkArgument(graph.containsVertex(start), "start not found in graph");

        // Initialize all of the vertices in the graph
        for (Graph<City, Connection>.Vertex vertex
                : graph.getVertices()) {
            City city = vertex.getData();
            city.color = VertexColor.WHITE;
            city.parent = null;
            city.shortestPathCost = city.equals(start) ? 0 : Double.MAX_VALUE;
        }

        // Use Dijkstra's algorithm to compute a shortest paths tree
        for (int i = 0; i < graph.getNumVertices(); ++i) {
            // Select the white city with the smallest shortest path cost
            Graph<City, Connection>.Vertex selectedVertex = null;
            City selectedCity = null;
            double minCost = Double.MAX_VALUE;
            for (Graph<City, Connection>.Vertex vertex
                    : graph.getVertices()) {
                if (vertex.getData().color == VertexColor.WHITE
                        && vertex.getData().shortestPathCost < minCost) {
                    selectedVertex = vertex;
                    selectedCity = vertex.getData();
                    minCost = vertex.getData().shortestPathCost;
                }
            }

            // If no city found, graph has disconnected components
            checkState(selectedCity != null,
                    "graph has disconnected components");

            // Color the selected city black
            selectedCity.color = VertexColor.BLACK;

            // Traverse each connection that leaves the selected city
            for (Graph<City, Connection>.Edge edge
                    : selectedVertex.getEdgesIncidentFrom()) {
                // If adjacent city is white
                Connection connection = edge.getData();
                City adjacentCity = edge.getTo().getData();

                // Relax edge from selected city to adjacent city
                relax(selectedCity, adjacentCity, connection);
            }
        }
    }

    private static void relax(City from, City to, Connection connection) {
        // If path to "to" through "from" is shorter than previously
        // identified path
        if (to.shortestPathCost > from.shortestPathCost + connection.cost) {
            to.parent = from;
            to.shortestPathCost = from.shortestPathCost + connection.cost;
        }
    }
}
