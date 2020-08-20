// DepthFirstSearch.java
package L8_Graphs;


import L2_LinkedList.SinglyLinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DepthFirstSearch {
    public static final class Course {
        private String name;
        private VertexColor color;

        public Course(String name) {
            checkNotNull(name, "name must not be null");

            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Course that = (Course) o;

            return name.equals(that.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static SinglyLinkedList<String> planCourses(
            Graph<Course, ?> graph) {
        checkNotNull(graph, "graph must not be null");

        // Initialize all vertices in the graph
        for (Graph<Course, ?>.Vertex vertex : graph.getVertices()) {
            vertex.getData().color = VertexColor.WHITE;
        }

        // Create list to hold planned courses
        SinglyLinkedList<String> plannedCourses =
                new SinglyLinkedList<String>();

        // Perform multiple depth first searches each starting at a different
        // vertex to ensure courses with no prerequisites (i.e. disconnected
        // parts of the graph) are included in the results
        for (Graph<Course, ?>.Vertex vertex : graph.getVertices()) {
            if (vertex.getData().color == VertexColor.WHITE) {
                planCoursesRecursive(vertex, plannedCourses);
            }
        }

        return plannedCourses;
    }

    private static void planCoursesRecursive(
            Graph<Course, ?>.Vertex vertex,
            SinglyLinkedList<String> plannedCourses) {
        // Color the vertex gray
        vertex.getData().color = VertexColor.GRAY;

        // Recursively traverse each adjacent white vertex
        for (Graph<Course, ?>.Edge edge : vertex.getEdgesIncidentFrom()) {
            Graph<Course, ?>.Vertex adjacentVertex = edge.getTo();

            if (adjacentVertex.getData().color == VertexColor.WHITE) {
                planCoursesRecursive(adjacentVertex, plannedCourses);
            }
        }

        // Color the vertex black and add it to the front of the list
        plannedCourses.insertHead(vertex.getData().name);
    }
}
