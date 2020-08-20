// DepthFirstSearchTests.java
package L8_Graphs.Test;

import L8_Graphs.DepthFirstSearch;
import L8_Graphs.Graph;
import L2_LinkedList.SinglyLinkedList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DepthFirstSearchTests {
    private Graph<DepthFirstSearch.Course, Integer> graph;

    @BeforeMethod
    public void beforeMethod() {
        // Build course graph with these courses and prerequisites:
        //      CS100 -> CS200
        //      CS200 -> CS300
        //      CS300 -> MA300
        //      MA100 -> CS300
        //      MA100 -> MA200
        //      MA200 -> MA300
        //      CS150 (no prerequisites)
        graph = new Graph<DepthFirstSearch.Course, Integer>();

        // Courses
        DepthFirstSearch.Course cs100 = new DepthFirstSearch.Course("CS100");
        DepthFirstSearch.Course cs200 = new DepthFirstSearch.Course("CS200");
        DepthFirstSearch.Course cs300 = new DepthFirstSearch.Course("CS300");
        DepthFirstSearch.Course ma100 = new DepthFirstSearch.Course("MA100");
        DepthFirstSearch.Course ma200 = new DepthFirstSearch.Course("MA200");
        DepthFirstSearch.Course ma300 = new DepthFirstSearch.Course("MA300");
        DepthFirstSearch.Course cs150 = new DepthFirstSearch.Course("CS150");
        graph.insertVertex(cs100);
        graph.insertVertex(cs200);
        graph.insertVertex(cs300);
        graph.insertVertex(ma100);
        graph.insertVertex(ma200);
        graph.insertVertex(ma300);
        graph.insertVertex(cs150);

        // CS100 -> CS200
        graph.insertEdge(cs100, cs200, 0);

        // CS200 -> CS300
        graph.insertEdge(cs200, cs300, 0);

        // CS300 -> MA300
        graph.insertEdge(cs300, ma300, 0);

        // MA100 -> CS300
        graph.insertEdge(ma100, cs300, 0);

        // MA100 -> MA200
        graph.insertEdge(ma100, ma200, 0);

        // MA200 -> MA300
        graph.insertEdge(ma200, ma300, 0);
    }

    @Test
    public void testPlanCourses() {
        SinglyLinkedList<String> plannedCourses =
                DepthFirstSearch.planCourses(graph);

        verifyCoursePresent(plannedCourses, "CS100");
        verifyCoursePresent(plannedCourses, "CS200");
        verifyCoursePresent(plannedCourses, "CS300");
        verifyCoursePresent(plannedCourses, "MA100");
        verifyCoursePresent(plannedCourses, "MA200");
        verifyCoursePresent(plannedCourses, "MA300");
        verifyCoursePresent(plannedCourses, "CS150");

        verifyCourseOrder(plannedCourses, "CS100", "CS200");
        verifyCourseOrder(plannedCourses, "CS200", "CS300");
        verifyCourseOrder(plannedCourses, "CS300", "MA300");
        verifyCourseOrder(plannedCourses, "MA100", "CS300");
        verifyCourseOrder(plannedCourses, "MA100", "MA200");
        verifyCourseOrder(plannedCourses, "MA200", "MA300");
    }

    private static void verifyCoursePresent(
            SinglyLinkedList<String> plannedCourses,
            String name) {
        // Loop over all courses
        SinglyLinkedList<String>.Element elem = plannedCourses.getHead();
        while (elem != null) {
            // If found course, success
            if (elem.getData().equals(name)) {
                return;
            }
            elem = elem.getNext();
        }

        // Failed to find course
        throw new IllegalStateException("Failed to find " + name);
    }

    private static void verifyCourseOrder(
            SinglyLinkedList<String> plannedCourses,
            String before,
            String after) {
        boolean foundBefore = false;

        // Loop over all courses, throwing if before does not come before after
        SinglyLinkedList<String>.Element elem = plannedCourses.getHead();
        while (elem != null) {
            // If found before, set flag
            if (elem.getData().equals(before)) {
                foundBefore = true;
            }
            // If found after
            else if (elem.getData().equals(after)) {
                // If already found before, success
                if (foundBefore) {
                    return;
                }

                // Found after before finding before, throw error
                throw new IllegalStateException(before
                        + " did not come before " + after);
            }

            elem = elem.getNext();
        }

        // Failed to find before, after, or both, throw error
        throw new IllegalStateException("Failed to find " + before
                + ", " + after + " or both");
    }
}
