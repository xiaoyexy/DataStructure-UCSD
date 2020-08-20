// MinimumSpanningTreeTests.java
package cse41321.algorithms.graph;

import cse41321.containers.Graph;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MinimumSpanningTreeTests {
    private Graph<MinimumSpanningTree.WaterFacility,
            MinimumSpanningTree.Pipeline> graph;

    @BeforeMethod
    public void beforeMethod() {
        // Build water facility graph with these facilities and potential
        // pipelines:
        //      "San Vicente Reservoir" <-- 10.3 --> "Barona Casino"
        //      "San Vicente Reservoir" <-- 88.8 --> "El Cajon"
        //      "San Vicente Reservoir" <-- 15.2 --> "Lakeside"
        //      "San Vicente Reservoir" <-- 97.0 --> "Poway"
        //      "San Vicente Reservoir" <-- 30.9 --> "Ramona"
        //      "Barona Casino" <-- 23.2 --> "El Cajon"
        //      "Barona Casino" <-- 18.5 --> "Lakeside"
        //      "Barona Casino" <-- 99.1 --> "Poway"
        //      "Barona Casino" <-- 14.5 --> "Ramona"
        //      "El Cajon" <-- 10.1 --> "Lakeside"
        //      "El Cajon" <-- 99.7 --> "Poway"
        //      "El Cajon" <-- 74.6 --> "Ramona"
        //      "Lakeside" <-- 61.7 --> "Poway"
        //      "Lakeside" <-- 50.0 --> "Ramona"
        //      "Poway" <-- 38.7 --> "Ramona"
        graph = new Graph<MinimumSpanningTree.WaterFacility,
                MinimumSpanningTree.Pipeline>();

        // Add vertices
        MinimumSpanningTree.WaterFacility sanVicenteReservoir =
                addFacility("San Vicente Reservoir");
        MinimumSpanningTree.WaterFacility baronaCasino =
                addFacility("Barona Casino");
        MinimumSpanningTree.WaterFacility elCajon =
                addFacility("El Cajon");
        MinimumSpanningTree.WaterFacility lakeside =
                addFacility("Lakeside");
        MinimumSpanningTree.WaterFacility poway =
                addFacility("Poway");
        MinimumSpanningTree.WaterFacility ramona =
                addFacility("Ramona");

        // "San Vicente Reservoir" <-- 10.3 --> "Barona Casino"
        addPipelines(sanVicenteReservoir, baronaCasino, 10.3);

        // "San Vicente Reservoir" <-- 88.8 --> "El Cajon"
        addPipelines(sanVicenteReservoir, elCajon, 88.8);

        // "San Vicente Reservoir" <-- 15.2 --> "Lakeside"
        addPipelines(sanVicenteReservoir, lakeside, 15.2);

        // "San Vicente Reservoir" <-- 97.0 --> "Poway"
        addPipelines(sanVicenteReservoir, poway, 97.0);

        // "San Vicente Reservoir" <-- 30.9 --> "Ramona"
        addPipelines(sanVicenteReservoir, ramona, 30.9);

        // "Barona Casino" <-- 23.2 --> "El Cajon"
        addPipelines(baronaCasino, elCajon, 23.2);

        // "Barona Casino" <-- 18.5 --> "Lakeside"
        addPipelines(baronaCasino, lakeside, 18.5);

        // "Barona Casino" <-- 99.1 --> "Poway"
        addPipelines(baronaCasino, poway, 99.1);

        // "Barona Casino" <-- 14.5 --> "Ramona"
        addPipelines(baronaCasino, ramona, 14.5);

        // "El Cajon" <-- 10.1 --> "Lakeside"
        addPipelines(elCajon, lakeside, 10.1);

        // "El Cajon" <-- 99.7 --> "Poway"
        addPipelines(elCajon, poway, 99.7);

        // "El Cajon" <-- 74.6 --> "Ramona"
        addPipelines(elCajon, ramona, 74.6);

        // "Lakeside" <-- 61.7 --> "Poway"
        addPipelines(lakeside, poway, 61.7);

        // "Lakeside" <-- 50.0 --> "Ramona"
        addPipelines(lakeside, ramona, 50.0);

        // "Poway" <-- 38.7 --> "Ramona"
        addPipelines(poway, ramona, 38.7);
    }

    @Test
    public void testComputeOptimumDistributionSystem() {
        MinimumSpanningTree.computeOptimumDistributionSystem(
                graph,
                new MinimumSpanningTree.WaterFacility(
                        "San Vicente Reservoir"));

        MinimumSpanningTree.WaterFacility sanVicenteReservoir =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "San Vicente Reservoir")).getData();
        MinimumSpanningTree.WaterFacility baronaCasino =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "Barona Casino")).getData();
        MinimumSpanningTree.WaterFacility elCajon =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "El Cajon")).getData();
        MinimumSpanningTree.WaterFacility lakeside =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "Lakeside")).getData();
        MinimumSpanningTree.WaterFacility poway =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "Poway")).getData();
        MinimumSpanningTree.WaterFacility ramona =
                graph.getVertex(
                        new MinimumSpanningTree.WaterFacility(
                                "Ramona")).getData();

        assertNull(sanVicenteReservoir.getParent());
        assertSame(baronaCasino.getParent(), sanVicenteReservoir);
        assertSame(elCajon.getParent(), lakeside);
        assertSame(lakeside.getParent(), sanVicenteReservoir);
        assertSame(poway.getParent(), ramona);
        assertSame(ramona.getParent(), baronaCasino);
    }

    private MinimumSpanningTree.WaterFacility addFacility(String name) {
        MinimumSpanningTree.WaterFacility facility =
                new MinimumSpanningTree.WaterFacility(name);
        graph.insertVertex(facility);
        return facility;
    }

    private void addPipelines(
            MinimumSpanningTree.WaterFacility facility1,
            MinimumSpanningTree.WaterFacility facility2,
            double cost) {
        graph.insertEdge(facility1, facility2,
                new MinimumSpanningTree.Pipeline(cost));
        graph.insertEdge(facility2, facility1,
                new MinimumSpanningTree.Pipeline(cost));
    }
}
