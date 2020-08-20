// MinimumSpanningTree.java
package L9_GraphAlgorithms;

import L8_Graphs.VertexColor;
import L8_Graphs.Graph;

import static com.google.common.base.Preconditions.*;

public final class MinimumSpanningTree {
    /**
     * A facility that produces or distributes water.
     */
    public static final class WaterFacility {
        private String name;
        private WaterFacility parent;
        private VertexColor color;
        private double costToConnectToParent;

        public WaterFacility(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public WaterFacility getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WaterFacility that = (WaterFacility) o;

            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    /**
     * A pipeline connecting two water facilities.
     */
    public static final class Pipeline {
        private double cost;

        public Pipeline(double cost) {
            this.cost = cost;
        }
    }

    /**
     * Generates a minimum spanning tree connecting all water facilities.
     * The "parent" field in each facility is set to represent the tree.
     */
    public static void computeOptimumDistributionSystem(
            Graph<WaterFacility, Pipeline> graph,
            WaterFacility start) {
        checkNotNull(graph, "graph must not be null");
        checkNotNull(start, "start must not be null");
        checkArgument(graph.containsVertex(start), "start not found in graph");

        // Initialize all of the vertices in the graph
        for (Graph<WaterFacility, Pipeline>.Vertex vertex
                : graph.getVertices()) {
            WaterFacility facility = vertex.getData();
            facility.color = VertexColor.WHITE;
            facility.parent = null;
            facility.costToConnectToParent =
                    facility.equals(start) ? 0 : Double.MAX_VALUE;
        }

        // Use Prim's algorithm to compute a minimum spanning tree
        for (int i = 0; i < graph.getNumVertices(); ++i) {
            // Select the white facility with the smallest cost to connect
            // to its parent
            Graph<WaterFacility, Pipeline>.Vertex selectedVertex = null;
            WaterFacility selectedFacility = null;
            double minCost = Double.MAX_VALUE;
            for (Graph<WaterFacility, Pipeline>.Vertex vertex
                    : graph.getVertices()) {
                if (vertex.getData().color == VertexColor.WHITE
                        && vertex.getData().costToConnectToParent < minCost) {
                    selectedVertex = vertex;
                    selectedFacility = vertex.getData();
                    minCost = vertex.getData().costToConnectToParent;
                }
            }

            // If no facility found, graph has disconnected components
            checkState(selectedFacility != null,
                    "graph has disconnected components");

            // Color the selected facility black
            selectedFacility.color = VertexColor.BLACK;

            // Traverse each pipeline that leaves the selected facility
            for (Graph<WaterFacility, Pipeline>.Edge edge
                    : selectedVertex.getEdgesIncidentFrom()) {
                // If adjacent facility is white and it would be cheaper to
                // connect to that facility with this pipeline
                Pipeline pipeline = edge.getData();
                WaterFacility adjacentFacility = edge.getTo().getData();
                if (adjacentFacility.color == VertexColor.WHITE
                        && pipeline.cost
                                < adjacentFacility.costToConnectToParent) {
                    // Use this pipeline (i.e. set adjacent facility's parent
                    // to the selected facility)
                    adjacentFacility.parent = selectedFacility;
                    adjacentFacility.costToConnectToParent = pipeline.cost;
                }
            }
        }
    }
}
