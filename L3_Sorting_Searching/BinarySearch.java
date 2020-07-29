// BinarySearch.java
package L3_Sorting_Searching;

import java.util.Comparator;

public class BinarySearch {
    public static <T> int binarySearch(
            T[] sortedArray,
            T key,
            Comparator<? super T> comparator) {

        // Continue searching until the left and right indices cross
        int left = 0;
        int right = sortedArray.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;

            int compareResult = comparator.compare(key, sortedArray[middle]);
            if (compareResult > 0) {
                // Key larger than middle, continue search above middle
                left = middle + 1;
            } else if (compareResult < 0) {
                // Key smaller than middle, continue search below middle
                right = middle - 1;
            } else {
                // Key found at middle, return middle index
                return middle;
            }
        }

        // Key not found
        return -1;
    }
}
