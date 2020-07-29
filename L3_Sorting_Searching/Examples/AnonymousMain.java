// Main.java
package L3_Sorting_Searching.Examples;

import java.util.Comparator;

// Outputs:
//      Radius comparison (c1, c2): -1
public class AnonymousMain {
    public static void main(String[] args) {
        Circle c1 = new Circle(5, 1, 2);
        Circle c2 = new Circle(6, 2, 1);

        // Instantiate anonymous Comparator and call compare on it
        System.out.println("Radius comparison (c1, c2): "
                + new Comparator<Circle>() {
                    public int compare(Circle c1, Circle c2) {
                        if (c1.getRadius() < c2.getRadius()) {
                            return -1;
                        }
                        if (c1.getRadius() > c2.getRadius()) {
                            return 1;
                        }
                        return 0;
                    }
                }.compare(c1, c2));
    }
}
