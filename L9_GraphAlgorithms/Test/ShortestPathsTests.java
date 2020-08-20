// ShortestPathsTests.java
package cse41321.algorithms.graph;

import cse41321.containers.Graph;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertSame;

public class ShortestPathsTests {
    private Graph<ShortestPaths.City, ShortestPaths.Connection> graph;

    @BeforeMethod
    public void beforeMethod() {
        // Build city graph with these roads:
        //      "Amsterdam" <-- 1650.594 --> "Berlin"
        //      "Amsterdam" <-- 50.1 --> "Brussels"
        //      "Amsterdam" <-- 99.91 --> "London"
        //      "Amsterdam" <-- 909.17 --> "Paris"
        //      "Amsterdam" <-- 700.03 --> "Rome"
        //      "Berlin" <-- 39.991 --> "Brussels"
        //      "Berlin" <-- 151.101 --> "London"
        //      "Berlin" <-- 312.17 --> "Paris"
        //      "Berlin" <-- 1513.741 --> "Rome"
        //      "Brussels" <-- 306.244 --> "London"
        //      "Brussels" <-- 101.11 --> "Paris"
        //      "Brussels" <-- 504.55 --> "Rome"
        //      "London" <-- 402.745 --> "Paris"
        //      "London" <-- 1766.323 --> "Rome"
        //      "Paris" <-- 12.66 --> "Rome"
        graph = new Graph<ShortestPaths.City, ShortestPaths.Connection>();

        // Add vertices
        ShortestPaths.City amsterdam = addCity("Amsterdam");
        ShortestPaths.City berlin = addCity("Berlin");
        ShortestPaths.City brussels = addCity("Brussels");
        ShortestPaths.City london = addCity("London");
        ShortestPaths.City paris = addCity("Paris");
        ShortestPaths.City rome = addCity("Rome");

        // "Amsterdam" <-- 1650.594 --> "Berlin"
        addConnections(amsterdam, berlin, 1650.594);

        // "Amsterdam" <-- 50.1 --> "Brussels"
        addConnections(amsterdam, brussels, 50.1);

        // "Amsterdam" <-- 99.91 --> "London"
        addConnections(amsterdam, london, 99.91);

        // "Amsterdam" <-- 909.17 --> "Paris"
        addConnections(amsterdam, paris, 909.17);

        // "Amsterdam" <-- 700.03 --> "Rome"
        addConnections(amsterdam, rome, 700.03);

        // "Berlin" <-- 39.991 --> "Brussels"
        addConnections(berlin, brussels, 39.991);

        // "Berlin" <-- 151.101 --> "London"
        addConnections(berlin, london, 151.101);

        // "Berlin" <-- 312.17 --> "Paris"
        addConnections(berlin, paris, 312.17);

        // "Berlin" <-- 1513.741 --> "Rome"
        addConnections(berlin, rome, 1513.741);

        // "Brussels" <-- 306.244 --> "London"
        addConnections(brussels, london, 306.244);

        // "Brussels" <-- 101.11 --> "Paris"
        addConnections(brussels, paris, 101.11);

        // "Brussels" <-- 504.55 --> "Rome"
        addConnections(brussels, rome, 504.55);

        // "London" <-- 402.745 --> "Paris"
        addConnections(london, paris, 402.745);

        // "London" <-- 1766.323 --> "Rome"
        addConnections(london, rome, 1766.323);

        // "Paris" <-- 12.66 --> "Rome"
        addConnections(paris, rome, 12.66);
    }

    @Test
    public void testComputeShortestPaths() {
        ShortestPaths.computeShortestPaths(
                graph,
                new ShortestPaths.City("Amsterdam"));

        ShortestPaths.City amsterdam =
                graph.getVertex(
                        new ShortestPaths.City("Amsterdam")).getData();
        ShortestPaths.City berlin =
                graph.getVertex(
                        new ShortestPaths.City("Berlin")).getData();
        ShortestPaths.City brussels =
                graph.getVertex(
                        new ShortestPaths.City("Brussels")).getData();
        ShortestPaths.City london =
                graph.getVertex(
                        new ShortestPaths.City("London")).getData();
        ShortestPaths.City paris =
                graph.getVertex(
                        new ShortestPaths.City("Paris")).getData();
        ShortestPaths.City rome =
                graph.getVertex(
                        new ShortestPaths.City("Rome")).getData();

        assertSame(amsterdam.getParent(), null);
        assertSame(berlin.getParent(), brussels);
        assertSame(brussels.getParent(), amsterdam);
        assertSame(london.getParent(), amsterdam);
        assertSame(paris.getParent(), brussels);
        assertSame(rome.getParent(), paris);
    }

    private ShortestPaths.City addCity(String name) {
        ShortestPaths.City city = new ShortestPaths.City(name);
        graph.insertVertex(city);
        return city;
    }

    private void addConnections(
            ShortestPaths.City city1,
            ShortestPaths.City city2,
            double cost) {
        graph.insertEdge(city1, city2, new ShortestPaths.Connection(cost));
        graph.insertEdge(city2, city1, new ShortestPaths.Connection(cost));
    }
}
