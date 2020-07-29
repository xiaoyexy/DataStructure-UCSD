// DistanceFromOriginThenRadiusDescendingComparator.java
package L3_Sorting_Searching.Examples;

import java.util.Comparator;

// Compare two circles using distance from origin then radius descending
public class DistanceFromOriginThenRadiusDescendingComparator
        implements Comparator<Circle> {
    public int compare(Circle c1, Circle c2) {
        int result;

        // Compare first using distance from origin
        DistanceFromOriginComparator dfoComparator = new DistanceFromOriginComparator();
        result = dfoComparator.compare(c1, c2);
        if (result != 0) {
            return result;
        }

        // If equal distance from origin, compare using radius descending
        RadiusDescendingComparator rdComparator = new RadiusDescendingComparator();
        return rdComparator.compare(c1, c2);
    }
}
