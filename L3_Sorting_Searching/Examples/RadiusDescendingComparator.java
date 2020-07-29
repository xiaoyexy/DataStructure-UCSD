// RadiusDescendingComparator.java
package L3_Sorting_Searching.Examples;

import java.util.Comparator;

// Compare two circles using radius descending
public class RadiusDescendingComparator implements Comparator<Circle> {
    public int compare(Circle c1, Circle c2) {
        if (c1.getRadius() < c2.getRadius()) {
            return 1;   // Flip result from non-descending comparator
        }
        if (c1.getRadius() > c2.getRadius()) {
            return -1;  // Flip result from non-descending comparator
        }
        return 0;
    }
}
