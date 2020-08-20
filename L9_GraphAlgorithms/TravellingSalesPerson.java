// TravellingSalesPerson.java
package cse41321.algorithms.graph;

import cse41321.containers.SinglyLinkedList;

import static com.google.common.base.Preconditions.*;

public final class TravellingSalesPerson {
    public static final class City {
        private String name;
        private int x, y;
        private VertexColor color;

        public City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            City city = (City) o;

            return name.equals(city.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    /**
     * Returns tour of cities within factor of 2 of optimal tour.
     */
    public static SinglyLinkedList<City> computeShortestTour(
            City[] graph,
            City start) {
        checkNotNull(graph, "graph must not be null");
        checkNotNull(start, "start must not be null");
        for (City city : graph) {
            checkNotNull(city, "graph contains one or more null cities");
        }

        SinglyLinkedList<City> tour = new SinglyLinkedList<City>();

        // Initialize all of the vertices in the graph
        City startCity = null;
        City currentCity = null;
        for (City city : graph) {
            if (city.equals(start)) {
                startCity = city;
                startCity.color = VertexColor.BLACK;
                tour.insertTail(startCity);
                currentCity = startCity;
            } else {
                city.color = VertexColor.WHITE;
            }
        }

        // Verify start found in graph
        checkArgument(startCity != null, "start not found in graph");

        // Use nearest-neighbor heuristic to compute the tour
        for (int i = 0; i < graph.length - 1; ++i) {
            // Select the white city closest to the previous vertex in the tour
            City closestCity = null;
            double closestDistance = Double.MAX_VALUE;
            for (City city : graph) {
                if (city.color == VertexColor.WHITE) {
                    double distance = Math.sqrt(
                            Math.pow(city.x - currentCity.x, 2.0)
                            + Math.pow(city.y - currentCity.y, 2.0));
                    if (distance < closestDistance) {
                        closestCity = city;
                        closestDistance = distance;
                    }
                }
            }

            // Add the closest city to the tour
            tour.insertTail(closestCity);

            // Color the closest city black
            closestCity.color = VertexColor.BLACK;

            // Make the closest city the current city
            currentCity = closestCity;
        }

        // Insert the start city again to complete the tour
        tour.insertTail(startCity);

        return tour;
    }
}
