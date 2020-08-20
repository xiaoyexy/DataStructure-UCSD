// TravellingSalesPersonTests.java
package L9_GraphAlgorithms.Test;

import L2_LinkedList.SinglyLinkedList;
import L9_GraphAlgorithms.TravellingSalesPerson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TravellingSalesPersonTests {
    private TravellingSalesPerson.City[] graph;

    @BeforeMethod
    public void beforeMethod() {
        // Build city graph with these cities & coordinates:
        //      a (2,1)
        //      b (5,2)
        //      c (1,3)
        //      d (4,3)
        //      e (6,3)
        //      f (2,4)
        //      g (5,5)
        graph = new TravellingSalesPerson.City[]{
                new TravellingSalesPerson.City("a", 2, 1),
                new TravellingSalesPerson.City("b", 5, 2),
                new TravellingSalesPerson.City("c", 1, 3),
                new TravellingSalesPerson.City("d", 4, 3),
                new TravellingSalesPerson.City("e", 6, 3),
                new TravellingSalesPerson.City("f", 2, 4),
                new TravellingSalesPerson.City("g", 5, 5),
        };
    }

    @Test
    public void testComputeShortestTour() {
        SinglyLinkedList<TravellingSalesPerson.City> tour =
                TravellingSalesPerson.computeShortestTour(
                        graph,
                        new TravellingSalesPerson.City("a", 0, 0));

        SinglyLinkedList<TravellingSalesPerson.City>.Element elem =
                tour.getHead();
        assertEquals(elem.getData().getName(), "a");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "c");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "f");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "d");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "b");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "e");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "g");

        elem = elem.getNext();
        assertEquals(elem.getData().getName(), "a");
    }
}
