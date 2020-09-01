package L8_Graphs.Homework8;

import L4_Stacks_Queues_Sets.Queue;
import L8_Graphs.Graph;
import L8_Graphs.VertexColor;
import com.google.common.primitives.Chars;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;


public class Homework8 {

    public static final class Room {
        private char name;
        private VertexColor color;

        public Room(char name) {
            checkNotNull(name, "name must not be null");
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Room that = (Room) o;
            return name == that.name;
        }

        @Override
        public int hashCode() {
            return Chars.hashCode(name);
        }

    }

    public static void colorVertex(Graph<Room, ?> graph, char start) {
        checkNotNull(graph, "graph must not be null");
        checkNotNull(start, "start must not be null");
        checkState(graph.containsVertex(new Room(start)), "start not found in graph");

        // Initialize all vertices in the graph
        for (Graph<Room, ?>.Vertex vertex : graph.getVertices()) {
            Room room = vertex.getData();
            if (room.name == start) {
                // Initialize the start vertex
                room.color = VertexColor.GRAY;
            } else {
                // Initialize vertices other than the start vertex
                room.color = VertexColor.WHITE;
            }
        }

        // Initialize queue with the start vertex
        Queue<Graph<Room, ?>.Vertex> queue = new Queue<Graph<Room, ?>.Vertex>();
        queue.enqueue(graph.getVertex(new Room(start)));

        // Perform breadth-first search setting each reachable vertex's colors
        while (!queue.isEmpty()) {
            Graph<Room, ?>.Vertex vertex = queue.dequeue();

            // Look at all vertices adjacent to current vertex
            for (Graph<Room, ?>.Edge edge : vertex.getEdgesIncidentFrom()) {
                // If adjacent vertex is white, color it gray, update hops, and add to queue
                Graph<Room, ?>.Vertex adjacentVertex = edge.getTo();
                if (adjacentVertex.getData().color == VertexColor.WHITE) {
                    adjacentVertex.getData().color = VertexColor.GRAY;
                    queue.enqueue(adjacentVertex);
                }
            }
            vertex.getData().color = VertexColor.BLACK;
        }
    }


    public static boolean isExitReachable(Graph<Room, ?> maze, char entrance, char exit) {
        checkNotNull(maze, "graph must not be null");
        checkNotNull(entrance, "entrance must not be null");
        checkNotNull(exit, "exit must not be null");

        checkState(maze.containsVertex(new Homework8.Room(entrance)), "entrance not found in graph");
        checkState(maze.containsVertex(new Homework8.Room(exit)), "exit not found in graph");

        colorVertex(maze, entrance);
        for (Graph<Room, ?>.Vertex vertex : maze.getVertices()) {
            if (vertex.getData().name == exit) {
                if (vertex.getData().color != VertexColor.WHITE) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        // nodes
        Homework8.Room nodeA = new Homework8.Room('A');
        Homework8.Room nodeB = new Homework8.Room('B');
        Homework8.Room nodeC = new Homework8.Room('C');
        Homework8.Room nodeD = new Homework8.Room('D');
        Homework8.Room nodeE = new Homework8.Room('E');
        Homework8.Room nodeF = new Homework8.Room('F');
        Homework8.Room nodeG = new Homework8.Room('G');


        // maze1
        Graph maze1 = new Graph<Homework8.Room, Integer>();

        maze1.insertVertex(nodeA);
        maze1.insertVertex(nodeB);
        maze1.insertVertex(nodeC);
        maze1.insertVertex(nodeD);
        maze1.insertVertex(nodeE);
        maze1.insertVertex(nodeF);
        maze1.insertVertex(nodeG);

        // nodeA <-> nodeD
        maze1.insertEdge(nodeA, nodeD, 0);
        maze1.insertEdge(nodeD, nodeA, 0);

        // nodeA <-> nodeC
        maze1.insertEdge(nodeA, nodeC, 0);
        maze1.insertEdge(nodeC, nodeA, 0);

        // nodeB <-> nodeD
        maze1.insertEdge(nodeB, nodeD, 0);
        maze1.insertEdge(nodeD, nodeB, 0);

        // nodeC <-> nodeF
        maze1.insertEdge(nodeC, nodeF, 0);
        maze1.insertEdge(nodeF, nodeC, 0);

        // nodeD <-> nodeE
        maze1.insertEdge(nodeD, nodeE, 0);
        maze1.insertEdge(nodeE, nodeD, 0);

        // nodeD <-> nodeG
        maze1.insertEdge(nodeD, nodeG, 0);
        maze1.insertEdge(nodeG, nodeD, 0);

        // nodeE <-> nodeG
        maze1.insertEdge(nodeE, nodeG, 0);
        maze1.insertEdge(nodeG, nodeE, 0);

        // nodeF <-> nodeG
        maze1.insertEdge(nodeF, nodeG, 0);
        maze1.insertEdge(nodeG, nodeF, 0);


        // maze2
        Graph maze2 = new Graph<Homework8.Room, Integer>();

        maze2.insertVertex(nodeA);
        maze2.insertVertex(nodeB);
        maze2.insertVertex(nodeC);
        maze2.insertVertex(nodeD);
        maze2.insertVertex(nodeE);
        maze2.insertVertex(nodeF);
        maze2.insertVertex(nodeG);

        // nodeA <-> nodeD
        maze2.insertEdge(nodeA, nodeD, 0);
        maze2.insertEdge(nodeD, nodeA, 0);

        // nodeA <-> nodeC
        maze2.insertEdge(nodeA, nodeC, 0);
        maze2.insertEdge(nodeC, nodeA, 0);

        // nodeB <-> nodeD
        maze2.insertEdge(nodeB, nodeD, 0);
        maze2.insertEdge(nodeD, nodeB, 0);

        // nodeC <-> nodeF
        maze2.insertEdge(nodeC, nodeF, 0);
        maze2.insertEdge(nodeF, nodeC, 0);

        // nodeE <-> nodeG
        maze2.insertEdge(nodeE, nodeG, 0);
        maze2.insertEdge(nodeG, nodeE, 0);


        System.out.println(Homework8.isExitReachable(maze1, 'A', 'G'));
        System.out.println(Homework8.isExitReachable(maze2, 'A', 'G'));


    }
}
