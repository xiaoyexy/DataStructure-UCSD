// Main.java
package L3_Sorting_Searching.Examples;

// Outputs:
//      Radius comparison (c1, c2): -1
//      Radius descending comparison (c1, c2): 1
//      Distance from origin (c1, c2): 0
//      Distance from origin then radius descending (c1, c2): 1
public class Main {
    public static void main(String[] args) {
        Circle c1 = new Circle(5, 1, 2);
        Circle c2 = new Circle(6, 2, 1);

        System.out.println("Radius comparison (c1, c2): "
                + new RadiusComparator().compare(c1, c2));
        System.out.println("Radius descending comparison (c1, c2): "
                + new RadiusDescendingComparator().compare(c1, c2));
        System.out.println("Distance from origin (c1, c2): "
                + new DistanceFromOriginComparator().compare(c1, c2));
        System.out.println("Distance from origin then radius descending (c1, c2): "
                + new DistanceFromOriginThenRadiusDescendingComparator()
                .compare(c1, c2));
    }
}
