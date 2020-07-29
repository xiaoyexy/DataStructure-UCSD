// RadiusComparator.java
package L3_Sorting_Searching.Examples;

import java.util.Comparator;


// Compare two circles using radius
public class RadiusComparator implements Comparator<Circle> {
    public int compare(Circle c1, Circle c2) {
        if (c1.getRadius() < c2.getRadius()) {
            return -1;
        }
        if (c1.getRadius() > c2.getRadius()) {
            return 1;
        }
        return 0;
    }
}
